package com.citius.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citius.models.User;
import com.citius.models.UserGroup;
import com.citius.models.User_Roles;
import com.citius.services.UserGroupService;
import com.citius.services.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	UserGroupService userGroupService;

	@Operation(summary = "Used for Authentication of Users - called by Gateway")
	@GetMapping("/auth/{username}")
	public User authenticateUser(@PathVariable String username) {

		return userService.authenticateUser(username);

	}

	@Operation(summary = "Used to get all Users from System")
	@GetMapping("/users")
	public List<User> getAllUsers() {
		System.out.println("Inside Get Users");
		return userService.getAllUsers();
	}

	@Operation(summary = "Add new User Group")
	@PostMapping("/usergroup")
	public ResponseEntity<?> addUserGroup(@RequestBody UserGroup userGroup) {
		String res = userGroupService.addUserGroup(userGroup);
		if (res.equalsIgnoreCase("success"))
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Operation(summary = "Get All User Groups from System")
	@GetMapping("/usergroup")
	public List<UserGroup> getAllUserGroups() {
		return userGroupService.getAllUserGroup();
	}

	@Operation(summary = "Map Users to user groups : need userid and userGroupId")
	@PostMapping("/mapRoles")
	public ResponseEntity<?> addRolesToUser(@RequestBody Map<String, String> requestMap) throws IOException {
		long userId = Long.parseLong(requestMap.get("userId"));
		long userGroupId = Long.parseLong(requestMap.get("userGroupId"));
		User_Roles userRole = userService.addRolesToUser(userId, userGroupId);

		if (userRole != null) {
			return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
		}

		return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Operation(summary = "Get all User Groups/Roles assigned to particular user")
	@GetMapping("/roleMapping/{username}")
	public Set<UserGroup> getAllRoles(@PathVariable String username) {
		return userService.getUserRoles(username);
	}

	@Operation(summary = "Create New User")
	@PostMapping("/userCreate")
	public User createUser(@RequestBody User user) {
		Set<User_Roles> userRoles = user.getUserRoles();
		userRoles.stream().forEach(userRole -> {
			userRole.setUser(user);
		});

		User createdUser = new User();
		try {
			createdUser = userService.createUser(user, userRoles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return createdUser;
	}

	@Operation(summary = "Activate or Deactivate User from System")
	@PutMapping("/disableUser/{username}")
	public ResponseEntity<?> disableUser(@PathVariable String username) {
		String res = userService.updateIsActive(username);
		if (res.equalsIgnoreCase("success"))
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
