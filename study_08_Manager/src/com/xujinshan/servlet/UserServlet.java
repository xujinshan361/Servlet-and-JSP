package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.User;
import com.xujinshan.service.UserService;
import com.xujinshan.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {

	// Service 层对象
	UserService us = new UserServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取操作符
		String operation = req.getParameter("operation");
		if("login".equals(operation)) {
			// 调用登录处理方法
			checkUserLogin(req,resp);
		}else if("register".equals(operation)) {
			// 调用注册功能
			
		}else {
			System.out.println("没有找到对应的操作符："+operation);
		}
		
		
	}
	// 处理登录

	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) {
		// 获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		System.out.println(uname+"--->"+pwd);
		// 处理请求信息
			// 获取service层对象
		
			// 校验
			User u = us.checkUserLoginService(uname, pwd);
			System.out.println(u);
		// 响应处理结果
			// 直接响应
			// 请求转发
			// 重定向
		
	}
}
