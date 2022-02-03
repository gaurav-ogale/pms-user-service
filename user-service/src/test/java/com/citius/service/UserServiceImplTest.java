package com.citius.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.citius.models.User;
import com.citius.models.UserGroup;
import com.citius.models.User_Roles;
import com.citius.repository.UsersRepository;
import com.citius.services.UserServiceImpl;

@ExtendWith(SpringExtension.class)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
public class UserServiceImplTest {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Mock
	UsersRepository userRepository;

	@Test
	void createUserTest() throws Exception {
		User user = new User();
		user.setUsername("gaurav_ogale123");
		user.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
		user.setUserEmail(null);
		user.setUserContactNo("873787383");
		user.setUserDOB(LocalDate.of(1995, 11, 1));
		user.setUserFirstName("Gaurav");
		user.setUserLastName("Ogale");
		user.setUserTitle("Mr");

		UserGroup userGroup = new UserGroup();
		userGroup.setUserRoleId(11L);
		userGroup.setUserRole("Admin");

		UserGroup userGroup2 = new UserGroup();
		userGroup2.setUserRoleId(12L);
		userGroup2.setUserRole("Physician");

		UserGroup userGroup3 = new UserGroup();
		userGroup3.setUserRoleId(13L);
		userGroup3.setUserRole("Nurse");

		Set<User_Roles> userRoles = new HashSet<User_Roles>();

		User_Roles userRole = new User_Roles();
		userRole.setUserGroup(userGroup);
		userRole.setUser(user);

		User_Roles userRole1 = new User_Roles();
		userRole1.setUserGroup(userGroup2);
		userRole1.setUser(user);

		User_Roles userRole2 = new User_Roles();
		userRole2.setUserGroup(userGroup3);
		userRole2.setUser(user);

		userRoles.add(userRole);
		userRoles.add(userRole1);
		userRoles.add(userRole2);

		//User testUser = userServiceImpl.createUser(user, userRoles);
		//System.out.println(testUser.toString());
		//assertThat(testUser.getUserId()).isNotNull();
	}

	@Test
	void getAllUsers() {
		assertEquals(7,userServiceImpl.getAllUsers().size());		
	}
	
	@Test
	void updateIsActive() {
		String str = userServiceImpl.updateIsActive("gauravO");
		assertEquals("Success", str);
	}
	
	@Test
	void getUserRoles() {
		Set<UserGroup> userGroups = userServiceImpl.getUserRoles("gaurav_ogale12");
		System.out.println(userGroups);
	}
	

}
