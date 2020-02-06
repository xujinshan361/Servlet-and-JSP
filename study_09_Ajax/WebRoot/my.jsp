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
			//获取元素对象
			var showdiv=document.getElementById("showdiv");
			// 修改元素内容
			showdiv.innerHTML="哈哈，今天的雾霾真的是新鲜"
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
