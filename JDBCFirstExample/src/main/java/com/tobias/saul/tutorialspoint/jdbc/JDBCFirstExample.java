package com.tobias.saul.tutorialspoint.jdbc;

//import required packages
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCFirstExample {
	//JDBC driver name and DB url
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	// database credentials
	static final String USER = "root";
	static final String PASSWORD = "password";
	
	public static void main(String[] args) {
		// for communication between application and DB
		Connection conn = null;
		// for SQL statements
		Statement stmnt = null;
		
		
		try {
			// register jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// open a connection
			System.out.println("Connecting to a database...\n");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			// execute a query
			stmnt = conn.createStatement();
			String sql;
			sql = "SELECT id, age, first, last FROM employees";
			
			// execute query, store in result set
			ResultSet rs = stmnt.executeQuery(sql);
			
			//iterate through result set
			while(rs.next()) {
				// get values from result set
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				System.out.print("ID: " + id + ", ");
				System.out.print("First Name: " + first + ", ");
				System.out.print("Last Name: " + last + ", ");
				System.out.println("Age: " + age);
				
				
			}
			
			System.out.println();
			
			rs.close();
			stmnt.close();
			conn.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//finally block used to close re4sources
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
		
		System.out.println("End of program...");
		
		
	}
	

}
