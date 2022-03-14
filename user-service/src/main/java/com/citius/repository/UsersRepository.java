package com.citius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citius.models.User;

public interface UsersRepository extends JpaRepository<User, Long> {

	@Query(value = "select * from users u where u.user_email = ?1", nativeQuery = true)
	User getUserWithEmail(String username);

	User findByUsername(String username);

	@Query(value = "select a from User a JOIN User_Roles b ON a.userId = b.user JOIN UserGroup c ON b.userGroup = c.userRoleId where c.userRole=:role")
	List<User> getUsersBasedOnRole(String role);

}
