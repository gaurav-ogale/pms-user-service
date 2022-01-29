package com.citius.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.citius.models.AuthUser;
import com.citius.models.User;
import com.citius.models.UserGroup;
import com.citius.models.User_Roles;
import com.citius.repository.UserGroupRepository;
import com.citius.repository.UserRolesRepository;
import com.citius.repository.UsersRepository;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private UserGroupRepository userGroupRepository;

	@Autowired
	private UserRolesRepository userRoleRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(User user) {
		return null;
	}

	@Override
	public AuthUser authenticateUser(String username) throws Exception {
		
		AuthUser userCreds = new AuthUser();
		User user = userRepository.findByUsername(username);
		if(user!=null) {
			userCreds.setUsername(user.getUsername());
			userCreds.setPassword(user.getPassword());
		}		
		return userCreds;
	}

	@Override
	public User createUser(User user, Set<User_Roles> userRoles) throws Exception {

		User tempUser = this.userRepository.findByUsername(user.getUsername());

		if (tempUser != null)
			throw new Exception("User Already Exists !");
		else {
			// Save User Role
			if (userRoles != null) {
				userRoles.stream().forEach(userRole -> userGroupRepository.save(userRole.getUserGroup()));
				user.getUserRoles().addAll(userRoles);
			}
			// save User
			tempUser = userRepository.save(user);
		}

		return tempUser;
	}

	@Override
	public Set<User_Roles> getUserRoles(String userName) {
		User userDetails = userRepository.findByUsername(userName);
	
		if(userDetails != null) {
			return userDetails.getUserRoles();
		}
		
		return null;
	}
	
	public User_Roles addRolesToUser(long userId, long userGroupId) {
		User_Roles userRoles = new User_Roles();
		
		User user = userRepository.findById(userId).get();		
		UserGroup userGroup = userGroupRepository.getById(userGroupId);		
		userRoles.setUser(user);
		userRoles.setUserGroup(userGroup);
		
		return userRoleRepository.save(userRoles);
	}


}
