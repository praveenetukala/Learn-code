package com.ctel.bpcl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctel.bpcl.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	@Query("select u from User u where u.userName = :userName and u.pwd=:pwd")
	Optional<User> findByUserNameAndPwd(@Param("userName") String username, @Param("pwd") String password);

	@Query(value = "select role_id from bpcl_user_role_map burm where user_id =:userId",nativeQuery = true)
	Integer findByRoleId(@Param("userId")Integer userId);

	
}
