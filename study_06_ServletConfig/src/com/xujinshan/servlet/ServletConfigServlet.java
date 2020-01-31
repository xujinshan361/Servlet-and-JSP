package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ServletConfig 对象的学习
 * 		问题：
 * 			使用ServletContext对象可以获取web.xml 中的全局配置文件，
 * 			在web.xml 中，每个Servlet也可以进行单独的配置，那么该怎么获取配置信息？
 * 		解决：
 * 			使用ServletConfig对象
 * 		作用：
 * 			ServletConfig 对象是Servlet 的专属配置对象，每个Servlet都单独拥有一个ServletConfig对象，用来获取web.xml 中的配置信息。
 * 		使用：
 * 			获取ServletConfig对象
 * 				ServletConfig sc = this.getServletConfig();
 * 			获取web.xml 中Servlet的配置信息(在web.xml 中配置servlet的配置文件)
 * 				String code = sc.getInitParameter("config");
 * @author 23503
 *
 */
public class ServletConfigServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取ServletConfig对象
		ServletConfig sc = this.getServletConfig();
		// 获取web.xml 中的Servlet的配置信息
		String code = sc.getInitParameter("config");
		System.out.println(code);
		
	}
}
