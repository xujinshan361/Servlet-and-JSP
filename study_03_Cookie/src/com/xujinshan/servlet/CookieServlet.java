package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie 学习：
 * 		作用：解决了发送不同请求的数据共享问题
 * 		使用：
 * 		Cookie 的创建和存储
 * 			// 创建Cookie对象
 * 				Cookie c = new Cookie(String name, String value);
 * 			// 设置Cookie(可选)
 * 				// 设置有效期
 * 				c.setMaxAge(int seconds);
 * 				//设置有效路径
 * 				c.setPath(String uri);
 * 			// 响应Cookie 信息给客户端
 * 				resp.addCookie(c);
 * 		Cookie的获取
 * 			// 获取Cookie 信息数组
 * 				Cookie[] cks = req.getCookies();
 * 			// 遍历数组获取 Cookie信息
 * 				使用for循环遍历  --> 需要进行判断是否为空，防止空指针异常
 * 				for(Cookie c :cks){}
 * 		注意：
 * 			一个Cookie对象存储一条数据，多条数据，可以多创建几个Cookie对象存储
 * 		特点：
 * 			浏览器端的数据存储技术
 * 			存储的数据声明在服务器端
 * 			临时存储：存储在浏览器的运行内存中，浏览器关闭即失效
 * 			定时存储：设置了Cookie的有效期，存储在客户端的硬盘中，在有效期内符合路径要求的请求都会附带该信息
 * 			默认Cookie信息存储好之后，每次请求都会附带，除非设置有效路径
 * @author xujinshan361@163.com
 *
 */
public class CookieServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		
		// 获取请求信息
		
		// 处理请求信息
		
		// 响应处理结果
		// 使用Cookie进行浏览器端的数据存储
		// 创建Cookie对象
		Cookie c = new Cookie("mouse", "thinkpad");
		Cookie c2 = new Cookie("key", "student");
		// 设置Cookie
		// 设置Cookie的有效期
		c2.setMaxAge(3*24*3600);
		// 设置有效路径
		c2.setPath("/study_03_Cookie/gc");
		// 响应Cookie信息
		resp.addCookie(c);
		resp.addCookie(c2);
		
		// 直接响应
		resp.getWriter().write("Cookie学习");
		
		// 请求转发
		
		// 重定向
		
	}
}
