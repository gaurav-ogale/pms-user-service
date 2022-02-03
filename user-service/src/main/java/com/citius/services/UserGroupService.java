package com.citius.services;

import java.util.List;

import com.citius.models.UserGroup;

public interface UserGroupService {
	
	UserGroup addUserGroup(UserGroup userGroup) throws Exception;
	
	List<UserGroup> getAllUserGroup();
	


}
