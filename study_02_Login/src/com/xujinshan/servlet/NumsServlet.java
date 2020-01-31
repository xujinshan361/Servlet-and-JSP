package com.xujinshan.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class NumsServlet extends HttpServlet {

	// 覆写init方法，将数据读取到ServletContext对象中
	@Override
	public void init() throws ServletException {
		// 获取文件中的计数器数据
		// 获取文件路径
		String path = this.getServletContext().getRealPath("/nums/nums.txt");
		// 声明流对象
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(path);
			br = new BufferedReader(fr);
			String nums = br.readLine();
			System.out.println(nums);
			this.getServletContext().setAttribute("nums", nums);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 覆写销毁方法，存储计数器到文件
	@Override
	public void destroy() {
		// 获取网页计数器
		int nums = (int) this.getServletContext().getAttribute("nums");
		// 获取文件路径
		String path = this.getServletContext().getRealPath("/nums/nums.txt");
		// 声明流对象
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(path);
			bw = new BufferedWriter(fw);
			bw.write(nums+"");
			bw.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
