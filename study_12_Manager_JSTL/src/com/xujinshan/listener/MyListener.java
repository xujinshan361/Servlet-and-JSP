package com.xujinshan.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 监听器，显示网页用户数
 * @author xujinshan361@163.com
 *
 */
public class MyListener implements HttpSessionListener,ServletContextListener{

	// 监听session对象的创建 --->人数自增
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// 获取ServletContext对象
		ServletContext sc = se.getSession().getServletContext();
		// 获取在线统计人数的变量
		int count = (int)sc.getAttribute("count");
		sc.setAttribute("count", ++count);
	}
	// 监听session对象的销毁--->人数自减
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// 获取ServletContext对象
		ServletContext sc = se.getSession().getServletContext();
		// 获取在线统计人数的变量
		int count = (int)sc.getAttribute("count");
		sc.setAttribute("count", --count);
	}
	// application 对象销毁
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	// application 对象的初始化
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 获取application
		ServletContext sc = sce.getServletContext();
		// 在application 对象中存储变量用来统计在线人数
		sc.setAttribute("count", 0);
	}
}
