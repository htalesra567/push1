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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Category")
public class Category extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8163319599306922057L;
	Connection connection;
	PreparedStatement ps;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","1234");
			ps=connection.prepareStatement("select * from category");
		}		
			catch (ClassNotFoundException|SQLException e) {
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
		
		try(ResultSet result = ps.executeQuery()) {
			
			out.println("<html>");
			out.println("<body>");
			out.println("<table-border ='1'>");
			out.println("<tr>");
			out.println("<th> Name </th>");
			out.println("<th> Description </th>");
			out.println("<th> Image </th>");
			out.println("</tr>");
			
			while(result.next()) {
				out.println("<tr>");
				out.println("<td><a href='products?categoryId =" + result.getInt("categoryId")+"'>"+result.getString("categoryName")+"</a></td>");
				out.println("<td>" +result.getString("categoryDescription")+"</td>");
				out.println("<td><img src='Images/" + result.getString("categoryImageUrl")+"'height='60px' width='60px'/></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
