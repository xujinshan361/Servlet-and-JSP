package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xujinshan.pojo.User;
import com.xujinshan.service.LoginService;
import com.xujinshan.service.impl.LoginServiceImpl;

/**
 * 请求中文乱码解决
 * 		1.使用String进行数据的重新编码
 * 		实例：
 * 			uname = new String(uname.getBytes("iso8859-1"),"utf-8");
 * 		2.使用公共配置
 * 			get方式：
 * 				步骤一：req.setCharacterEncoding("utf-8");
 * 				步骤二：在Tomcat的目录下的conf目录中修改server.xml文件
 * 					<Connector port="8080" protocol="HTTP/1.1"
 * 					connectionTimeout="20000"
 * 					redirectPort="8443" URIEncoding="UTF-8" useBodyEncodingForURI="true"/>(添加useBodyEncodingForURI="true")
 * 
 * 			post方式：
 * 				req.setCharacterEncoding("utf-8");
 * 
 * Servlet 流程总结：
 * 		浏览器发起请求到服务器(请求)
 * 		服务器接收浏览器的请求进行解析，创建request对象存储请求数据
 * 		服务器调用对应的Servlet进行请求处理，并将request对象作为实参传递给Servlet的方法
 * 		Servlet的方法执行进行请求处理
 * 			 // 设置请求编码格式
 * 			 // 设置响应编码格式
 * 			 // 获取请求信息
 * 			 // 处理请求信息
 * 				// 创建业务层对象
 * 				// 调用业务层对象的方法
 * 			 // 响应处理结果
 * 
 *  数据流转流程：
 *  	浏览器--> 服务器--> 数据库--> 服务器--> 浏览器
 *  
 *  请求转发：
 *  	作用：实现多个Servlet联动操作处理请求，这样避免代码冗余，让Servlet的职责更加明确
 *  
 *  	使用：req.getRequestDispatcher("要转发的地址").forward(req, resp);
 *  		地址：相对路径，直接书写Servlet的别名即可
 *  
 *  	特点：一次请求，浏览器地址栏信息不改变，
 *  	
 *  	注意：请求转发后直接 return 结束即可。(其他的Servlet已经响应了)
 *  
 *request对象的作用域：
 *  	作用：
 *  		解决了一次请求内的不同Servlet的数据(请求数据+其他数据)共享问题
 *  	作用域：
 *  		基于请求转发，一次请求中的所有Servlet共享
 *  	注意：
 *  		使用request对象进行数据流转，数据只在一次请求内有效
 *  	特点：
 *  		服务器创建
 *  		每次请求都会创建
 *  		生命周期一次请求
 *  重定向：
 *  	问题：
 *  		如果当前的请求，Servlet无法进行处理怎么办？
 *  		如果使用请求转发，造成表单数据重复提交怎么办？
 * 		解决：
 * 			解决了表单重复提交的问题，以及当前Servlet无法处理的请求问题
 * 			使用重定向
 * 		使用：
 * 			response.sendRedirect("路径");
 * 			示例：resp.sendRedirect("/study_02_Login/main");
 * 			本地路径为：URI
 * 			网络路径为：定向资源的URL信息
 * 		特点：
 * 			俩次请求，俩个request对象
 * 			浏览器地址栏信息改变
 * 		时机：
 * 			如果请求中有表单数据，而数据又比较重要，不能重复提交，建议使用重定向
 * 			如果请求被Servlet接收后，无法进行处理，建议使用重定向定位到可以处理的资源
 * 
 * 解决主页面用户名显示为null的问题：
 * 		原因:
 * 			因为在用户登录成功后，使用重定向显示主页面，俩次请求，而用户的信息在第一次请求中，
 * 			第二次请求中，没有用户数据，所以显示为null
 * 		解决：
 * 			使用session
 *使用ServletContext 对象完成网页计数器
 *		在用户登录校验中创建计数器，每次自增，然后存储到ServletContext对象中
 *		在主页面里取出计数器数据显示给用户即可
 *
 * @author xujinshan361@163.com
 *
 */
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		String uname = req.getParameter("uname");
		// 浏览器解析的数据是GBK格式，需要转换成utf-8(后台服务器) --> 不服分请求方式
//		uname = new String(uname.getBytes("iso8859-1"),"utf-8");  // 使用String进行数据的重新编码
		String pwd = req.getParameter("pwd");
		
		System.out.println(uname+"--->"+pwd);   // 如果是null，则键名有问题
		
		// 处理请求信息
		// 获取业务层对象
		LoginService ls = new LoginServiceImpl();
		User u = ls.checkLoginService(uname, pwd);
		System.out.println(u);
		// 响应处理结果
		if(u != null) {
			// 创建CooKies信息，实现三天免登陆
			// 由于Cookie 信息是在请求头中，不安全，可以使用uid
			Cookie c = new Cookie("uid",""+u.getUid());
			//设置Cookie的有效期
			c.setMaxAge(3*24*3600);
			c.setPath("/study_02_Login/ck");
			// 添加Cookie信息
			resp.addCookie(c);
			
//			resp.getWriter().write("登录成功");
			// 使用请求转发，如果刷新会导致表单数据重新提交
//			req.getRequestDispatcher("main").forward(req, resp);
			
			// 创建网页计数器
			// 获取计数器数据
			ServletContext sc = this.getServletContext();
			if(sc.getAttribute("nums") != null) {
				int nums = Integer.parseInt((String) sc.getAttribute("nums"));
				//计数器自增
				nums+=1;
				//再次存储到ServletContext对象中
				sc.setAttribute("nums", nums);
			}else {
				sc.setAttribute("nums", 1);
			}
			// 将数据存储到session对象中
			 HttpSession hs = req.getSession();
			 hs.setAttribute("user", u);
			 
			// 使用重定向
			// 俩次请求，第二次相当于直接请求main，刷新不会在提交表单的数据
			resp.sendRedirect("/study_02_Login/main");   // 常规写法
//			resp.sendRedirect("main");  // 非常规写法
			
			
		}else {
//			resp.getWriter().write("登录失败");
//			resp.getWriter().write("<html>");
//			resp.getWriter().write("<head>");
//			
//			resp.getWriter().write("</head>");
//			
//			resp.getWriter().write("<body>");
//			resp.getWriter().write("<form action='login' method='get'>");
//			resp.getWriter().write("用户名：<input type='text' name='uname' value=''/> <br/>");
//			resp.getWriter().write("密码：<input type='password' name='pwd' value=''/> <br/>");
//			resp.getWriter().write("<input type='submit' value='登录'/> <br/>");
//			resp.getWriter().write("</form>");
//			resp.getWriter().write("</body>");
//			resp.getWriter().write("</html>");
			
			// 使用request对象实现不同Servlet的数据流转
			// 登录不成功，在请求行中添加新的键值对，以便请求转发的时候进行判断
			req.setAttribute("str", "用户名或密码错误");  
			// 使用请求转发
			req.getRequestDispatcher("page").forward(req, resp);
//			System.out.println("哈哈");
			return;
		}
	}
}
