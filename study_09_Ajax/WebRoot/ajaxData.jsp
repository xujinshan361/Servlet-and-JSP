<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!-- 
	需求分析：
		1.在当前页面显示查询结果，考虑使用Ajax
		2.创建ajax函数，
		3.调用ajax函数发送请求到UserServlet
		4.调用业务层获取对应的数据

 -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ajaxData.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- 声明JavaScript代码域 -->
<script type="text/javascript" src="js/ajaxUtils.js"></script>
<script type="text/javascript">
	// 获取数据
	function getData(){
		// 获取用户请求信息
		var name=document.getElementById("uname").value;	
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
					// 获取响应数据
					var result=ajax.responseText;
					eval("var u="+result);
					// 处理响应数据
					//获取table表格对象
					var ta=document.getElementById("ta");
					ta.innerHTML="";
					var tr=ta.insertRow(0);
					var cell0=tr.insertCell(0);
					cell0.innerHTML="编号";
					var cell1=tr.insertCell(1);
					cell1.innerHTML="名称";
					var cell2=tr.insertCell(2);
					cell2.innerHTML="价格";
					var cell3=tr.insertCell(3);
					cell3.innerHTML="位置";
					var cell4=tr.insertCell(4);
					cell4.innerHTML="描述";
					//插入新的行
					var tr=ta.insertRow(1);
					var cell0=tr.insertCell(0);
					cell0.innerHTML=u.uid;
					var cell1=tr.insertCell(1);
					cell1.innerHTML=u.uname;
					var cell2=tr.insertCell(2);
					cell2.innerHTML=u.price;
					var cell3=tr.insertCell(3);
					cell3.innerHTML=u.loc;
					var cell4=tr.insertCell(4);
					cell4.innerHTML=u.desc;	 
						
				}
			}
		}
		// 发送请求
		ajax.open("get", "user?name="+name);
		ajax.send(null);
	}
	
	function getData02(){
 		var name=document.getElementById("uname").value;
 		var data="name="+name;
 		myAjax("get","user",data,function(ajax){
	 		//获取响应数据
				var rs=ajax.responseText;
			//处理响应数据
				alert(rs);
 		});
 	}
</script>
  </head>
  
  <body>
  	<h3>Ajax JSON数据格式学习</h3>
  	<h3>欢迎访问英雄商店</h3>
  	<hr>
  	英雄名称：<input type="text" name="uname" value="" id="uname"/>
  	<input type="button" value="搜索" onclick="getData02()"/>
  	<hr>
  	<table border="1px" id="ta">
	  	<!--
	  	<tr>
	  		<td>编号</td>
	  		<td>名称</td>
	  		<td>价格</td>
	  		<td>位置</td>
	  		<td>描述</td>
	  	</tr>
	 	-->
  	</table>
  </body>
</html>
