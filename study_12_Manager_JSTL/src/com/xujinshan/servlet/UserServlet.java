package com.xujinshan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.xujinshan.pojo.User;
import com.xujinshan.service.UserService;
import com.xujinshan.service.impl.UserServiceImpl;

/**
 * Servlet重定向路径总结：
 * 		相对路径：
 * 			从当前请求的路径查找资源的路径
 * 			相对路径如果Servlet中的别名中包含目录，会造成重定向资源查找失败
 * 		绝对路径：
 * 			/虚拟项目名/资源路径
 * 			第一个 /  表示服务器根目录
 * Servlet请求转发：
 * 			/表示项目根目录
 * 			req.getRequestDispatcher("/资源路径").forward(req, resp);
 * 
 * @author xujinshan361@163.com
 *
 */
public class UserServlet extends HttpServlet {
	
	// 声明日志对象
	Logger logger = Logger.getLogger(UserServlet.class);
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
		}else if("pwd".equals(operation)) {
			// 调用密码修改功能
			userChangePwd(req,resp);
		}else if("showUser".equals(operation)) {
			// 显示所有用户信息功能
			userShow(req,resp);
		}else if("register".equals(operation)) {
			// 调用注册功能
			userReg(req,resp);
		}else if("out".equals(operation)) {
			// 调用退出功能
			userOut(req,resp);
		}else {
			logger.debug("没有找到对应的操作符："+operation);
//			System.out.println("没有找到对应的操作符："+operation);
		}
	}
	
	// 注册用户
	private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取请求信息
		String uname= req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		String sex = req.getParameter("sex");
		int age = req.getParameter("age")!=""?Integer.parseInt(req.getParameter("age")):0;
		String birth = req.getParameter("birth");
		System.out.println(birth);
		// 将日期格式为24/05/2018 通过字符串拼接成为格式 2018-05-24
		String[] bs = null;
		if(birth != "") {
			bs = birth.split("/");
			birth=bs[2]+"-"+bs[0]+"-"+bs[1];
		}
		User u = new User(0,uname,pwd,sex,age,birth);
		// 处理请求信息
		// 调用业务层处理
		int index = us.useRegService(u);
		System.out.println(index);
		// 响应处理结果
		if(index>0) {
			// 获取session对象
			HttpSession hs =req.getSession();
			hs.setAttribute("flag", 2);
			// 重定向
			resp.sendRedirect("/study_12_Manager_JSTL/login.jsp");
			return;
		}
	}

	// 显示所有用户信息
	private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 处理请求
		// 调用service
		List<User> lu = us.userShowService();
		if(lu != null) {
			// 将查询的用户数据存储到request对象
			req.setAttribute("lu", lu);
			req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
			return ;
		}
	}

	// 用户修改密码
	private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取数据
		String newPwd = req.getParameter("newPwd");
		// 从session中获取用户的信息
		User u= (User)req.getSession().getAttribute("user");
		int uid = u.getUid();
		// 处理请求
		// 调用service 处理
		int index =us.userChangePwdService(newPwd,uid);
		if(index > 0) {
			// 获取session对象
			HttpSession hs =req.getSession();
			hs.setAttribute("flag", 1);
			// 重定向到登录页面
			resp.sendRedirect("/study_12_Manager_JSTL/login.jsp");
			return ;
		}
	}
	
	// 用户退出
	private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 获取session对象
		HttpSession hs =req.getSession();
		// 强制销毁session 
		hs.invalidate();
		// 重定向到登录页面
		resp.sendRedirect("/study_12_Manager_JSTL/login.jsp");
	}
	
	// 处理登录
	private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		// 获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		// 处理请求信息
		// 获取service层对象
	
		// 校验
		User u = us.checkUserLoginService(uname, pwd);
	
		
		if (u != null) {        // 登录成功
			// 获取session对象
			HttpSession hs = req.getSession();
			// 将用户数据存储到session中
			hs.setAttribute("user", u);
			// 重定向
			resp.sendRedirect("main/main.jsp");
			return ;
		}else {    // 登录失败
			// 添加表示符到request中
			req.setAttribute("flag", 0);
			// 请求转发
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
			return ;
		}
		// 响应处理结果
		// 直接响应
		// 请求转发
	
	}
}
