package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 的生命周期：
 * 		1.从第一次调用到服务器关闭(不配置load-on-startup)
 * 		2.如果Servlet在web.xml 中配置了 load-on-startup ，生命周期为从服务器启动到服务器关闭。
 * 注意：
 * 	init方法是对Servlet 进行初始化的一个方法，会在Servlet第一次加载进存储时候执行。
 * 	destroy方法是在Servlet被销毁的时候执行，也就是服务器关闭时候。
 * @author xujinshan361@163.com
 *
 */
public class ServletLife extends HttpServlet {
	
	// 初始化方法，在Servlet第一次加载内容的时候被调用
	@Override
	public void init() throws ServletException {
		System.out.println("ServletLife 初始化完成");
	}
	
	// service 方法，真正处理请求的方法
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("servlet life");
		System.out.println("servlet life");
	}
	
	//销毁的时候调用，也就是服务器被关闭的时候。
	@Override
	public void destroy() {
		System.out.println("我被销毁了。。。。");
	}
}
