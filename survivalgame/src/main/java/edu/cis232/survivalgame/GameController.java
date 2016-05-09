package edu.cis232.survivalgame;

import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import edu.cis232.survivalgame.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

@SuppressWarnings("restriction")
public class GameController {
	
ArrayList<Item> inventory = new ArrayList<Item>();
StringBuilder sb = new StringBuilder();
String next = "You Move on to the next room";
boolean unlocked;


	
	
	@FXML
    private ImageView imgBack;
    @FXML
    private Button btnQuit;
    @FXML
    private ImageView img1;
    @FXML
    private ImageView img2;
    @FXML
    private ImageView img3;
    @FXML
    private TextField tfName;
    @FXML
    private ImageView imgSecret;
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

	@FXML
	void quit(ActionEvent event) {
		lblMessage.setText("You gave up hope. your pursuers are close.\n"
				+ "You made it through these rooms:\n"+sb.toString());
		

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
    		unlocked=false;
    		Openable opened = (Openable)thingy;
    		lblMessage.setText(opened.Open());
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
    		unlocked=true;
    		s = "";
    	}
    	lblMessage.setText(s);
    }
    }

	@FXML
    void Start(ActionEvent event) throws SQLException, BadNameException {
		try{
			throw new BadNameException(tfName.getText());
			
		    	try {
					firstRoom();
					btnStart.visibleProperty().setValue(false);
					tfName.setVisible(false);
				} catch (SQLException e) {
						CreateImageDB.initDB();
					e.printStackTrace();
				}
	    	
	    	
	    	}catch (BadNameException e){
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
		
		//REQ #8
		String selectSql = "SELECT Address FROM Image";
		 
		// Send the statement to the DBMS.
        ResultSet result = stmt.executeQuery(selectSql);
        
        result.next();
        String black = result.getString("Address");
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
        Image darkRoom = new Image(black);
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
        String black = result.getString("Address");
        result.next();
        String chest = result.getString("Address");
        result.next();
        String door = result.getString("Address");
        result.next();
        String hall = result.getString("Address");
        
        conn.close();
        sb.append("First Room, ");
        
        
        
        Image doorImage = new Image(door);
        Image chestImage = new Image(chest);
        Image hallImage = new Image(hall);
        Image darkRoom = new Image(black);
        
        
        imgBack.setImage(hallImage);
        
        lblMessage.setText(next);
        
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
				if (door2.getLocked()==false){
					try {
						thirdRoom();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
				
					lblMessage.setText(door2.Open());			
				}
				
			}
        	
        });
        img1.setOnScroll(new EventHandler<ScrollEvent>(){

			@Override
			public void handle(javafx.scene.input.ScrollEvent wheel) {
				
				lblMessage.setText(door2.unlock());
				
			}
        	
        });
        Chest chest1 = new Chest("Second Chest", new Item("Torch", 2));
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
	public void thirdRoom() throws SQLException{
		
	    	final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;ifexists=true;hsqldb.lock_file=false";
	    	
	    	// Create a connection to the database.
			Connection conn = DriverManager.getConnection(DB_URL);
			// Create a Statement object.
			Statement stmt = conn.createStatement();
			
			String selectSql = "SELECT Address FROM Image";
			 
			// Send the statement to the DBMS.
	        ResultSet result = stmt.executeQuery(selectSql);
	        
	        result.next();
	        String black = result.getString("Address");
	        result.next();
	        String chest = result.getString("Address");
	        result.next();
	        String door = result.getString("Address");
	        result.next();
	        String hall = result.getString("Address");
	        conn.close();
	        sb.append("Second Room, ");
	        
	        Image doorImage = new Image(door);
	        Image chestImage = new Image(chest);
	        Image hallImage = new Image(hall);
	        Image darkRoom = new Image(black);
	        
	        imgBack.setImage(hallImage);
	        lblMessage.setText(next);
	        img1.setVisible(false);
	        Door rdoor = new Door("Right Door", 0);
	        img3.setImage(doorImage);
	        img3.setOnMouseEntered(new EventHandler<MouseEvent>(){
	
				@Override
				public void handle(MouseEvent e) {
					lblMessage.setText(rdoor.inspect());
					}
	        });
	        img3.setOnMouseClicked(new EventHandler<MouseEvent>(){
	
				@Override
				public void handle(MouseEvent e) {
					lblMessage.setText(rdoor.Open());
					try {
						darkFourthRoom();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					}
	        });
	        LockedDoor barredDoor = new LockedDoor("Left Door", 8);
	        img2.setImage(doorImage);
	        img2.setOnMouseEntered(new EventHandler<MouseEvent>(){
	        	
				@Override
				public void handle(MouseEvent e) {
					lblMessage.setText(barredDoor.inspect());	
				}
			});
	        img2.setOnMouseClicked(new EventHandler<MouseEvent>(){
	
				@Override
				public void handle(MouseEvent e) {
					lblMessage.setText(barredDoor.Open());
				}
	       });
	        img2.setOnScroll(new EventHandler<ScrollEvent>(){
	
				@Override
				public void handle(javafx.scene.input.ScrollEvent wheel) {
					
					lblMessage.setText(barredDoor.Open());
				}
	        });
	        
	    
	}
	public void darkFourthRoom() throws SQLException{
    	final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;ifexists=true;hsqldb.lock_file=false";
    	
    	// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object.
		Statement stmt = conn.createStatement();
		
		String selectSql = "SELECT Address FROM Image";
		 
		// Send the statement to the DBMS.
        ResultSet result = stmt.executeQuery(selectSql);
        
        result.next();
        String black = result.getString("Address");
        result.next();
        String chest = result.getString("Address");
        result.next();
        String door = result.getString("Address");
        result.next();
        String hall = result.getString("Address");        
        
        conn.close();
        sb.append("Third Room, ");
        
        
        
        Image doorImage = new Image(door);
        Image chestImage = new Image(chest);
        Image hallImage = new Image(hall);
        Image darkRoom = new Image(black);
        
        img1.setVisible(false);
        img2.setVisible(false);
        img3.setVisible(false);
        imgBack.setVisible(false);
        imgSecret.setImage(darkRoom);
        
        lblMessage.setText("This Room is Dark.");
        
        LockedDoor sconce = new LockedDoor("Dark Hall", 2);
        
        imgSecret.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText("Scroll through your Inventory to try and find a torch.");
				
			}
        	
        });

        imgSecret.setOnScroll(new EventHandler<ScrollEvent>(){

			@Override
			public void handle(javafx.scene.input.ScrollEvent wheel) {
				
				
				try {
					litFourthRoom();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
			
        	
        });

        
    }
	public void litFourthRoom() throws SQLException{
    	final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;ifexists=true;hsqldb.lock_file=false";
    	
    	// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object.
		Statement stmt = conn.createStatement();
		
		String selectSql = "SELECT Address FROM Image";
		 
		// Send the statement to the DBMS.
        ResultSet result = stmt.executeQuery(selectSql);
        
        result.next();
        String black = result.getString("Address");
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
        Image darkRoom = new Image(black);
        
        img1.setVisible(true);
        img2.setVisible(true);
        img3.setVisible(false);
        imgSecret.setVisible(false);
        imgBack.setVisible(true);
        imgBack.setImage(hallImage);
        
        lblMessage.setText("You light your Torch, revealing the room");
        
        Door door2 = new Door("Fifth  Door", 1);
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
				try {
					finalRoom();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				
			}
        	
        });
        Chest chest1 = new Chest("Third Chest", new Item("Sword and Shield", 4));
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
	public void finalRoom() throws SQLException{
    	final String DB_URL = "jdbc:hsqldb:file:ObjectsDB/objects;ifexists=true;hsqldb.lock_file=false";
    	
    	// Create a connection to the database.
		Connection conn = DriverManager.getConnection(DB_URL);
		// Create a Statement object.
		Statement stmt = conn.createStatement();
		
		String selectSql = "SELECT Address FROM Image";
		 
		// Send the statement to the DBMS.
        ResultSet result = stmt.executeQuery(selectSql);
        
        result.next();
        String black = result.getString("Address");
        result.next();
        String chest = result.getString("Address");
        result.next();
        String door = result.getString("Address");
        result.next();
        String hall = result.getString("Address");
        result.next();
        String land = result.getString("Address");
        
        conn.close();
        sb.append("Dark Room, ");
 
        Image doorImage = new Image(door);
        Image chestImage = new Image(chest);
        Image hallImage = new Image(hall);
        Image darkRoom = new Image(black);
        Image landscape = new Image(land);
        
        imgBack.setImage(hallImage);
        img2.setVisible(false);
        
        lblMessage.setText(next);
        
        Door lastDoor = new Door("Final Door", 1);
        img1.setImage(doorImage);
        img1.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				lblMessage.setText(lastDoor.inspect()+".. \n"
						+ "But you can see some sunlight behind this one");
				
			}
        	
        });
        img1.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				sb.append("Final room.");
				imgBack.setImage(landscape);
				img1.setVisible(false);
				lblMessage.setText("You Make it out into the sunlight, a world of "
						+ "\nopportunity ahead of you. You Made it through these rooms:\n"+sb);
				
			}
        	
        });

        
    }
	
}
