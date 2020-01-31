package com.xujinshan.service.impl;

import com.xujinshan.dao.LoginDao;
import com.xujinshan.dao.impl.LoginDaoImpl;
import com.xujinshan.pojo.User;
import com.xujinshan.service.LoginService;

public class LoginServiceImpl implements LoginService{

	// 创建Dao层过度向
	LoginDao ld = new LoginDaoImpl();
	// 校验用户登录信息
	@Override
	public User checkLoginService(String uname, String pwd) {
		return ld.checkLoginDao(uname, pwd);
	}
	// 校验Cookie信息
	@Override
	public User checkUidService(String uid) {
		return ld.checkUidDao(uid);
	}	
}
