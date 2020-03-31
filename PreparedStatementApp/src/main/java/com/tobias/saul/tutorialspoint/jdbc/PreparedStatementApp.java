package com.tobias.saul.tutorialspoint.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PreparedStatementApp {
	
	static final String JDBC_DRIVER = "con.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	static final String USER = "root";
	static final String PASSWORD = "password";
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement stmnt = null;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			System.out.println("Connecting to a database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			//parameters can be passed in to prepared statement
			String sql = "UPDATE employees SET age=? WHERE id=?";
			System.out.println("Creating prepared statement....");
			stmnt = conn.prepareStatement(sql);
			
			//setXXX binds values to parameters
			//parameter marked by ordinal position
			stmnt.setInt(1, 12);
			stmnt.setInt(2, 1);
			
			//execute update
			//check rows affected
			int rows = stmnt.executeUpdate();
			System.out.println("Rows affected: " + rows);
			
			sql = "SELECT id, age, first, last FROM employees";
			ResultSet rs =  stmnt.executeQuery(sql);
			
			while(rs.next()) {
				System.out.print("id: " + rs.getInt("id") + ", ");
				System.out.print("age: " + rs.getInt("age") + ", ");
				System.out.print("first: " + rs.getString("first") + ", ");
				System.out.println("last: " + rs.getString("last"));
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
