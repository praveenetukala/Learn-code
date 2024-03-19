package com.gtwy.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gtwy.entity.AppUser;

public interface AppUserRepo extends JpaRepository<AppUser, Integer> {

	@Query(value = "select * from app_user  where user_name = :userName and  pwd=:pwd", nativeQuery = true)
	Optional<AppUser> findByUserNameAndPwd(@Param("userName") String username, @Param("pwd") String password);
}
