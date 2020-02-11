package com.xujinshan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xujinshan.dao.UserDao;
import com.xujinshan.pojo.User;

public class UserDaoImpl implements UserDao {
	
	// 根据用户名和密码查询用户信息
	@Override
	public User checkUserLoginDao(String uname, String pwd) {
		// 声明JDBC对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 声明数据存储对象
		User u = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			// 创建SQL命令
			String sql = "select * from t_user where uname=? and pwd=?";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			ps.setString(1, uname);
			ps.setString(2, pwd);
			// 执行SQL
			rs = ps.executeQuery();
			// 遍历结果
			while(rs.next()) {
				// 给变量赋值
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
			}
			System.out.println(u);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭资源
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 返回结果
		return u;
	}
	
	// 根据用户ID修改用户密码
	@Override
	public int userChangePwdDao(String newPwd, int uid) {
		// 声明JDBC对象
		Connection conn = null;
		PreparedStatement ps = null;
		// 创建变量 
		int index = -1;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			// 创建SQL命令
			String sql = "update t_user set pwd=? where uid=?";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			ps.setString(1, newPwd);
			ps.setInt(2, uid);
			// 执行SQL
			index = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭资源
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 返回结果
		return index;	
	}
	
	// 获取所有的用户信息
	@Override
	public List<User> userShowDao() {
		// 声明JDBC对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		// 声明数据存储对象
		List<User> lu = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			// 创建SQL命令
			String sql = "select * from t_user";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			// 执行SQL
			rs = ps.executeQuery();
			// 给集合赋值
			lu = new ArrayList<User>();
			// 遍历结果
			while(rs.next()) {
				System.out.println("*******");
				// 给变量赋值
				User u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
				u.setSex(rs.getString("sex"));
				u.setAge(rs.getInt("age"));
				u.setBirth(rs.getString("birth"));
				// 将对象存储到lu
				lu.add(u);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭资源
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 返回结果
		return lu;
	}
	// 用户注册
	@Override
	public int userRegDao(User u) {
		// 声明JDBC对象
		Connection conn = null;
		PreparedStatement ps = null;
		// 声明数据存储对象
		int index = -1;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			// 创建SQL命令
			String sql = "insert into t_user values(default,?,?,?,?,?)";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			ps.setString(1, u.getUname());
			ps.setString(2, u.getPwd());
			ps.setString(3, u.getSex());
			ps.setInt(4, u.getAge());
			ps.setString(5, u.getBirth());
			// 执行
			index = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭资源
			if(ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		// 返回结果
		return index;
	}
}
