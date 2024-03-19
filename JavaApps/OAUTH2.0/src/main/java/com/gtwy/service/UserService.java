package com.gtwy.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gtwy.entity.AppRolePermissionMap;
import com.gtwy.entity.AppUser;
import com.gtwy.exception.GlobalException;
import com.gtwy.model.Response;
import com.gtwy.model.UserInputReq;
import com.gtwy.oauthReqRes.AuthRequest;
import com.gtwy.oauthReqRes.AuthResponse;
import com.gtwy.repo.AppRolePermissionMapRepo;
import com.gtwy.repo.AppUserRepo;
import com.gtwy.utility.EncryptDecryptHelper;
import com.gtwy.utility.EncryptedDecryptResponseHelper;

@Service
public class UserService {

	@Value("${AES.KEY}")
	private String key;

	private EncryptDecryptHelper encryptDecryptHelper;
	private AppUserRepo userRepo;
	private AppRolePermissionMapRepo appRolePermissionMapRepo;
	private EncryptedDecryptResponseHelper responseHelper;
	private ObjectMapper objectMapper;

	@Autowired
	public UserService(ObjectMapper objectMapper, EncryptDecryptHelper encryptDecryptHelper, AppUserRepo userRepo,
			AppRolePermissionMapRepo appRolePermissionMapRepo, EncryptedDecryptResponseHelper responseHelper) {
		this.encryptDecryptHelper = encryptDecryptHelper;
		this.objectMapper = objectMapper;
		this.userRepo = userRepo;
		this.appRolePermissionMapRepo = appRolePermissionMapRepo;
		this.responseHelper = responseHelper;
	}

	public ResponseEntity<?> findByUsername(AuthRequest authRequest) {

		System.out.println("UserService.findByUsername()");
		System.out.println("AuthRequest :" + authRequest);

		Optional<AppUser> user = null;

		try {
			/* Decrypting the data */
			// UserInputReq inputReq =
			// objectMapper.readValue(encryptDecryptHelper.decrypt(key,
			// authRequest.getDataKey()),
			// UserInputReq.class);

			user = userRepo.findByUserNameAndPwd(authRequest.getUsername(), authRequest.getPassword());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (user.isPresent()) {
			AppUser usr = user.get();

			// Getting the app_role_permission_map by user id
			Integer roleId = appRolePermissionMapRepo.findRoleByuserId(usr.getId());

			// Getting the list of AppRolePermissionMap by role id
			List<AppRolePermissionMap> appRolePermissionMaps = appRolePermissionMapRepo
					.findByRolePermissionMap("AND_MENU", roleId, "A");

			// Setting the roles to users AppRolePermissionMap
			usr.setAppRolePermissionMap(appRolePermissionMaps);

			/* Encrypting the data */
			// Map<String, String> map = responseHelper.encryptedSuccessResponse(
			// ResponseEntity.ok(new AuthResponse(HttpStatus.OK.value(), "success", usr)),
			// UserService.class);
			return ResponseEntity.ok(usr);
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new Response<>(HttpStatus.UNAUTHORIZED.value(), "Unauthorized Request", null));
		}
	}
}
