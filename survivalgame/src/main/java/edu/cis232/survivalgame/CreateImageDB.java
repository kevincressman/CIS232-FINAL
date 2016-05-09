package edu.cis232.survivalgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateImageDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			initDB();
    	}
	

	/*
	 * Drops Image Table if already exists
	 */
	public static void dropTables(Connection conn) {
		System.out.println("Checking for existing tables.");
	
		try {
				Statement stmt = conn.createStatement();
			
			try {
				
				stmt.execute("DROP TABLE Image");
				System.out.println("Image table dropped.");
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	/*
	 * Creates the Image Table
	 * also adds in four rows of data
	 */
    
	public static void buildImageTable(Connection conn) {
		try {

			Statement stmt = conn.createStatement();

			// Create the table.
			stmt.execute("CREATE TABLE Image (" + "ImageID CHAR(10) NOT NULL PRIMARY KEY, " + "Name CHAR(25), " + "Address CHAR(5000) " + ")");


			// Insert row #1.Door
			stmt.execute("INSERT INTO Image VALUES ( " + "'D-101', " + "'Door_1', " 
						 + "'http://1.bp.blogspot.com/_5ke3OeOEo0g/TLrm7groP8I/AAAAAAAACqU/6FQEcnYbMXY/s1600/3_door.jpg' )");

			// Insert row #2.Hall
			stmt.execute("INSERT INTO Image VALUES ( " + "'H-101', " + "'Hall_1', " 
						 + "'https://s-media-cache-ak0.pinimg.com/736x/ed/40/aa/ed40aa1a34995ce14b0a7e57dc3f3b8c.jpg' )");
			
			//Insert row #3.Chest
			stmt.execute("INSERT INTO Image VALUES ( " + "'C-101', " + "'Chest_1', " 
					 + "'https://revphil2011.files.wordpress.com/2011/04/treasure_chest_medium.png' )");
			
			//Insert row #4.Black
			stmt.execute("INSERT INTO Image VALUES ( " + "'B-101', " + "'BLACK_1', " 
					 + "'https://lh5.ggpht.com/kvswWeqQPJYFZbHQlQPswxIeAOZ-U6JQR5YQ1OYjobK9oT8bvNmptVXJbfwVZS1xnQeg=h900' )");
			
			//Insert row #5.Land
			stmt.execute("INSERT INTO Image VALUES ( " + "'L-101', " + "'Land_1', " 
					 + "'https://www.expired-domains.co/content/wp-content/uploads/2015/08/Late-Summer-Field-Summer-Landscape-.jpg' )");
			

			System.out.println("Image table created.");
		} catch (SQLException ex) {
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
	
	public static void initDB(){
		final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;hsqldb.lock_file=false";

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
}