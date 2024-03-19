package com.ctel.bpcl.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ctel.bpcl.entity.User;
import com.ctel.bpcl.exception.GlobalException;
import com.ctel.bpcl.model.MUser;
import com.ctel.bpcl.model.Response;
import com.ctel.bpcl.model.UserInputReq;
import com.ctel.bpcl.repo.MRolePermissionMapRepo;
import com.ctel.bpcl.repo.UserRepo;
import com.ctel.bpcl.security.JWTUtil;
import com.ctel.bpcl.security.model.AuthRequest;
import com.ctel.bpcl.security.model.AuthResponse;
import com.ctel.bpcl.security.model.Role;
import com.ctel.bpcl.util.EncryptDecryptHelper;
import com.ctel.bpcl.util.EncryptedDecryptResponseHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

/**
 *  *  * @author Gowtham G  
 */
@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private MRolePermissionMapRepo mRolePermissionMapRepo;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private EncryptDecryptHelper encryptDecryptHelper;
	@Autowired
	private EncryptedDecryptResponseHelper encryptedDecryptResponseHelper;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${AES.KEY}")
	private String key;

	public Mono<ResponseEntity<?>> findByUsername(AuthRequest ar) {
		Optional<User> user = null;
		logger.info(" userName is {}");

		try {
			UserInputReq inputReq = objectMapper.readValue(encryptDecryptHelper.decrypt(key, ar.getDataKey()),
					UserInputReq.class);
			logger.info("user LoginService Decrypted User input data :" + inputReq.getUsername());
			user = userRepo.findByUserNameAndPwd(inputReq.getUsername(), inputReq.getPassword());
		} catch (JsonMappingException e) {
			logger.error("In The JsonMappingExceptionn {}", e);
			throw new GlobalException(HttpStatus.EXPECTATION_FAILED, "JsonMappingException");
		} catch (JsonProcessingException e) {
			logger.error("In The JsonProcessing Exception", e);
			throw new GlobalException(HttpStatus.EXPECTATION_FAILED, "JsonProcessingException");
		}

		Map<String, String> map = new HashMap<>();
		if (user.isPresent()) {
			User usr = user.get();
			MUser mUser = BeanUtils.instantiateClass(MUser.class);
			mUser.setPassword(user.get().getPwd());
			mUser.setUsername(user.get().getUserName());
			mUser.setRoles(Arrays.asList(Role.ROLE_ADMIN));
			Integer roleId = userRepo.findByRoleId(user.get().getId());
			usr.setRolePermissionMap(mRolePermissionMapRepo.findByRolePermissionMap("AND_MENU", roleId, "A"));
			usr.setPwd(user.get().getPwd());
			usr.setUserName(user.get().getUserName());
			logger.info("user LoginService,success ,Before Encrypting data {}", usr);
			map = encryptedDecryptResponseHelper.encryptedSuccessResponse(
					ResponseEntity
							.ok(new AuthResponse(HttpStatus.OK.value(), "success", jwtUtil.generateToken(mUser), usr)),
					UserService.class);
			return Mono.just(ResponseEntity.ok(map));
		} else {
			logger.info("user LoginService,Unauthorized Request ");
			return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new Response<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized Request", null)));
		}

	}

	public Mono<ResponseEntity<?>> findByUsernameAndPassword(AuthRequest ar) {

		logger.info(" userName is {}");
		Optional<User> user = userRepo.findByUserNameAndPwd(ar.getUsername(), ar.getPassword());

		if (user.isPresent()) {
			MUser mUser = BeanUtils.instantiateClass(MUser.class);
			mUser.setPassword(encryptDecryptHelper.encrypt(user.get().getPwd()));
			mUser.setUsername(encryptDecryptHelper.encrypt(user.get().getUserName()));
			mUser.setRoles(Arrays.asList(Role.ROLE_ADMIN));

			return Mono.just(ResponseEntity
					.ok(new AuthResponse(HttpStatus.OK.value(), "success", jwtUtil.generateToken(mUser), null)));

		} else {
			return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new Response<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized Request", null)));
		}

	}

}
