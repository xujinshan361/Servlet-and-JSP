package com.xujinshan.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xujinshan.dao.UserDao;
import com.xujinshan.vo.User;

public class UserDaoImpl implements UserDao{

	@Override
	public User getUserInfo(String name) {
		// 声明数据存储对象
		User u = null;
		// 声明JDBC对象
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			// 加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			// 获取连接对象
			//useUnicode=true&characterEncoding=UTF-8 解决查询条件是中文不能查询到
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ajax_test?useUnicode=true&characterEncoding=UTF-8", "root", "123456");
			// 创建SQL命令
			String sql = "select * from users where uname=?";
			// 创建SQL命令对象
			ps = conn.prepareStatement(sql);
			// 给占位符赋值
			ps.setString(1, name);
			// 执行
			rs = ps.executeQuery();
			while(rs.next()) {
				u = new User();
				u.setUid(rs.getInt("uid"));
				u.setUname(rs.getString("uname"));
				u.setPrice(rs.getDouble("price"));
				u.setLoc(rs.getString("loc"));
				u.setDesc(rs.getString("desc"));
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
