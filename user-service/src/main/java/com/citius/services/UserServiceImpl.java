package com.citius.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public User authenticateUser(String username) {

		User user = new User();
		user = userRepository.findByUsername(username);
		if (user != null) {
			return user;
		}
		return user;
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
	public Set<UserGroup> getUserRoles(String userName) {

		User userDetails = userRepository.findByUsername(userName);
		Set<UserGroup> userGroups = new HashSet<>();

		if (userDetails.getUserRoles() != null) {
			userDetails.getUserRoles().stream().forEach(userDetail -> {
				UserGroup userGroup = userDetail.getUserGroup();
				userGroups.add(userGroup);
			});
		} else {
			return null;
		}

		return userGroups;
	}

	public User_Roles addRolesToUser(long userId, long userGroupId) {
		User_Roles userRoles = new User_Roles();

		User user = userRepository.findById(userId).get();
		UserGroup userGroup = userGroupRepository.getById(userGroupId);
		userRoles.setUser(user);
		userRoles.setUserGroup(userGroup);

		return userRoleRepository.save(userRoles);
	}

	@Override
	public String updateIsActive(String userName) {
		User user = new User();
		user = userRepository.findByUsername(userName);
		Boolean isActive = user.getIsActive();
		user.setIsActive(!isActive);
		user = userRepository.save(user);

		if (user.getIsActive() != isActive) {
			return "Success";
		}
		return null;
	}

}
