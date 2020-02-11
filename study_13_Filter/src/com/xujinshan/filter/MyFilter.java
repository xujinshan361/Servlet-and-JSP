package com.xujinshan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 过滤器学习:
 * 		问题：
 * 			Servlet的作用是针对浏览器发起的请求，进行请求的处理，通过Servlet技术我们可以灵活的进行请求的处理
 * 			但是我们不但要对请求记性处理，还要对服务器的资源进行统一的管理，比如请求编码格式的统一设置，资源的统一分配等。
 * 		作用：
 * 			对服务器接收的请求资源和响应给浏览器的资源进行管理
 * 			保护servlet
 * 		使用：
 * 			创建一个实现了Filter接口的普通java类
 * 			覆写接口方法
 * 				init方法： 服务器启动的时候执行，资源初始化
 * 				doFilter方法：拦截请求的方法，在此方法中可以对资源实现管理
 * 					注意：
 * 						需要手动对请求资源进行放行
 * 						chain.doFilter(request, response);
 * 				destory方法：服务器关闭的时候执行
 * 
 * 			在web.xml 中配置过滤器
 * 			<filter>
 * 				<filter-name>myFilter</filter-name>
 * 				<filter-class>com.xujinshan.filter.MyFilter</filter-class>
 * 			</filter>
 * 			<filter-mapping>	
 * 				<filter-name>myFilter</filter-name>
 * 				<url-pattern>/*</url-pattern>
 * 			</filter-mapping>
 * 			注意：
 * 				url-pattern:/* 		
 * 					表示拦截所有的请求
 * 				url-pattern:*.do 	
 * 					表示所有以.do 结尾的请求，一般用来进行模块拦截处理
 * 				url-pattern:/ts.do
 * 					表示拦截指定的url的请求，针对某个Servlet的请求进行拦截，保护servlet
 * 		过滤器的生命周期
 * 			服务器启动到服务器关闭
 * 
 * 		总结：
 * 			过滤器由程序员声明和配置，服务器根据请求中的uri信息调用
 * 		执行：
 * 			浏览器发起请求到服务器，服务器接收到请求后，根据URI信息在web.xml 中找到对应的过滤器，
 * 			执行doFilter方法，该方法对此次请求进行处理后如果符合要求则放行，放行后
 * 			如果还有符合要求的过滤器则继续进行过滤，直到执行对应的servlet进行请求处理，
 * 			servlet对请求处理完毕后，也就是service方法结束后，还需要继续返回相应的doFilter 方法继续执行
 * 
 * 		案例：
 * 			统一编码格式设置
 * 			session管理
 * 			权限管理
 * 			资源管理(统一水印等)
 * 			
 * @author xujinshan361@163.com
 *
 */
public class MyFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("MyFilter.init(我被初始化了)");
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("MyFilter.doFilter(我被执行 了)");
		// 设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 判断session
		HttpSession hs = ((HttpServletRequest)request).getSession();
		if(hs.getAttribute("user") == null) {
			((HttpServletResponse)response).sendRedirect("/a/login.jsp");
		}else {
			// 放行
			chain.doFilter(request, response);
		}
		System.out.println("MyFilter.doFilter(我被执行 了2)");
	}
	@Override
	public void destroy() {
		System.out.println("MyFilter.destroy(我被销毁了)");
	}
}
