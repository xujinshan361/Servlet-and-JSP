<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- Ajax 请求 -->

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
		function ajaxReq(){
			// 获取用户请求数据
			var uname=document.getElementById("uname").value;
			var data ="name="+uname;
			// 创建Ajax引擎对象
			var ajax;
			if(window.XMLHttpRequest){ // 火狐
				ajax=new XMLHttpRequest();
			}else if(window.ActiveXObject){		// ie
				ajax=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			// 复写onreadystatechange函数
			ajax.onreadystatechange=function(){
				//判断Ajax状态码
				if(ajax.readyState == 4){
					// 判断响应状态码
					if(ajax.status == 200){
						// 获取响应内容
						var result = ajax.responseText;
						// 处理响应内容
						alert(result);
					}
				}
			}
				
			// 发送请求
				// get方式：请求实体拼接在URL后面
				// ajax.open("get","ajaxreq?"+data);
				// ajax.send(null);
				// post方式:请求实体需要单独转发
				
				ajax.open("post", "ajaxreq")
				// 加上这一句话，将提交内容以键值对提交
				ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				ajax.send("name=李四&pwd=345")
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
  <form enctype="application/x-www-form-urlencoded"></form><!-- Ajax.setRequestHeader 里面参数相同 -->
    <h3>欢迎登陆403峡谷</h3> <hr>
    <input type="text" name = "uname" id="uname" value=""/>
    <input type="button" value="测试" onclick="ajaxReq()"/>
    <div id="showdiv"></div>
  </body>
</html>
