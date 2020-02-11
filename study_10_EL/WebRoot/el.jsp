<%@ page language="java" import="java.util.*,com.xujinshan.pojo.*" pageEncoding="utf-8"%>
<!-- 
使用传统的方式获取作用域对象的数据

 -->
 <h3>EL表达式学习:使用传统方式获取作用域对象的数据</h3>
 <b><%=request.getParameter("uname")%></b><br>
 <b><%=request.getAttribute("str")%></b><br>
 <b><%=((User)request.getAttribute("user")).getAddr().getTown() %></b><br>
 <b><%=((ArrayList<String>)request.getAttribute("list")).get(2) %></b><br>
 <b><%=((User)((ArrayList<User>)request.getAttribute("list2")).get(0)).getAddr().getPre()%></b><br>
 <b><%=((ArrayList<User>)request.getAttribute("list2")).get(0).getAddr().getPre()%></b><br>
 <b><%=((HashMap<String,String>)request.getAttribute("map")).get("a")%></b><br>
 <b><%=((User)((HashMap<String,User>)request.getAttribute("map2")).get("a")).getUname() %></b>
 <%--
 	传统方式获取作用域数据：
 		缺点一：导入包
 		缺点二：需要强制转型
 		缺点三：获取数据的代码过于麻烦
 	使用EL表达式获取作用域数据：
 		作用：
 			获取作用域对象中的数据
 			注意：获取的是pageContext、request、session、application四个对象中的数据，其他数据一概不理会
 			找到了则获取返回，找不到则什么都不做，也不报错
 		语法：
 			${表达式}
 			表达式：
 				获取请求数据
	 				request对象存储了请求数据，---> param.键名   直接返回值
	 				request对象存储了请求数据，---> paramvalues.键名      返回的是数组
	 			通过setAttribute 方法存到作用域对象中的数据：
	 				${键名} 返回键名所对应的值
	 			注意：
	 				如果键名存储的是普通字符串则直接返回
	 				如果存储的是对象，则返回的是对象
	 					获取对象中的数据：
	 						普通对象
	 							${键名.属性名.属性名.....}
	 						集合对象
	 							list集合----->${键名[角标]}
	 							map集合------->${键名.map集合存储的键名}
	 作用域查找顺序：
	 	默认查找顺序
		 pageContext-->request-->session-->application
		 注意：
		 	每次查找都是从小到大进行查找，找到了则获取，不再继续找了
 		指定查找：
 			${pageScope.hello}---${requestScope.hello}---${sessionScope.hello}--${applicationScope.hello}
 			
 	EL表达式的逻辑运算	
 		${逻辑表达式}	&&	||	!
 		${算术表达式}	+	-	*	/
 		${关系表达式}	>	<	>=	<=	!=	==	%
 		特殊：
 			三目运算符
 		注意：
 			可以是算术表达式，也可以是算术表达式，
 			+：表示加法运算，不表示字符连接，
 			使用EL表达式进行字符连接会报错
 	
 	EL的空值判断:
 		&{empty 键名}		
 		作用：
 			判断键名对象的值是否有数据
 			
 	EL获取请求头数据和Cookie数据
 		请求头数据：
 			${header}:返回所有的请求头数据
 			${header["键名"]}：返回指定键名的请求头数据
 			${headerValues["键名"]}：返回指定键名(同键不同值)的值的数组
 		获取Cookie数据：
 			&{cookie}：返回存储了所有的cookie对象的map集合
 			${cookie.键名}:返回指定的cookie对象
 			${cookie.键名.name}：	返回指定的cookie对象的存储的数据的键名
 			${cookie.键名.value}:返回指定的cookie对象的存储的数据的值
 		
 --%>
<!-- 使用EL表达式获取作用域对象数据 -->
<hr>
<h3>EL表达式学习：使用EL表达式获取作用域对象的数据</h3>
 <b>${param.uname}</b><br>
 <b>${str}</b><br>
 <b>${user.addr.town}</b><br>
 <b>${list[2]}</b><br>
 <b>${list2[0].addr.pre}</b><br>
 <b>${map.c}</b><br>
 <b>${map2.a.addr.city}</b>
 <hr/>
 <h3>EL的作用域查找顺序</h3>
 <!-- EL的作用域查找顺序 -->
 <%
 	pageContext.setAttribute("hello", "hello pageContext");
 	request.setAttribute("hello", "hello request");
 	session.setAttribute("hello", "hello session");
 	application.setAttribute("hello", "hello application");
 
 %>
 <b>${pageScope.hello}---${requestScope.hello}---${sessionScope.hello}--${applicationScope.hello}</b>
 <hr>
 <h3>EL表达式的逻辑运算</h3>
 <!-- EL表达式的逻辑运算 -->
 ${1+2}--${4-2}--${1*2}--${4/2}--${4%2}--${4==4}--${4>3}--${1==1?"男":"女"}--${1+"2"}
 
 <hr>
 <h3>EL的空值判断</h3><br>
 ${empty s}--${empty s1}--${empty s2}--${empty s3}--${empty s4}
 
 <hr>
 <h3>EL获取请求头数据和Cookie数据</h3>
 ${header}===${header["user-agent"]}===${headerValues["accept-language"][0]}
 <hr>
 ${cookie}===${cookie.JSESSIONID}===${cookie.JSESSIONID.name}===${cookie.JSESSIONID.value}
 
 
 
 
 
 
 
 
 
 
 
 