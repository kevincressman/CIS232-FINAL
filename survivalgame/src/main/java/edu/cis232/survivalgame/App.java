package edu.cis232.survivalgame;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Hello world!
 *
 */
public class App extends Application
{    public static void main( String[] args ){
    System.out.println( "Hello World!" );
    launch(args);
}

    
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent parent = FXMLLoader.load(App.class.getResource("Game.fxml"));
		
		Scene scene = new Scene(parent);
		
		stage.setScene(scene);
		//REQ#1
		stage.setTitle("Paul and Kevin's Survival Game!");	
		stage.show();

	}
}

    
    
