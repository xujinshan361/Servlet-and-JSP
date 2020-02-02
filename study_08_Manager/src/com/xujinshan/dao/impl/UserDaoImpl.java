package com.xujinshan.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// 声明变量
		User u = null;
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/manager", "root", "123456");
			// 创建SQL命令
			String sql = "select * from t_user where uname=? and pwd =?";
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
}
