package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AjaxServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		// 设置响应编码格式
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		// 处理请求信息
		// 响应处理结果
		resp.getWriter().write("今天的雾霾真的是新鲜");
	}
}
