package com.cdac.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	
	Connection connection;
	PreparedStatement ps;
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		try {
			ServletContext application = getServletContext(); 
			
			String  dbUrl = application.getInitParameter("dbUrl"); 
			String dbDriver = application.getInitParameter("dbDriver");
			String dbUser = application.getInitParameter("dbUser");
			String dbPassword = application.getInitParameter("dbPassword");
			
			  if (dbUrl == null || dbDriver == null || dbUser == null || dbPassword == null) {
		            throw new ServletException("Database parameters are missing in web.xml.");
		        }
			
			Class.forName(dbDriver);
			
			connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword);
			ps = connection.prepareStatement("select * from users where username=? and password=?");
			
		}
		catch (ClassNotFoundException|SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		
		try {
			if(ps != null) {
				ps.close();
			}
			
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			PrintWriter out = response.getWriter();
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			
			if(username != null && password != null) {
				try {
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet result=ps.executeQuery();
				if(result.next()) {
					out.println("user authenticated");	
			}
				else {
					out.println("some issue occured");
				}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
	}

}
