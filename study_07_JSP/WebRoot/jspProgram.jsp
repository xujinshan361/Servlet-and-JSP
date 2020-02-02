<%@ page language="java" import="java.util.*,java.lang.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page session="true" %>
<%@ page isErrorPage="true" %>
<%@ page errorPage="error.jsp" %>

<%-- JSP 的注释格式  --%>

<%-- 
JSP 学习：
	概念：
		JSP全名为 java server pages ，中文名叫java 服务器页面，其根本是一个简化的servlet 设计
		由Sum Microsystems公司倡导、许多公司参与一起建立 一种动态网页技术标准
	
	特点：
		本质上还是servlet
		跨平台，一次编写处处运行
		组件跨平台
		健壮性和安全性
		
	JSP的访问原理：
		浏览器发起请求，请求JSP，请求被Tomcat服务器接收，执行JspServlet 
		将请求的JSP文件转译成对应的java文件(也就是servlet)
		然后执行转译好的java文件

	JSP 的三种注释：
		前端语言注释：
			会被转译，也会被发送，但是不会被浏览器执行
		java语言注释：
			会被转译，但是不会被Servlet执行
		JSP注释:
			不会被转译
	
	JSP的page指令：
		<%@page 属性名="属性值" 属性名="属性值" ……%>
		language：声明JSP要被转译的语言
		import：声明转译的java文件要导入的包，不同的包使用逗号隔开
		pageEncodeing:设置JSP 文件的数据编码格式
		contentType="text/html;charset=utf-8":设置JSP数据响应给浏览器时，浏览器的解析和编码格式
		session:设置转译的servlet 中是否开启session支持，默认开启，true表示开启，false表示关闭
		errorPage:设置JSP运行错误跳转的页面
		extends:设置JSP转译的java文件要继承的父类(全限定路径：包名+类名)
		作用：
			配置JSP文件的转译的相关参数。
			
	JPS的局部代码块：
		特点：
			局部代码块中声明的java代码会被原样转译到JSP对应的servlet文件的  _JspService 方法中
			代码块中声明的变量都是局部变量
		使用：
			<% java 代码 %>
		缺点：
			使用局部代码块在JSP中进行逻辑判断，书写麻烦，阅读困难		
		开发：
			Servlet进行请求逻辑处理，使用JSP进行页面展现
	
	JSP的全局代码块：
		特点：
			声明的java代码作为全局代码转译到对应的servlet类中
		使用：
			<%!  全局代码块  %>
		注意：
			全局代码块声明，局部代码块调用，一定要找好位置，
	
	JSP的脚本段语句
		特点：
			帮助我们快速的获取变量或者方法的返回值作为数据响应给浏览器
		使用：
			<%= 变量名或者方法  %>	
		注意：
			不要在变量名或者方法后使用分号	
		位置：
			除JSP语法要求以外的任意位置
	
	JSP的静态引入和动态引入
		静态引入：
			<%@ include file="要引入的JSP文件的相对路径" %>
			特点：
				会将引入的JSP文件和当前JSP文件转译成一个java(Servlet)文件使用。
				在网页中也就显示了合并后的显示效果
			注意：
				静态引入的JSP文件不会单独转译成java(Servlet)文件 --> 需要通过静态引入去访问，不是直接访问
				当前文件和静态引入的JSP文件中不能够使用java代码块声明同名变量。
		动态引入：
			<jsp:include page="要引入的JSP文件的相对路径"></jsp:include>
			特点：
				会将引入的JSP文件单独转译，在当前文件转译好的java文件中调用引入的JSP文件的转译文件
				在网页中显示合并后的显示效果
			注意：
				动态引入运行文件中声明同名变量(本质会转译成俩个java(Servlet)文件)
		优点：
			降低JSP代码的冗余，便于维护升级
		
	JSP 的转发标签 forward
		使用：
			<jsp:forward page="">要转发的JSP文件的相对路径</jsp:forward>	
		特点：
			一次请求
			地址栏信息不改变
		注意：
			在转发标签的俩个标签中间，除了写<jsp:param value="aaa" name="str"/>子标签不会报错，其他任意字符都会报错
			<jsp:param value="aaa" name="str"/>
			name：属性为附带的数据的键名
			value：为附带的数据内容
			注意：会将数据以？的形式拼接在转发路径的后面
			
	JSP的九大内置对象
		内置对象：
			JSP 文件在转译成对应的Servlet文件的时候自动生成的并声明的对象
			在JSP页面中直接使用即可
		注意：
			内置对象在JSP页面中使用，使用局部代码块或者脚本段语句来使用，不能在全局代码块中使用
		内容:九个
			pageContext
				页面上下文对象，封存了其他内置对象。封存了当前JSP的运行信息
				注意:
					每个JSP文件单独拥有一个pageContext对象
					作用域：当前页面
			request
				封存当前请求数据的对象，由Tomcat服务器创建，一次请求
			session
				此对象用来存储用户的不同请求的共享数据的。一次会话
			application
				ServletContext对象，一个项目只有一个，存储用户共享数据的对象，以及完成其他操作，项目内
			response
				响应对象，用来响应请求处理结果给浏览器的对象，设置响应头，重定向
			out
				响应对象，JSP内部使用，带有缓冲区的响应对象，效率高于response对象
			page
				代表当前JSP的对象， 相当于java中的this
			exception
				异常对象，存储了当前运行的异常信息
				使用此对象，需要在page指定中使用属性isErrorPage="true"开启
			config
				也就是ServletConfig，主要是用来获取web.xml 中的配置数据，完成一些初始化数据的读取
			
	四个作用域：
		pageContext:当前页面，解决了当前页面内数据共享问题，获取其他内置对象
		request：一次请求，一次请求的Servlet的数据共享，通过请求转发将数据流转给下一个Servlet
		session：一次会话，一个用户的不同请求的数据共享，将数据从一次请求流转给其他请求
		application:项目内，不同的用户的数据共享问题，将数据从一个用户流转给其他用户
		作用：
			数据流转
			
	JSP的路径
		在JSP中资源路径可以使用相对路径完成跳转	，但是：
			问题一：资源的位置不可随意更改
			问题二：需要使用 ../ 进行文件夹的跳出，使用比较麻烦
		使用绝对路径(重点)：
			/虚拟项目名/资源名或者项目资源路径 
			例如：
				<a href="/sutdy_07_JSP/a/b/b.jsp">b.jsp</a>
			注意：
				在JSP中资源的第一个/ 表示的是服务器根目录，相当于：localhost:8080		
		使用JPS中自带的全局路径声明(eclipse 创建默认不带这个，MyEclipse创建默认带这个)：
			<%
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			%>
			
			<base href="<%=basePath%>">
			作用：
				给资源前面添加项目路径：http://localhost:8080/虚拟项目名/
				
					
					
 --%>
 <html>
 	<head>
 		<title>JSP 基本语法学习</title>
 		<meta charset='utf-8'/>
 	</head>
 	<body>
 	<h3>JSP 基本语法学习</h3>
 	<hr/>
 	<!-- 局部代码块 -->
 	<%
 		// 声明java代码域 -- 局部代码块声明
 		String str = "JSP 中使用逻辑校验很难受";
 		int a = 2;
 		if (a>3){
 	 %>
 		<b>JSP 学习很简单</b>
 		<%} else{ %>
 			<i><%=str %></i>
 			
 		<%test();} %>
 		
 	<!-- 全局代码块 -->
 	<%!
 		int b = 5;
		public void test(){
			System.out.println("我是全局代码块声明！");
		}
 	 %>
 	 <!-- JSP 的静态引入 -->
 	 <%@ include file="includeStatic.jsp" %>
 	 
 	 <!-- JSP 的动态引入 -->
 	 <jsp:include page="includeDynamic.jsp"></jsp:include>
 	 
 	 <%-- 
 	 <!-- JSP 的转发forward标签  -->
 	 <jsp:forward page="forward.jsp">
 	 	<jsp:param value="aaa" name="str"/>
 	 </jsp:forward> 
 	 --%>
 	 
 	 <!-- JSP的九大内置对象的学习 -->
 	 <%
 	 	// 获取请求数据
 	 	String s =request.getParameter("str");     // 直接在url后面拼接 ?str=666,即可获取数据
 	 	request.getAttribute("str");
 	  %>
 	  <%=s %>   <!-- 输出数据到浏览器 -->
 	  <%
 	  	// response.sendRedirect("forward.jsp");
 	   %>
 	   <!-- JSP的路径 -->
 	   <a href="a/a.jsp">a.jsp</a>
 	   <a href="a/b/b.jsp">b.jsp</a>
 	</body>
 </html>