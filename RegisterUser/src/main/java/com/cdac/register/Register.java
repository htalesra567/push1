package com.cdac.register;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class Register extends HttpServlet {

	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		
		try {
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<body>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td> username:</td>");
			out.println("<td> password:</td>");
			out.println("<td> name:</td>");
			out.println("<td> email:</td>");
			out.println("<td> city:</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> "+ username+"</td>");
			out.println("<td> "+ password+"</td>");
			out.println("<td> "+ name+"</td>");
			out.println("<td> "+ email+"</td>");
			out.println("<td> "+ city+"</td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("</body>");
			out.println("</body>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
