package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求信息
		// 处理请求信息
		req.setAttribute("str", "监听器学习");
		HttpSession hs = req.getSession();
		hs.setAttribute("str", "session中增加数据");
		hs.invalidate();  // 销毁
		// 响应处理结果
		resp.getWriter().write("this is listener study");
	}
}
