package com.citius.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "usergroup")
public class UserGroup {
	@Id
	private Long userRoleId;
	private String userRole;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userGroup")
	@JsonIgnore
	private Set<User_Roles> userRoles = new HashSet<User_Roles>();

	public UserGroup() {
		// TODO Auto-generated constructor stub
	}

	public UserGroup(Long userRoleId, String userRole, Set<User_Roles> userRoles) {
		super();
		this.userRoleId = userRoleId;
		this.userRole = userRole;
		this.userRoles = userRoles;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Set<User_Roles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<User_Roles> userRoles) {
		this.userRoles = userRoles;
	}

}
