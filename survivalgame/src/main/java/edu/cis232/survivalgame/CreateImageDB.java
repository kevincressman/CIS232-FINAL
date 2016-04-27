package edu.cis232.survivalgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateImageDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects";

			try {
				// Create a connection to the database.
				Connection conn = DriverManager.getConnection(DB_URL);
	
				dropTables(conn);
				
				buildImageTable(conn);
				
				conn.close();
			} catch (Exception ex) {
				System.out.println("ERROR: " + ex.getMessage());
			}
    	}
	

	/*
	 * Drops Employee Table if already exists
	 */
	public static void dropTables(Connection conn) {
		System.out.println("Checking for existing tables.");
	
		try {
				Statement stmt = conn.createStatement();
			
			try {
				
				stmt.execute("DROP TABLE Image");
				System.out.println("Image table dropped.");
			} catch (SQLException ex) {
	
			}
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/*
	 * Creates the Employee Table
	 * also adds in five rows of data
	 */
    
	public static void buildImageTable(Connection conn) {
		try {

			Statement stmt = conn.createStatement();

			// Create the table.
			stmt.execute("CREATE TABLE Image (" + "ImageID CHAR(10) NOT NULL PRIMARY KEY, " + "Name CHAR(25), " + "Adress CHAR(500), " + ")");

			// Insert row #1.
			stmt.execute("INSERT INTO Image VALUES ( " + "'D-101', " + "'Door', " 
						 + "'http://preview.turbosquid.com/Preview/2014/07/10__22_08_28/asdasd.jpg89018b1d-3ab2-4763-bb9b-e301982cbe3aOriginal.jpg', )");
//
//			// Insert row #2.
//			stmt.execute("INSERT INTO Image VALUES ( " + "'A-101', " + "'Hall', " 
//						 + "'Secretary of War', )");
//
//			// Insert row #3.
//			stmt.execute("INSERT INTO Image VALUES ( " + "'A-103', " + "'William Seward', " 
//						 + "'Secretary of State', " + "16.95 )");
//
//			// Insert row #4.
//			stmt.execute("INSERT INTO Image VALUES ( " + "'A-104', " + "'Ulysses S. Grant', " 
//						 + "'Commanding General of the United States Army', " + "13.55 )");
//
//			// Insert row #5.
//			stmt.execute("INSERT INTO Image VALUES ( " + "'A-105', " + "'Paul Turnbaugh', " 
//						 + "'Presidential Aid', " + "5.95 )");

			System.out.println("Image table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}

