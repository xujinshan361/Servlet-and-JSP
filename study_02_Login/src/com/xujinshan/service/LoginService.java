package com.xujinshan.service;

import com.xujinshan.pojo.User;

public interface LoginService {
	/**
	 * 校验用户登录信息
	 * @param uname
	 * @param pwd
	 * @return
	 */
	User checkLoginService(String uname, String pwd);
	/**
	 * 校验用户Cookie信息
	 * @return
	 */
	User checkUidService(String uid);
}
