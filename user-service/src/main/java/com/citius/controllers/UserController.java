package com.citius.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citius.exception.UserInternalServerException;
import com.citius.models.DoctorSpecializations;
import com.citius.models.Employee;
import com.citius.models.User;
import com.citius.models.UserGroup;
import com.citius.models.User_Roles;
import com.citius.services.UserGroupService;
import com.citius.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(description = "This Service will Handle All User Management,User Access Requests", name = "User Controller")
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
	
	@Operation(summary = "Get User from User id")
	@GetMapping("/user/{userId}")
	public User getUser(@PathVariable String userId) {
		return userService.getUser(userId);
	}

	@Operation(summary = "Add new User Group")
	@PostMapping("/usergroup")
	public ResponseEntity<?> addUserGroup(@RequestBody UserGroup userGroup) throws Exception {

		if (userGroupService.addUserGroup(userGroup) != null)
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
	@PostMapping("/user")
	public User createUser(@RequestBody User user) throws Exception {
		User createdUser = new User();

		createdUser = userService.createUser(user, true, null);

		return createdUser;
	}

	@Operation(summary = "Create New Employee")
	@PostMapping("/employee")
	public User createEmployee(@RequestBody Employee employee) throws Exception {
		User createdUser = new User();

		if (employee.getUserGroup() != null) {
			createdUser = userService.createUser(employee.getUser(), false, employee.getUserGroup());
		} else {
			throw new UserInternalServerException("UserGroup Should not be Empty for Employees");
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

	@Operation(summary = "Password Reset")
	@GetMapping("/reset/{username}")
	public String forgotPassword(@PathVariable String username) {
		String res = userService.sendPasswordResetEmail(username);
		return res;
	}

	@GetMapping("/employee/{role}")
	public List<User> getEmployees(@PathVariable String role) {
		return userService.getEmployees(role);
	}
	
	@GetMapping("/specializations")
	public DoctorSpecializations[] getSpecializations(){
		return DoctorSpecializations.values();
	}

}
