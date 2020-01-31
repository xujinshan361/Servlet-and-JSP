package com.xujinshan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * service 方法和 doGet 方法和doPost方法的区别
 * 		service方法：	
 * 			不管是get方式还是post方式的请求，如果Servlet类中有service方法，则优先调用service方法
 * 		doGet 方法：
 * 			在没有service方法的情况下，如果是get方式的请求所调用的处理请求的方法
 * 		doPost 方法：
 * 			在没有service方法的情况下，如果是post方式的请求所调用的处理请求的方法。
 * 注意：
 * 		如果在覆写的service方法中调用父类的service方法(super.service(req,resp))
 * 		则service方法处理完成后，会再次根据请求方式响应doGet或doPost方法(如果没有重写doGet或doPost则会报405)
 * 		一般情况下，不在覆写的service方法中调用父类的service方法，避免出现405错误
 * 		如果调用了父类的service方法，则一定需要写doGet或doPost方法
 * 
 * Servlet的常见错误
 * 
 * 		404错误：资源未找到
 * 			原因一：在请求地址中的Servlet的别名书写错误
 * 			原因二：虚拟项目名称拼写错误
 * 
 * 		500错误：内部服务器错误
 * 			错误一：java.lang.ClassNotFoundException: com.xujinshan.servlet.ServletMethod
 * 			解决：在web.xml 中效验servlet类的全限定路径是否拼写错误
 * 			
 * 			错误二：因为service方法体的代码执行错误导致的
 * 			解决：根据错误提示对service方法体中的代码进行错误更改
 * 
 * 		405错误：请求方式不支持
 * 			原因：请求方式和Servlet中的方法不匹配所造成的
 * 			解决：尽量使用service方法进行请求处理，并且不要在service方法中调用父类的service方法
 * 
 * @author xujinshan361@163.com
 *
 */
public class ServletMethod extends HttpServlet {

	/*@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是service");
		//super.service(req,resp);
	}
	*/
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是doGet方法");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("我是doPost方法");
	}
	
}
