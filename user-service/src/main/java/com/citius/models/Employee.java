package com.citius.models;

import java.util.HashSet;
import java.util.Set;

public class Employee {

	private User user;
	private Set<UserGroup> userGroup = new HashSet<>();

	public Employee(User user, Set<UserGroup> userGroup) {
		super();
		this.user = user;
		this.userGroup = userGroup;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<UserGroup> getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(Set<UserGroup> userGroup) {
		this.userGroup = userGroup;
	}

}
