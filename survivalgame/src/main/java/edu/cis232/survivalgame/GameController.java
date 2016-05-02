package edu.cis232.survivalgame;

import edu.cis232.survivalgame.CreateImageDB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameController {
	
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
    @FXML
    void OpenChest(MouseEvent event) {
    	
    	lblMessage.setText(OpenIfOpenable(ARGUMENTS));
    }

    /*
     * Attempts to open the door seeing whether or not the
     * door is locked
     */
    @FXML
    void OpenDoor(MouseEvent event) {

    }
    
    /*
     * Will create the first room and if the Database is not created
     * Will create the Database
     */

    @FXML
    void Start(ActionEvent event) throws SQLException {
    	
    	try {
			buildRoom();
			btnStart.visibleProperty().setValue(false);
		} catch (SQLException e) {
				CreateImageDB.initDB();
			e.printStackTrace();
		}
    }
    
    /*
     * Reads from the database and sets the first room images
     */
    public void buildRoom() throws SQLException{
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
        
        img1.setImage(doorImage);
        img2.setImage(chestImage);
        
    }
	public static void OpenifOpenable(Object object, ArrayList<Item> inventory){
		if(object instanceof Door){
			Openable opened = (Openable)object;
			opened.Open();
			
		}else if(object instanceof Chest){
			Openable opened = (Openable)object;
			opened.Open();
			Item i = ((Chest) opened).getItem();
			inventory.add(i);
			
		}else if(object instanceof LockedDoor){
			Openable opened = (Openable)object;
			opened.Open();
			//on click
			CheckForKey((LockedDoor)object, inventory);
			
		} else {
			System.out.println("The "+ object.getName() + " is not openable");
		}
	}

	public static void CheckForKey(LockedDoor door, ArrayList<Item> inventory){
		
		for(Item i : inventory){
			if(door.getValue()==i.getValue()){
				door.unlock();
				System.out.println("The "+ door.getName() + " Has Been Opened!");
			}
		}
	}
}