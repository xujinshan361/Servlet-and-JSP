package com.xujinshan.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器学习：
 * 		作用：
 * 			监听作用域对象request session application 的创建销毁和内容的改变
 * 		使用：
 * 			创建了一个实现了指定接口的java类
 * 				监听request
 * 					监听request--->ServletRequestListener----> 监听request对象的创建和销毁
 * 						requestDestroyed(ServletRequestEvent sre)    // 销毁
 * 						requestInitialized(ServletRequestEvent sre)  // 创建
 * 						注意：
 * 							形参可以获取监听的request对象
 * 							sre.getServletRequest()
 * 						
 * 					监听request--->ServletRequestAttributeListener----> 监听request作用域数据的变更
 * 						attributeAdded(ServletRequestAttributeEvent srae)		// 监听request作用域数据的添加
 * 						attributeRemoved(ServletRequestAttributeEvent srae) 	// 监听request作用域数据的删除
 * 						attributeReplaced(ServletRequestAttributeEvent srae)	// 监听request作用域数据的修改
 * 						注意：
 * 							形参可以获取被监听的数据
 * 							srae.getName()  // 获取监听数据的键
 * 							srae.getValue() // 获取监听数据的值
 * 				监听session
 * 					监听session--->HttpSessionListener-----> 监听session对象的创建和销毁
 * 						sessionCreated(HttpSessionEvent se)		// 创建
 * 						sessionDestroyed(HttpSessionEvent se)   // 销毁
 * 						注意：
 * 							形参可以获取被监听的session对象
 * 							se.getSession();
 * 					监听session--->HttpSessionAttributeListener---->监听session中数据的变更
 * 						attributeAdded(HttpSessionBindingEvent se) 		// 监听session中数据的添加
 * 						attributeRemoved(HttpSessionBindingEvent se)	// 监听session中数据的删除
 * 						attributeReplaced(HttpSessionBindingEvent se)	// 监听session中数据的修改
 * 						注意：
 * 							形参可以获取当前被监听的数据
 * 							se.getName()   // 获取监听数据的键
 * 							se.getvalue()	// 获取监听数据的值
 * 				监听application
 * 					监听application--->ServletContextListener---> 监听application对象的创建和销毁
 * 						contextDestroyed(ServletContextEvent sce)   	// 监听application对象的销毁	服务器关闭
 * 						contextInitialized(ServletContextEvent sce)		// 监听application对象的初始化    服务器启动
 * 						注意：
 * 							形参可以获取当前application 对象
 * 							sec.getServletContext();
 * 					监听application-->ServletContextAttributeListener--->监听application数据的变更
 * 						attributeAdded(ServletContextAttributeEvent scae)		// 监听application数据的添加
 * 						attributeRemoved(ServletContextAttributeEvent scae)		// 监听application数据的删除
 * 						attributeReplaced(ServletContextAttributeEvent scae)	// 监听application数据的变更
 * 						注意：	
 * 							形参可以获取当前监听的数据
 * 							scae.getName();		// 获奖数据的键
 * 							scae.getValue();	// 获取数据的值
 * 			在web.xml 中配置监听类
 * 				<listener>
 * 					<listener-class>com.xujinshan.listener.MyListener</listener-class>
 * 				</listener>
 * 			案例：
 * 				统计当前在线人数
 * 				统计网页浏览器次数
 * @author xujinshan361@163.com
 *
 */
public class MyListener implements ServletRequestListener, ServletRequestAttributeListener, 
	HttpSessionListener, HttpSessionAttributeListener, ServletContextListener, ServletContextAttributeListener{

	// request对象销毁
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("我被销毁了");
	}
	// request对象创建
	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("我被创建了");
	}
	// 监听request作用域数据的添加
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("request中增加了一条数据---"+srae.getName()+":"+srae.getValue());
	}
	// 监听request作用域数据的添加
	@Override
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}
	// 监听request作用域数据的修改
	@Override
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		
	}
	/*-----------------------------------------------------------------------*/
	// 监听session对象创建
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("session被创建了");
	}
	// 监听session对象的销毁
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session被销毁了");
	}
	// 监听session中数据的添加
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		System.out.println("session 中增加了一条数据---"+se.getName()+":"+se.getValue());
	}
	// 监听session中数据的删除
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	// 监听session中数据的更改
	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}
	
	/*----------------------------------------------------------------------------*/
	// application 对象被销毁
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("application 对象被销毁了");
	}
	// application 对象被创建
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("application 对象被创建");
	}
	// 监听application 数据的添加
	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		System.out.println("application中存储了数据--"+scae.getName()+":"+scae.getValue());
	}
	// 监听application 数据的删除
	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		
	}
	// 监听application数据的变更
	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		
	}
}
