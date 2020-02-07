package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xujinshan.service.UserService;
import com.xujinshan.serviceImpl.UserServiceImpl;
import com.xujinshan.vo.User;

public class UserServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		String name = req.getParameter("name");
		System.out.println(name);
		// 处理响应信息
			// 获取业务层对象
			UserService us = new UserServiceImpl();
			// 处理业务
			User u = us.getUserInfoService(name);
			System.out.println("查询到的结果为："+u);
		// 响应处理结果
			// 谷歌的Gson 包，传入对象，直接返回拼接好的JSON格式  
			// {"uid":2,"uname":"后裔","price":200.0,"loc":"射手","desc":"很牛逼"}
		resp.getWriter().write(new Gson().toJson(u));  //JSON 格式
		
	}
}
