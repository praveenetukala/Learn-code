package com.ctel.bpcl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctel.bpcl.entity.MRolePermissionMap;

public interface MRolePermissionMapRepo  extends JpaRepository<MRolePermissionMap, String> {

	@Query(value = "select 	id,display_name ,parent_id from 	  bpcl_permission   where 	 per_type  = :perType 	and  id  in ( 	select 		 permission_id 	from 		 bpcl_role_permission_map 	where 		 role_id  = :roleId		and  status  = :status)",nativeQuery = true)
	List<MRolePermissionMap> findByRolePermissionMap(@Param("perType") String perType,@Param("roleId")Integer roleId ,@Param("status") String status);

}
