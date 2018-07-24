package vehicles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Baz
 */
public class Main extends  Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vehicles/Vehicles.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Vehicles Menu");
        stage.setScene(scene);
        stage.show();
    } 
    
    public static void main(String[]args) {
        launch(args); 
    }
    
}