<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%--
	JSTL标签库学习：
		什么是JSTL标签库
			JSTL是apache 对EL表达式的扩展(也就是说JSTL依赖于EL)，JSTL是标签语言，JSTL标签使用方便，它与JSP动作标签一样，
			只不过它不是JSP内置的标签，需要我们自己导包，以及指定的标签库。
			注意：
				如果使用MyEclipse 开发JavaWeb，那么在把项目发布到Tomcat时，会自动导入JSTL包，如果使用别的软件，需要自己导入JSTL包
		作用：
			用来提升在JSP页面的逻辑代码的编码效率，使用标签来替换逻辑代码的直接书写、高效、美观、整洁、易读。
			
		使用：
			JSTL的核心标签库(重点)
			一定需要引入：<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
			
			JSTL的格式化标签库
			JSTL的SQL标签库
			JSTL的函数标签库
			JSTL的XML标签库
		
		JSTL 的核心标签库
			1.导入jar包
			2.声明JSTL的标签库的引入(核心标签库)
				<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
			3.内容：
				基本标签：
					<c:out value="${str2}" default="嘿嘿"></c:out>
						数据可以为常量值，也可以是EL表达式
						作用：将数据输出给客户端，
					<c:set var="hello" value="hello pageContext" scope="page"></c:set>
						作用：存储数据到作用域对象中
							var：表示存储的键名
							value：表示存储的数据
							scope:表示存储的作用域对象：page request session application
					<c:remove var="hello" scope="request"/>
						作用：删除作用域中指定的键的数据
							var：表示要删除的键的名字
							scope：表示要删除的作用域(可选)
							注意：
								如果在不指定作用域的情况下使用该标签删除数据，会将四个作用域对象中符合要求的数据全部删除
							
				逻辑标签：
					<c:if test="${表达式}">
						前端代码
					</c:if>	
						作用：进行逻辑判断，相当于java代码的单分支判断
						注意：
							逻辑判断标签需要依赖于EL表达式，也就涉及到数据必须从作用域中获取
					<c:choose>
						<c:when test="">执行内容</c:when>
						<c:when test="">执行内容</c:when>
						.....
						<c:otherwise>执行内容</c:otherwise>
					</c:choose>
						作用：用来进行多条件的逻辑判断，类似java中的多分支语言
						注意：
							条件成立只会执行一次，都不成立则执行otherwise
							
				循环标签：
					<c:forEach begin="0" end="4" step="1" varStatus="vs">
						1111---${vs.index}--${vs.count}--${vs.first}--${vs.last}<br>
					</c:forEach>
										
					<c:forEach begin="1" end="4" step="2">
						循环体
					</c:forEach>
						作用：循环内容进行处理
						使用：
							begin：声明循环开始位置
							end:声明循环结束位置
							step:设置步长
							varStatus:声明变量记录每次循环的数据(角标、次数，是否是第一次循环，是否是最后一次循环)		
								注意：数据存储在作用域中，需要使用EL表达式获取
								例如：
									${vs.index}--${vs.count}--${vs.first}--${vs.last}
							items：声明要遍历的对象，结合EL表达式获取对象
							var:声明变量，记录每次循环的结果，存储在作用域中，需要使用EL表达式获取
							示例：
								<%
									ArrayList<String> list = new ArrayList<String>();
									list.add("a");
									list.add("b");
									list.add("c");
									request.setAttribute("list", list);
								 %>
								<c:forEach items="${list}" var="str">
									<i>${str}</i>
								</c:forEach>
--%>

<%
	request.setAttribute("str", "今天天气真好");
	//request.setAttribute("hello", "hello Request");
	request.removeAttribute("");   // 类似于 c:remove 
 %>
<!-- 基本标签学习 -->
<h3>基本标签学习</h3>
<c:out value="哈哈"></c:out>-------哈哈<br>
<c:out value="${str}"></c:out>-------${str}
<c:out value="${str2}" default="嘿嘿"></c:out><br>
<c:set var="hello" value="hello pageContext" scope="page"></c:set>
<c:set var="hello" value="hello request" scope="request"></c:set>
<c:set var="hello" value="hello session" scope="session"></c:set>
<c:set var="hello" value="hello application" scope="application"></c:set>
<c:out value="${requestScope.hello}"></c:out>
<c:remove var="hello" scope="request"/><br>
<c:out value="${hello}"></c:out>
<hr>
<!-- 逻辑标签学习 -->
<h3>逻辑标签学习</h3>
<!-- 传统方式 -->
<% 
	int a=4;
	if(a>3){
%>
<b>今天的天气有点热</b>
<% }%>
<!-- JSTL方式 -->
<c:set var="a" value="4"></c:set>
<c:if test="${a>3}">
	<b>今天的天气有点热</b>
</c:if>
<br>
<c:set var="score" value="85"></c:set>
<c:choose>
	<c:when test="${score>=90}"><i>奖励吃鸡装备一套</i></c:when>
	<c:when test="${score>=80&&score<90}"><i>奖励一个空投箱</i></c:when>
	<c:when test="${socre<80&&score>=70}"><i>无奖励无惩罚</i></c:when>
	<c:otherwise>
		<i>男女混合双打</i>
	</c:otherwise>
</c:choose>

<!-- JSTL 的循环标签 -->
<h3>JSTL的循环标签</h3>
<!-- 传统方式画一个表格 -->
<table border="1px">
	<tr>
		<td>课程名称</td>
		<td>教师</td>
		<td>价格</td>
		<td>重要性</td>
	</tr>
	<%
		for(int i=0;i<3;i++){
			if(i==2){
	 %>
	<tr>
		<td>java</td>
		<td>张三</td>
		<td>一万</td>
		<td>非常重要</td>
	</tr>
	<%} }%>
</table>
<%
	ArrayList<String> list = new ArrayList<String>();
	list.add("a");
	list.add("b");
	list.add("c");
	list.add("d");
	request.setAttribute("list", list);
	
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("a1", "哈哈");
	map.put("b1", "嘿嘿");
	request.setAttribute("map", map);
	
 %>
<!-- 使用JSTL方式完成循环 -->
<!-- 常量循环 -->
<c:forEach begin="0" end="4" step="1" varStatus="vs">
	1111---${vs.index}--${vs.count}--${vs.first}--${vs.last}<br />
</c:forEach>
<!-- 动态循环 -->
<c:forEach items="${list}" var="str">
	<i>${str}</i><br />
</c:forEach>
<!-- 创建表格数据 -->
<table border="1px">
	<tr>
		<td>课程名称</td>
		<td>教师</td>
		<td>价格</td>
		<td>重要性</td>
	</tr>
	<c:forEach items="${list}" var="s">
		<tr>
			<td>${s}</td>
			<td>${s}</td>
			<td>${s}</td>
			<td>${s}</td>
		</tr>
	</c:forEach>
</table>

<c:forEach items="${map}" var="m">${m.key}---${m.value}<br /></c:forEach>