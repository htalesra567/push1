package com.cdac.jdbc;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Helper {
	private Connection connection = null;
	Scanner sc = new Scanner(System.in);
	
	public Helper() {
		try {
		ResultSet rs = null;
		Statement statement = null;
		
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbccdac","root","1234");
		statement = connection.createStatement();
		
	}
		catch(SQLException e) {
			e.getStackTrace();
		}
	}
	
	public void addData() {
		
		System.out.println("enter username");
		String username = sc.nextLine();
		System.out.println("enter password");
		String password = sc.nextLine();
		System.out.println("enter name");
		String name = sc.nextLine();
		System.out.println("enter email");
		String email = sc.nextLine();
		System.out.println("enter city");
		String city = sc.nextLine();
		try {
			PreparedStatement sp = connection.prepareStatement("insert into users values(?,?,?,?,?)");
			
			sp.setString(1, username);
			sp.setString(2,password);
			sp.setString(3,name);
			sp.setString(4,email);
			sp.setString(5,city);
		} catch (SQLException e) {
			// TODO: handle exception
			e.getStackTrace();
		}
		
	}
	
	public void display(){
			ResultSet rSet = null;
			System.out.println("enter city name");
			String city = sc.nextLine();
		try {
			PreparedStatement sp = connection.prepareStatement("select * from users where city = ?");
			sp.setString(1, city);
			rSet = sp.executeQuery();
			
			while(rSet.next()) {
				System.out.println(rSet.getString(1));
				System.out.println(rSet.getString(2));
				System.out.println(rSet.getString(3));
				System.out.println(rSet.getString(4));
				System.out.println(rSet.getString(5));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}
	}
	
	public void updatePassWord() {
		System.out.println("enter username");
		String username = sc.nextLine();
		
		System.out.println("enter updated password");
		String uppass = sc.nextLine();
		
		try {
			PreparedStatement ps = connection.prepareStatement("update users set password =? where username =?");
			ps.setString(1, uppass);
			ps.setString(2, username);
			ps.execute();
			System.out.println("record updated");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	public void displayByUserName() {
		System.out.println("enter username");
		String user = sc.nextLine();
		ResultSet rs = null;
		try {
			PreparedStatement ps = connection.prepareStatement("select * from users where username =?");
			ps.setString(1, user);
		
		 rs =ps.executeQuery();
		 while(rs.next()) {
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
			System.out.println(rs.getString(5));
		 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
			}
	
}
