package edu.cis232.survivalgame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.MouseEvent;

public class GameController {
	
ArrayList<Item> inventory = new ArrayList<Item>();

	
	
	@FXML
    private ImageView imgBack;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private Button btnStart;

    @FXML
    private Label lblMessage;
    
    /*
     * Opens the chest and adds items to the players inventory
     */ 
    /*
     * Attempts to open the door seeing whether or not the
     * door is locked
     */
   
    
    
    void OpenObject(Object thingy) {
    	
    	OpenIfOpenable(thingy , inventory);
    	
    }
  
    
    /*
     * Will create the first room and if the Database is not created
     * Will create the Database
     */

	private void OpenIfOpenable(Object thingy, ArrayList<Item> inventory){
    	if(thingy instanceof Door){
    		Openable opened = (Openable)thingy;
    		lblMessage.setText(opened.Open());
    		
    	}else if(thingy instanceof Chest){
    		Openable opened = (Openable)thingy;
    		lblMessage.setText(opened.Open());
    		Item i = ((Chest) opened).getItem();
    		inventory.add(i);
    		
    	}else if(thingy instanceof LockedDoor){
    		Openable opened = (Openable)thingy;
    		lblMessage.setText(opened.Open());
    		//on click
    		CheckForKey((LockedDoor)thingy, inventory);
    		
    	} else {
    		lblMessage.setText("The "+ thingy.getName() + " is not openable");
    	}
    }

    private void CheckForKey(LockedDoor door, ArrayList<Item> inventory){

    for(Item i : inventory){
    	String s = "You Cannot open this door.";
    	if(door.getValue()==i.getValue()){
    		lblMessage.setText(door.unlock());
    		s = "";
    	}
    	lblMessage.setText(s);
    }
    }

	@FXML
    void Start(ActionEvent event) throws SQLException {
    	
    	try {
			firstRoom();
			btnStart.visibleProperty().setValue(false);
		} catch (SQLException e) {
				CreateImageDB.initDB();
			e.printStackTrace();
		}
    }
    
    /*
     * Reads from the database and sets the first room images
     */
    public void firstRoom() throws SQLException{
    	final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;ifexists=true;hsqldb.lock_file=false";
    	
    	// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object.
		Statement stmt = conn.createStatement();
		
		String selectSql = "SELECT Address FROM Image";
		 
		// Send the statement to the DBMS.
        ResultSet result = stmt.executeQuery(selectSql);
        
        result.next();
        String chest = result.getString("Address");
        result.next();
        String door = result.getString("Address");
        result.next();
        String hall = result.getString("Address");
        
        conn.close();
        
        
        
        Image doorImage = new Image(door);
        Image chestImage = new Image(chest);
        Image hallImage = new Image(hall);
        
        imgBack.setImage(hallImage);
        
        lblMessage.setText("Welcome to the Game.\n"
        		+ "You have escaped from your cell, but you can hear your captors\n close behind. Run quickly, but be careful; you cannot go back.");
        
        Door door1 = new Door("First Door", 0);
        img1.setImage(doorImage);
        img1.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(door1.inspect());

				
			}
        	
        });
        img1.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(door1.Open());				
				try {
					secondRoom();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
        	
        });
        Chest chest1 = new Chest("First Chest", new Item("Iron Key", 1));
        img2.setImage(chestImage);
        img2.setOnMouseEntered(new EventHandler<MouseEvent>(){
        	
			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(chest1.inspect());
				
			}
        	
        });
        img2.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(chest1.Open());
				inventory.add(chest1.getItem());
				
			}
        	
        });
        
    }
    public void secondRoom() throws SQLException{
    	final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;ifexists=true;hsqldb.lock_file=false";
    	
    	// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object.
		Statement stmt = conn.createStatement();
		
		String selectSql = "SELECT Address FROM Image";
		 
		// Send the statement to the DBMS.
        ResultSet result = stmt.executeQuery(selectSql);
        
        result.next();
        String chest = result.getString("Address");
        result.next();
        String door = result.getString("Address");
        result.next();
        String hall = result.getString("Address");
        
        conn.close();
        
        
        
        Image doorImage = new Image(door);
        Image chestImage = new Image(chest);
        Image hallImage = new Image(hall);
        
        imgBack.setImage(hallImage);
        
        lblMessage.setText("You Move on to the second room");
        
        LockedDoor door2 = new LockedDoor("Second Door", 1);
        img1.setImage(doorImage);
        img1.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(door2.inspect());
				
			}
        	
        });
        img1.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(door2.Open());
				
			}
        	
        });
        img1.setOnScroll(new EventHandler<ScrollEvent>(){

			@Override
			public void handle(javafx.scene.input.ScrollEvent wheel) {
				
				lblMessage.setText(door2.unlock());
				
			}
        	
        });
        Chest chest1 = new Chest("Second Chest", new Item("Torch", 0));
        img2.setImage(chestImage);
        img2.setOnMouseEntered(new EventHandler<MouseEvent>(){
        	
			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(chest1.inspect());
				
			}
        	
        });
        img2.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(chest1.Open());
				
			}
        	
        });
        
    }

}
