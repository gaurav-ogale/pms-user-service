package com.citius.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.citius.models.User;
import com.citius.models.UserGroup;
import com.citius.models.User_Roles;

@Service
public interface UserService {

	List<User> getAllUsers();
	User getUser(User user);
	User authenticateUser(String username);
	Set<UserGroup> getUserRoles(String userName);
	User_Roles addRolesToUser(long userId, long userGroupId);	
	User createUser(User user , Boolean isPatient, Set<UserGroup> employeeRoleMap) throws Exception;
	String forgetPassword(String username);
	String updateIsActive(String userName);
}
