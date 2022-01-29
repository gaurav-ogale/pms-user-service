package com.citius.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.citius.models.AuthUser;
import com.citius.models.User;
import com.citius.models.User_Roles;

@Service
public interface UserService {

	List<User> getAllUsers();
	User getUser(User user);
	AuthUser authenticateUser(String username) throws Exception;
	Set<User_Roles> getUserRoles(String userName);
	User_Roles addRolesToUser(long userId, long userGroupId);
	
	User createUser(User user, Set<User_Roles> userRoles) throws Exception;
}
