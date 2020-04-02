package com.tobias.saul.tutorialspoint.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetUpdatableApp {

	static final String JDBC_DRIVER = "com.mysql.jc.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/EMP?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

	static final String USER = "root";
	static final String PASSWORD = "password";

	public static void displayRS(ResultSet rs) {
		try {
			
			System.out.println("Data");
			System.out.println("----------------------");
			rs.beforeFirst();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				int age = rs.getInt("age");
				String first = rs.getString("first");
				String last = rs.getString("last");
				
				
				System.out.print("id: " + id + ", ");
				System.out.print("age: " + age + ", ");
				System.out.println("Name: " +  first + " " + last);
			}
			
			System.out.println();
		} catch (SQLException se) {
			se.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmnt = null; 
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			System.out.println("Getting connection...");
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Connected.\n");
			
			System.out.println("Creating statement...");
			stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("Statement created.\n");
			
			String sql = "SELECT id, age, first, last FROM employees";
			ResultSet rs = stmnt.executeQuery(sql);
			
			//initial results
			displayRS(rs);
			
			rs.beforeFirst();
			
			//each rows age gets updated
			while(rs.next()) {
				int newAge = rs.getInt("age") + 5;
				rs.updateDouble("age", newAge);
				rs.updateRow();
			}
			
			//after first update
			displayRS(rs);
			
			//inserting a record to table
			System.out.println("\nInserting new record...\n");
			rs.moveToInsertRow();
			rs.updateInt("id", 8);
			rs.updateInt("age", 4);
			rs.updateString("first", "John");
			rs.updateString("last", "Paul");
			
			//commit row
			rs.insertRow();
			
			//after inserting row
			displayRS(rs);
			
			//delete a record
			rs.absolute(2);
			
			int id = rs.getInt("id");
			int age = rs.getInt("age");
			String first = rs.getString("first");
			String last = rs.getString("last");
			
			System.out.println("\nRecord to be deleted");
			System.out.println("id: " + id + " age: " + age + " Name: " + first + " " + last + "\n");
			
			//delete the current row
			rs.deleteRow();
			
			//after deletion
			displayRS(rs);
			
			rs.close();
			stmnt.close();
			conn.close();
			
			
		} catch(SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmnt != null) {
					stmnt.close();
				} 
			} catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}

}
