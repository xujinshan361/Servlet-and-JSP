package com.xujinshan.service;

import com.xujinshan.pojo.User;

public interface UserService {
	
	/**
	 * 校验用户登录
	 * @param uname 用户名
	 * @param pwd 密码
	 * @return 查询到的用户信息
	 */
	User checkUserLoginService(String uname, String pwd);
}
