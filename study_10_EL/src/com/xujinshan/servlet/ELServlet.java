package com.xujinshan.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xujinshan.pojo.Address;
import com.xujinshan.pojo.User;

/**
 * EL表达式的学习：
 * 		问题：
 * 			在servlet进行请求处理后，使用作用域对象作为数据流转的载体，将数据流转给对应的JSP文件
 * 			那么怎么在JSP中获取作用域中的数据?
 * 		使用：
 * 			传统方式：在JSP页面中，使用java脚本段语句。
 * 			
 * 		什么是EL表达式
 * 			全称：Expression Language 。一种写法非常简洁的表达式，语法简单，易懂，便于使用。表达式语言的灵感来自于ECMAScript 和XPath表达式语言
 * 		EL表达式的作用：
 * 			作用：
 * 				让JSP书写起来更加的方便，简化在JSP中获取作用域或者请求数据的写法，也会搭配Jstl 来进行使用
 * 		
 * @author xujinshan361@163.com
 *
 */

/*
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
 */
public class ELServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		// 获取请求信息
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pwd");
		
		// 处理请求信息
		System.out.println(uname + "--" + pwd);
		// 响应处理结果
			// 使用request作用域进行数据流转
				// 普通字符串
				req.setAttribute("str", "天气很清爽，很适合学习");
				// 对象类型
				User u= new User(1, "张三", "看电影", new Address("安徽","合肥","肥西县"));
				req.setAttribute("user", u);
				// 集合类型
					// List 集合
						// 存储普通字符串
						List<String> list = new ArrayList<String>();
						list.add("张三");
						list.add("李四");
						list.add("王五");
						// 存储对象
						User u2 = new User(2,"古丽娜","拍电影",new Address("新疆","乌鲁木齐","乌鲁木齐"));
						req.setAttribute("list", list);
						List<User> list2 = new ArrayList<>();
						list2.add(u2);
						req.setAttribute("list2", list2);
					// Map 集合
						// 存储普通字符
						Map<String,String> map = new HashMap<String,String>();
						map.put("a", "北京");
						map.put("b", "上海");
						req.setAttribute("map", map);
						// 存储对象
						Map<String, User> map2 = new HashMap<String, User>();
						map2.put("a", u);
						map2.put("b", u2);
						req.setAttribute("map2", map2);
					// 空值判断
						req.setAttribute("s", "");
						req.setAttribute("s1", new User());
						req.setAttribute("s3",new ArrayList<String>());
						req.setAttribute("s4", new HashMap<String,String>());
			// 请求转发
			req.getRequestDispatcher("/el.jsp").forward(req, resp);
			return ;
	}
}
