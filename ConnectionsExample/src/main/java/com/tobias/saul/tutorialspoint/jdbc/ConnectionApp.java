package com.tobias.saul.tutorialspoint.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionApp {
	
	//driver and DB url to be used
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	//user and password for connection
	static final String USER = "root";
	static final String PASSWORD = "password";
	
	
	
	public static void main(String[] args) {
		//connection object used for physical connection with a database
		Connection conn = null;
		//store user name and password
		Properties info = new Properties();
		
		
		try {
			//register a driver to load up driver's class
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			info.put("user", USER);
			info.put("password", PASSWORD);
			
			System.out.println("Connecting to a database");
			//getConnection() in order to create connection object for connection with DB
			//three overloaded methods available 
			
			//conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			conn = DriverManager.getConnection(DB_URL, info);
			
			System.out.println("-----------------------");
			System.out.println("Successul connection to database");
		} 
		
		// SQL exceptions to handle any sql errors
		catch (SQLException se) {
			se.printStackTrace();
		} 
		
		// used to close resources, clean up
		finally {
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
