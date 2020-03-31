package com.tobias.saul.tutorialspoint.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class StatementApp {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	static final String USER = "root";
	static final String PASSWORD = "password";
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmnt = null;
		
		try {
			Properties info = new Properties();
			info.put("user", USER);
			info.put("password", PASSWORD);
			
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			System.out.println("Connecting to a database...");
			conn = DriverManager.getConnection(DB_URL, info);
			
			System.out.println("Creating statement....");
			String query = "UPDATE employees SET age=23 WHERE id=1";
			stmnt = conn.createStatement();
			
			//check if query returns result set
			Boolean  ret = stmnt.execute(query);
			System.out.println("Query returns result set: " + ret.toString());
			
			// execute update
			//returns number of rows affected
			int rows = stmnt.executeUpdate(query);
			System.out.println("Number of rows affected: " + rows + "\n");
			
			String sql = "SELECT id, age, first, last FROM employees";
			//get result set
			ResultSet rs =  stmnt.executeQuery(sql);
			
			//iterate through results
			while(rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				System.out.print("ID: " + id + ", ");
				System.out.print("Age: " + age + ", ");
				System.out.print("First Name: " + first + ", ");
				System.out.println("Last Name: " + last);
			}
			
			rs.close();
			stmnt.close();
			conn.close();
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if(stmnt != null) {
					stmnt.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
