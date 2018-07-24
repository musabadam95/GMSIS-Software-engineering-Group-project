/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author muzab
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;
 Authentication sc1;
    @FXML
    private Label output;
    /**
     * Initializes the controller class.
     */
    @Override
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	 sc1=new Authentication();
              
    }    

    @FXML
    private void onButtonClick(ActionEvent event) {
        String username=usernameTxt.getText();
         String password=passwordTxt.getText();
         if(sc1.login(username,password)){
          output.setText("Status:Success");
        
         }else{
           output.setText("Status:Fail");
         
         }
        
    }
    
}
