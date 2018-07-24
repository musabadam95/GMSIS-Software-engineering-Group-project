/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import JavafxGUI.*;
/**
 *
 * @author muzab
 */
public class partsGUIMain extends Application{   
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("JavafxGUI.FXMLDocument.fxml"));
        Scene scene = new Scene(root, 400, 500);
        stage.setTitle("Parts Menu");
        stage.setScene(scene);
        stage.show();
    } 
    public static void main(String[]args){
    Application.launch(); 
    }
}
