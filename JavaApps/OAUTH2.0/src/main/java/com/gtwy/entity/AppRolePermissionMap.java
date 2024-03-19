package com.gtwy.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name = "app_role_permission_map")
public class AppRolePermissionMap implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private AppRole roleId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private AppUser userId;

	@Column(name = "permission_id")
	private String permissionId;

	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AppRole getRoleId() {
		return roleId;
	}

	public void setRoleId(AppRole roleId) {
		this.roleId = roleId;
	}

	public AppUser getUserId() {
		return userId;
	}

	public void setUserId(AppUser userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		String respose = null;
		try {
			respose = new ObjectMapper().writer().writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return respose;
	}
}