<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ajaxXML.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 声明JS代码域 -->
	<script type="text/javascript">
		function getXML(){
			// 创建Ajax对象
			var ajax;
			if(window.XMLHttpRequest){ // 火狐
				ajax=new XMLHttpRequest();
			}else if(window.ActiveXObject){		// ie
				ajax=new ActiveXObject("Msxml2.XMLHTTP"); 
			}
			// 复写onreadystatechange
			ajax.onreadystatechange=function(){
				// 判断ajax状态码
				if(ajax.readyState==4){
					// 判断响应状态码
					if(ajax.status==200){
						// 获取响应内容
						var doc = ajax.responseXML;
						// 处理响应内容
						// 获取元素对象
						alert(doc.getElementsByTagName("name")[0].innerHTML);
					}
				}
			}
			// 发送请求-->转给JSP，本质也是servlet
			ajax.open("get", "xml.jsp");
			ajax.send(null);
		}
		
	</script>
  </head>
  
  <body>
    <h3>Ajax XML数据格式学习</h3> <br>
    <hr>
    <input type="button" value="测试"  onclick="getXML()"/>
  </body>
</html>
