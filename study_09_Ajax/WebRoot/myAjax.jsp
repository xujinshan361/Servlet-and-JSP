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
	<script type="text/javascript">
		function getData(){
			// 创建Ajax引擎对象
			var ajax;
			if(window.XMLHttpRequest){ // 火狐
				ajax=new XMLHttpRequest();
			}else if(window.ActiveXObject){		// ie
				ajax=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			// 复写onreadystatement函数
			ajax.onreadystatechange=function(){
				// 判断Ajax 状态码
				if(ajax.readyState==4){
						// 判断响应状态码
						if(ajax.status==200){
						// 获取响应内容
						var result=ajax.responseText
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
				}
			}
			// 发送请求
			ajax.open("get", "ajax");  // 请求方式和请求地址
			ajax.send(null); // 火狐需要传入参数null， ie可以传也可以不传
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
