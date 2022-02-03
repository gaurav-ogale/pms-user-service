package com.citius.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.models.UserGroup;
import com.citius.models.UserRoles;
import com.citius.repository.UserGroupRepository;

@Service
public class UserGroupServiceImpl implements UserGroupService {

	@Autowired
	private UserGroupRepository userGroupRepositroy;

	@Override
	public UserGroup addUserGroup(UserGroup userGroup) throws Exception {
		UserRoles userRoleValues[] = UserRoles.values();
		Boolean validRoleValue = false;
		for (UserRoles data : userRoleValues) {
			if (userGroup.getUserRole().equals(data.name())) {
				validRoleValue = true;
				break;
			}
		}
		if (validRoleValue)
			return userGroupRepositroy.save(userGroup);
		else
			throw new Exception("Not Valid Data!");
	}

	@Override
	public List<UserGroup> getAllUserGroup() {
		return userGroupRepositroy.findAll();
	}

}
