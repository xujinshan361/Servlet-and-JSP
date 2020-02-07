<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'my.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 声明JavaScript代码域 -->
	<!--  
	Ajax 学习：
		1.Ajax的概念
			局部刷新技术，不是一门新技术，是多种技术的组合，是浏览器端的技术
		2.Ajax的作用
			实现在当前结果页中显示其他请求的响应内容
		3.Ajax的使用
			Ajax的基本流程
				// 创建Ajax引擎对象
				// 复写onreadystatechange函数
					// 判断Ajax状态码
						// 判断响应状态码
							// 获取响应内容(响应内容的格式)
								// 普通字符串 ：获取方式responseText
								// JSON(重点)	:获取方式responseText
									其实就是讲述数据按照JSON的格式拼接好的字符串，方便使用eval方法，
									将接收的字符串数据直接转换为JS的对象
									
									JSON格式：
										var 对象名={
											属性名：属性值 ,
											属性名：属性值 ,
											......
										}
										
								// XML数据：获取方式：responseXML ，返回document对象
									通过document对象将数据从XML中获取出来
									
						// 处理响应内容(JS 操作文档结构)
				// 发送请求
					// get请求：
						get的请求实体拼接在URL后面，？号隔开就好
						ajax.open("get","url");
						ajax.send(null);
					// post请求
						post请求有单独的实体
						ajax.open("post", "url");
						ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
						ajax.send(请求实体)
			Ajax的状态码
				Ajax状态码
					readyState:0,1,2,3,4
						4:表示响应内容被成功接收
				响应状态码
					status
					200：一切OK
					404：资源为找到
					500：内部服务器错误
			Ajax的异步和同步
				
	-->
	
	<script type="text/javascript">
		function getData(){
			// 创建Ajax引擎对象
			var ajax;
			if(window.XMLHttpRequest){ // 火狐
				ajax=new XMLHttpRequest();
			}else if(window.ActiveXObject){		// ie
				ajax=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			// 复写onreadystatechange函数
			ajax.onreadystatechange=function(){
				// 判断Ajax 状态码
				if(ajax.readyState==4){
						// 判断响应状态码
						if(ajax.status==200){
						// 获取响应内容
						var result=ajax.responseText
						// 处理响应内容
						// 获取元素对象
						var showdiv=document.getElementById("showdiv");
						// 修改元素内容
						showdiv.innerHTML=result;
					}else if(ajax.status==404){
						// 获取元素对象
						var showdiv=document.getElementById("showdiv");
						// 修改元素内容
						showdiv.innerHTML="请求资源不存在!";
					}else if(ajax.status==500){
						// 获取元素对象
						var showdiv=document.getElementById("showdiv");
						// 修改元素内容
						showdiv.innerHTML="服务器繁忙";
					}
				}else {
					// 获取元素内容
					var showdiv=document.getElementById("showdiv");
					// 修改元素内容
					showdiv.innerHTML="<img src='img/2.gif' width='200px' height='100px'/>";
				}
			}
			// 发送请求
			ajax.open("get", "ajax",true);  // 请求方式和请求地址
			ajax.send(null); // 火狐需要传入参数null， ie可以传也可以不传
			alert("哈哈");
			
			/*
			readyState值表示的含义：
				0：表示XMLHTTPRequest已建立，但是还未初始化，这时尚未调用open方法
				1：表示open方法已经被调用，但未调用send方法(已创建，未发送)
				2：表示send方法已经调用，其他数据未知
				3：表示请求已经成功发送，正在接收数据
				4：表示数据已经成功接收
			Http状态码
				200：请求成功
				404：请求资源未找到
				500：内部服务器错误
			*/
		}
	
	</script>
	<style type="text/css">
		#showdiv{
			border:solid 1px;
			width:200px;
			height:100px;
		}
	</style>
  </head>
  
  <body>
    <h3>欢迎登陆403峡谷</h3> <hr>
    <input type="button" value="测试" onclick="getData()"/>
    <div id="showdiv"></div>
  </body>
</html>
