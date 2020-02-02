package com.xujinshan.service.impl;

import com.xujinshan.dao.UserDao;
import com.xujinshan.dao.impl.UserDaoImpl;
import com.xujinshan.pojo.User;
import com.xujinshan.service.UserService;

public class UserServiceImpl implements UserService{

	// 声明Dao层对象
	UserDao ud = new UserDaoImpl();
	// 用户登录
	@Override
	public User checkUserLoginService(String uname, String pwd) {
		
		return ud.checkUserLoginDao(uname, pwd);
	}
}
