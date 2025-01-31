package com.cdac.shopipng;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Product extends HttpServlet {
	
	Connection connection;
	PreparedStatement ps;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","1234");
			connection.prepareStatement("select * from users where username =? and password=?");
		}		
			catch (ClassNotFoundException|	SQLException e) {
				e.printStackTrace();
			}	
	}
	
	
	@Override
	public void destroy() {
		try {
			if(ps!=null) {
				ps.close();
			}
			if(connection!=null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		try{
			
			String tmp = request.getParameter("categoryId");
			int categoryId = Integer.parseInt(tmp);
			
			ps.setInt(1, categoryId);
			try(ResultSet result = ps.executeQuery()){
			out.println("<html>");
			out.println("<body>");
			out.println("<table-border ='1'>");
			out.println("<tr>");
			out.println("<th> Name </th>");
			out.println("<th> Description </th>");
			out.println("<th> Price </th>");
			out.println("<th> Image </th>");
			out.println("</tr>");
			
			while(result.next()) {
				out.println("<tr>");
				out.println("<td>" +result.getString("ProductName")+"</td>");
				out.println("<td>" +result.getString("productDescription")+"</td>");
				out.println("<td>" +result.getString("productPrice")+"</td>");
				out.println("<td><img src='Images/" + result.getString("productImageUrl")+"'height='60px' width='60px'/></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
			
			
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	}
