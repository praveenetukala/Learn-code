package com.ctel.bpcl.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MRolePermissionMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8010735946100803366L;
	
	@Id
	private String id;
	
	@JsonProperty("displayName")
	private String display_name;
	
	@JsonProperty("parentId")
	private String  parent_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplay_name() {
		return display_name;
	}

	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	
	

}
