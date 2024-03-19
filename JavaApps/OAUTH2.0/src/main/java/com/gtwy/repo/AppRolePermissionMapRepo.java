package com.gtwy.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gtwy.entity.AppRolePermissionMap;

public interface AppRolePermissionMapRepo extends JpaRepository<AppRolePermissionMap, Integer> {

	@Query(value = "select role_id from app_role_permission_map where user_id =:userId", nativeQuery = true)
	Integer findRoleByuserId(@Param("userId") Integer userId);

	@Query(value = "select id ,display_name ,parent_id from app_permission where per_type = :perType and id in (select permission_id from app_role_permission_map where role_id = :roleId and status = :status)", nativeQuery = true)
	List<AppRolePermissionMap> findByRolePermissionMap(@Param("perType") String perType,
			@Param("roleId") Integer roleId, @Param("status") String status);
}
