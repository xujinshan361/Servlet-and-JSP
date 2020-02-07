package com.xujinshan.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.Address;
import com.xujinshan.pojo.User;

/**
 * EL表达式的学习：
 * 		问题：
 * 			在servlet进行请求处理后，使用作用域对象作为数据流转的载体，将数据流转给对应的JSP文件
 * 			那么怎么在JSP中获取作用域中的数据?
 * 		使用：
 * 			传统方式：在JSP页面中，使用java脚本段语句。
 * 			
 * 		什么是EL表达式
 * 			全称：Expression Language 。一种写法非常简洁的表达式，语法简单，易懂，便于使用。表达式语言的灵感来自于ECMAScript 和XPath表达式语言
 * 		EL表达式的作用：
 * 			作用：
 * 				让JSP书写起来更加的方便，简化在JSP中获取作用域或者请求数据的写法，也会搭配Jstl 来进行使用
 * @author xujinshan361@163.com
 *
 */
public class ELServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		// 处理请求信息
		System.out.println(uname + "--" + pwd);
		// 响应处理结果
			// 使用request作用域进行数据流转
				// 普通字符串
				req.setAttribute("str", "天气很清爽，很适合学习");
				// 对象类型
				User u= new User(1, "张三", "看电影", new Address("安徽","合肥","肥西县"));
				req.setAttribute("user", u);
				// 集合类型
					// List 集合
						// 存储普通字符串
						List<String> list = new ArrayList<String>();
						list.add("张三");
						list.add("李四");
						list.add("王五");
						// 存储对象
						User u2 = new User(2,"古丽娜","拍电影",new Address("新疆","乌鲁木齐","乌鲁木齐"));
						req.setAttribute("list", list);
						List<User> list2 = new ArrayList<>();
						list2.add(u2);
						req.setAttribute("list2", list2);
					// Map 集合
						// 存储普通字符
						Map<String,String> map = new HashMap<String,String>();
						map.put("a", "北京");
						map.put("b", "上海");
						req.setAttribute("map", map);
						// 存储对象
						Map<String, User> map2 = new HashMap<String, User>();
						map2.put("a", u);
						map2.put("b", u2);
						req.setAttribute("map2", map2);
			// 请求转发
			req.getRequestDispatcher("/el.jsp").forward(req, resp);
			return ;
	}
}
