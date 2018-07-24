/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author muzab
 */
public class LoginScreenMain extends Application{   
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("loginScreen.fxml"));
        Scene scene = new Scene(root, 400, 264);
        stage.setTitle("Welcome to GM-SIS");
        stage.setScene(scene);
        stage.show();
    } 
    public static void main(String[]args){
    Application.launch(); 
    }
}
