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

@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1756492348311370222L;
	Connection connection;
	PreparedStatement ps;

	@Override
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "1234");
	        ps = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
	        System.out.println("Database connection and PreparedStatement initialized successfully.");
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        throw new ServletException("Failed to initialize database connection or PreparedStatement", e);
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
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		PrintWriter out = response.getWriter();
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username == null || password == null) {
			throw new ServletException();
		}
		
		try {
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			try(ResultSet result = ps.executeQuery()) {
				 if(result.next()) {
					 response.sendRedirect("Category");
				 }
				 else {
					 System.out.println("Authentication Failed");
				 }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
}















