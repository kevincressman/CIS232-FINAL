package edu.cis232.survivalgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class MainApplication extends Application{
	
    public static void main( String[] args ){
        System.out.println( "Hello World!" );
        launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent parent = FXMLLoader.load(MainApplication.class.getResource("Coin.fxml"));
		
		Scene scene = new Scene(parent);
		
		stage.setScene(scene);
		
		stage.setTitle("Paul and Kevin's Survival Game!");
		stage.show();
	}
}
