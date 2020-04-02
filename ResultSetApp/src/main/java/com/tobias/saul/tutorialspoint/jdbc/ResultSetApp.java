package com.tobias.saul.tutorialspoint.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetApp {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	static final String USER = "root";
	static final String PASSWORD = "password";
	
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmnt = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			System.out.println("Connecting....");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			String sql = "SELECT id, age, first, last FROM employees";
			
			//statement return a result set
			//has a cursor pointing to current row in result set
			//has navigation, get, and update methods
			//parameter RSType, RSConcurrency
			//types: FORWARD_ONLY, SCROLL_INSENSITIVE, SCROLL_INSENSITIVE
			//concurrency: READ_ONLY, UPDATABLE
			stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmnt.executeQuery(sql);
			
			System.out.println("\nMoving cursor to last row of result set");
			rs.last();
			
			System.out.println("\nDisplaying the record at row");
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");
			
			System.out.println("id : " + id + " age: " + age + " first: " + first + " last: " + last);
			
			System.out.println("\nMove cursor to first row");
			rs.first();
			
			System.out.println("\nDisplaying the record at row");
			id = rs.getInt("id");
			age = rs.getInt("age");
			first = rs.getString("first");
			last = rs.getString("last");
			
			System.out.println("id : " + id + " age: " + age + " first: " + first + " last: " + last);
			
			System.out.println("\nMove cursor to next row");
			rs.next();
			
			System.out.println("\nDisplaying the record at row");
			id = rs.getInt("id");
			age = rs.getInt("age");
			first = rs.getString("first");
			last = rs.getString("last");
			
			System.out.println("id : " + id + " age: " + age + " first: " + first + " last: " + last);
			
			
			rs.close();
			System.out.println("\nStatement closing...");
			stmnt.close();
			System.out.println("Statement closed.");
			System.out.println("\nConnection closing...");
			conn.close();
			System.out.println("Connection closed.");
			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			
			try {
				if(stmnt != null) {
					stmnt.close();
				}
			} catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
				} 
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	
	

}
