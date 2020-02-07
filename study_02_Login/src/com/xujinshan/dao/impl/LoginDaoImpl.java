package com.xujinshan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xujinshan.dao.LoginDao;
import com.xujinshan.pojo.User;

public class LoginDaoImpl  implements LoginDao{

	@Override
	public User checkLoginDao(String uname, String pwd) {
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_test?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			// 创建SQL命令
			String sql = "select * from t_user where uname=? and pwd=?";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			ps.setString(1, uname);
			ps.setString(2, pwd);
			// 执行
			rs = ps.executeQuery();
			// 遍历执行结果
			while(rs.next()) {
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
			}
		} catch(Exception e) {
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
		// 返回
		return u;
	}
	
	@Override
	public User checkUidDao(String uid) {
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_test", "root", "123456");
			// 创建SQL命令
			String sql = "select * from t_user where uid=?";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			ps.setString(1, uid);
			// 执行
			rs = ps.executeQuery();
			// 遍历执行结果
			while(rs.next()) {
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPwd(rs.getString("pwd"));
			}
		} catch(Exception e) {
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
		// 返回
		return u;
	}
}
