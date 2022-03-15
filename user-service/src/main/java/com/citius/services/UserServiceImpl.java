package com.citius.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.citius.exception.UserInternalServerException;
import com.citius.models.Email;
import com.citius.models.User;
import com.citius.models.UserGroup;
import com.citius.models.UserRoles;
import com.citius.models.User_Roles;
import com.citius.repository.UserGroupRepository;
import com.citius.repository.UserRolesRepository;
import com.citius.repository.UsersRepository;
import com.citius.utils.CommonKeys;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	private UserGroupRepository userGroupRepository;

	@Autowired
	private UserRolesRepository userRoleRepository;

	@Autowired
	private RestTemplate restTemplate;

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
	public User createUser(User user, Boolean isPatient, Set<UserGroup> employeeRoleMap) throws Exception {
		Set<User_Roles> roleMap = new HashSet<User_Roles>();

		User tempUser = this.userRepository.findByUsername(user.getUsername());

		if (tempUser != null)
			throw new UserInternalServerException("Username Already Exits, Please login");
		else {
//			if (isPatient) {
//				// Set default UserGroup for All - Patient
//				User_Roles role = new User_Roles();
//				role.setUser(user);
//				role.setUserGroup(userGroupRepository.getUserGroupByuserRole(UserRoles.ROLE_PATIENT.name()));
//				roleMap.add(role);
//				user.getUserRoles().addAll(roleMap);
//			} else {
//				employeeRoleMap.stream().forEach(userGrp -> {
//					User_Roles role = new User_Roles();
//					role.setUser(user);
//					role.setUserGroup(userGrp);
//					roleMap.add(role);
//				});
//				user.getUserRoles().addAll(roleMap);
//			}
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
			throw new UserInternalServerException("Not Valid Operation");
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
		throw new UserInternalServerException("Invalid Operation!");
	}

	@Override
	public String forgetPassword(String username) {
		User user = new User();
		user = userRepository.findByUsername(username);
		if (user != null) {
			user.setPassword(CommonKeys.DEFAULT_ENCRYPTED_PASSWORD);
			userRepository.save(user);
			// Send Inputs to Notification Service with Default Password on User's Email
			return CommonKeys.SUCCESS;
		} else
			throw new UserInternalServerException("No User Found for Entered Data");
	}

	@Override
	public String sendPasswordResetEmail(String username) {

		Email email = new Email("gaurav.ogale@outlook.com", "Test", username, "USER-SERVICE");

		String str = restTemplate.postForObject("http://NOTIFICATION-SERVICE/", email, String.class);
		return str;
	}

	@Override
	public List<User> getDoctor() {
		return userRepository.getUsersBasedOnRole(UserRoles.ROLE_PHYSICIAN.toString());
	}

	@Override
	public List<User> getEmployees(String role) {
		return userRepository.getUsersBasedOnRole(role);
	}

}
