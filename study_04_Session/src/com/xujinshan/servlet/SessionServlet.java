package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Session学习
 * 		问题：
 * 			一个用户的不同请求处理的数据的共享怎么办？
 * 		解决：
 * 			使用session技术
 * 		原理：
 * 			用户第一次访问服务器，服务器会创建一个session对象给此用户
 * 			并将该session对象的JSESSIONID使用Cookie技术存储到浏览器，
 * 			保证用户的其他请求能够取到同一个session对象，也保证不同的请求能够获取到共享的数据
 * 		特点：
 * 			存储在服务器端
 * 			服务器进行创建
 * 			依赖Cookie技术
 * 			一次会话
 * 			默认存储时间是30分钟
 * 
 * 		作用：
 * 			解决了一个用户不同请求处理的数据共享问题
 * 		使用：
 * 			创建session对象/获取session对象
 * 				HttpSession hs = req.getSession();
 * 				如果请求中拥有session标识符，也就是JSESSIONID，则返回其对应的session对象，
 * 				如果请求中没有session标识符，也就是JSESSIONID，则创建新的session对象，并将其JSESSIONID作为cookie数据存储到浏览器中
 * 				如果session对象是失效了，也会重新创建一个session对象，并将其JSESSIONID存储在浏览器中
 * 			设置session的存储时间
 * 				方式一：
 * 					hs.setMaxInactiveInterval(int seconds);
 * 				方式二：
 * 					修改Tomcat 的配置文件web.xml    ==> 将对所有的项目有效(公共配置文件)
 * 					<session-config>
 * 						<session-timeout>30</session-time>
 * 					</session-config>
 * 				方式三：
 * 					将方式二的内容复制到项目的web.xml 文件下，针对这个项目有效
 * 
 * 				注意：
 * 					在指定的时间内session对象没有被使用则销毁，如果使用了，则重新计时
 * 			设置session强制失效
 * 				hs.invalidate();
 * 			存储和获取数据
 * 				存储：hs.setAttribute(String name, Object value);
 * 				获取：hs.getAttribute(String name);   // 返回的数据类型为Object类型
 * 				注意：
 * 					存储的动作和取出的动作可以发生在不同的请求中，但是存储要先于取出执行
 * 			使用时机：
 * 				一般用户在登录web项目时，会将用户个人信息存储到session中，供该用户的其他请求使用
 * 			总结：
 * 				session解决了一个用户的不同请求的数据的共享问题，只要在JSESSIONID不失效和session对象不失效的情况下
 * 				用户的任意请求在处理时都能获取到同一个session对象
 * 			作用域：
 * 				一次会话
 * 				也可以说在JSESSIONID和session对象不失效的情况下为整个项目内
 * 			session失效处理：
 * 				将用户请求中的JSESSIONID和后台获取到的session对象的ID进行比对，如果一致，
 * 				则session没有失效如；果不一致则证明session没有失效，重定向到登录页面，让用户重新登录
 * 		注意：
 * 			JSESSIONID存储在Cookie的临时存储空间中，浏览器关闭即失效
 * 
 * @author xujinshan361@163.com
 *
 */
public class SessionServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		String name ="张三";
		
		// 处理请求信息
			// 创建session对象
			HttpSession hs = req.getSession();
			System.out.println(hs.getId());
			// 设置session的存储时间
			// hs.setMaxInactiveInterval(5);
			// System.out.println(hs.getId());
			// 设置session的强制失效
//			hs.invalidate();
			// 存储数据
			hs.setAttribute("name", name);
		// 响应处理结果
			// 直接响应
			resp.getWriter().write("session学习");
			// 请求转发
			// 重定向
	}
}
