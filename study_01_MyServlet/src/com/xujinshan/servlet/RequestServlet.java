package com.xujinshan.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request 对象学习：
 * 		解释：
 * 			服务器接收到浏览器的请求后，会创建一个request对象，对象中存储了此次请求相关的请求数据。
 * 			服务器在调用Servlet时会将创建的request对象作为实参传递给Servlet的方法，比如：service方法
 * 
 * 		作用：request对象中封装了当前请求的所有信息
 * 
 * 		使用：
 * 			获取请求头数据
 * 				req.getMethod()   // 获取请求方式
 * 				req.getRequestURL()  // 获取请求URL信息
 * 				req.getRequestURI()  // 获取协议
 * 	
 * 			获取请求行数据
 * 				req.getHeader("user-agent");  // 返回指定的请求头信息
 * 				req.getHaderNames();          // 返回请求头的键名的枚举集合
 * 
 * 			获取用户数据
 * 				req.getParameter("uname");   // 返回指定的用户数据
 * 				req.getParameterValues("fav");   // 返回同键不同值的请求数据(多选)。返回数组
 * 				req.getPatameterNames() // 返回所有用户请求数据的枚举集合
 * 			注意：
 * 				如果要获取的请求数据不存在，不会报错，返回null，(处理数据的时候可能出现空指针异常)
 * 
 * 		注意：
 * 			request对象由Tomcat服务器创建，并作为实参传递给处理请求的Servlet的service方法
 * @author 23503
 *
 */
public class RequestServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取请求头数据
			
		// 获取请求方式
		String method = req.getMethod();
		System.out.println(method);
		// 获取请求RUL
		StringBuffer url = req.getRequestURL();
		System.out.println(url);
		// 获取URI
		String uri = req.getRequestURI();
		// 获取请求协议
		String h = req.getScheme();
		System.out.println(h);
		
		System.out.println("***************");
		
		// 获取请求行数据
		
		// 获取指定的请求行信息
		String value = req.getHeader("User-Agent");
		System.out.println(value);
		// 获取所有的请求行的键的枚举
		Enumeration e = req.getHeaderNames();
		while(e.hasMoreElements()) {
			String name = (String)e.nextElement();   // 返回为Object对象，需要强转
			String value2 = req.getHeader(name);
			System.out.println(name + ":" + value2);
		}
		
		System.out.println("****************");
		
		// 获取用户数据
		String name = req.getParameter("uname");   // 表单中的name属性，如果获取的结果是null可能是name属性值不正确
		String pwd = req.getParameter("pwd");
		System.out.println("name:" + name + "-->pwd:" +pwd);
//		String fav = req.getParameter("fav");    // 只能获取一个值，不能获取多个值
		
		String[] favs = req.getParameterValues("fav");
		if(favs != null) {
			for(String fav : favs) {
				System.out.println(fav);
			}
		}
		
		// 获取所有的用户请求数据的键的枚举集合-->req.getParameterNames()
	}
}
