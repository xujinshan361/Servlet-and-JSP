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
 