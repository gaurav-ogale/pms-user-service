package com.citius;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.citius.models.UserGroup;
import com.citius.models.UserRoles;
import com.citius.repository.UserGroupRepository;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restController() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner init(UserGroupRepository userGroupRepository) {
		return args -> {
			Set<UserGroup> userGroup = new HashSet<>();
			userGroup.add(new UserGroup(1L, UserRoles.ROLE_ADMIN.toString()));
			userGroup.add(new UserGroup(2L, UserRoles.ROLE_PHYSICIAN.toString()));
			userGroup.add(new UserGroup(3L, UserRoles.ROLE_NURSE.toString()));
			userGroup.add(new UserGroup(4L, UserRoles.ROLE_PATIENT.toString()));
			userGroup.forEach(usrGrp -> userGroupRepository.save(usrGrp));
		};
	}
}
