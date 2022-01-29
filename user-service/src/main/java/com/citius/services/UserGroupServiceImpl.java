package com.citius.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citius.models.UserGroup;

@Service
public class UserGroupServiceImpl implements UserGroupService{

	@Override
	public String addUserGroup(UserGroup userGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserGroup> getAllUserGroup() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Autowired
//	UserGroupDao userGroupDao;
//	@Override
//	public String addUserGroup(UserGroup userGroup) {
//		return userGroupDao.addUserGroup(userGroup);
//	}
//	@Override
//	public List<UserGroup> getAllUserGroup() {
//		return userGroupDao.getAllUserGroup();
//	}


}
