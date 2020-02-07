package com.xujinshan.serviceImpl;

import com.xujinshan.dao.UserDao;
import com.xujinshan.daoImpl.UserDaoImpl;
import com.xujinshan.service.UserService;
import com.xujinshan.vo.User;

public class UserServiceImpl implements UserService{

	// 获取Dao层对象
	UserDao ud = new UserDaoImpl();
	
	@Override
	public User getUserInfoService(String name) {
		return ud.getUserInfo(name);
	}
}
