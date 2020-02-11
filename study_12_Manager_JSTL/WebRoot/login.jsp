<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录后台管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>

<script language="javascript">
	$(function(){
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    })  
});  
</script> 

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
     
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
     <br/>  
     <!-- 使用JSTL和EL表达式完成提示语 -->
     <c:choose>
     	<c:when test="${flag==0}">
     		<div style="text-align:center;">
		     	<span style="font-size:15px;color:green;fond-weight:bold">用户名或密码错误</span>
		     </div>
     	</c:when>
     	<c:when test="${flag==1}">
     		<div style="text-align:center;">
		     	<span style="font-size:15px;color:green;fond-weight:bold">密码修改成功</span>
		     </div>
     	</c:when>
     	<c:when test="${flag==2}">
     		<div style="text-align:center;">
		     	<span style="font-size:15px;color:green;fond-weight:bold">注册成功</span>
		     </div>
     	</c:when>
     	
     </c:choose>
     <!-- 移除session中的flag标记 -->
     <c:remove var="flag" scope="session" />
    <div class="loginbox loginbox1">
    <form action="user" metho="post">
    <input type="hidden" name="operation" value="login"/>
    <ul>
    	<li></li>
    	<!--  鼠标点击一下就变成空的
	    <li><input name="uname" type="text" class="loginuser" value="admin" onclick="JavaScript:this.value=''"/></li>
	    <li><input name="pwd" type="text" class="loginpwd" value="密码" onclick="JavaScript:this.value=''"/></li>
	    -->
	    <!-- 鼠标点击不会变成空，需要写入东西 -->
	    <li><input name="uname" type="text" class="loginuser" placeholder="用户名"/></li>
	    <li><input name="pwd" type="password" class="loginpwd" " placeholder="密码"/></li>
	    <li class="yzm">
	    <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite> 
	    </li>
	    <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location='main.html'"  /><label><a href="user/reg.jsp">注册</a></label><label><a href="#">忘记密码？</a></label></li>
	    
	    </ul>
    </form>
    
    </div>
    
    </div>
    
    
    <div class="loginbm">版权所有，仅仅用于学习。邮箱：xujinshan361@163.com</div>
	
    
</body>

</html>
