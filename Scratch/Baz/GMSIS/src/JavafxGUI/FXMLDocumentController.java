/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 

package JavafxGUI;


import common.Authentication;
import common.userModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import parts.OrderPartsController;

/**
 *
 * @author louiraj
 
public class FXMLDocumentController implements Initializable {

   public TabPane Tabs;
    @FXML
    private TextField usernameTxt;
    @FXML
    private PasswordField passwordTxt;
    @FXML
    private Label output;
    @FXML
    private Tab sPTab;
    @FXML
    private Tab customerTab;
    @FXML
    private Tab bookingTab;
    @FXML
    private Tab orderPartTab;
    @FXML
    private Tab stockTab;
    @FXML
    private Button loginBtn;

    private userModel getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser the currentUser to set
     
    private void setCurrentUser(userModel currentUser) {
        this.currentUser = currentUser;
    }

    private Label label;
    @FXML
    private  Tab adminTab;
    private  userModel currentUser;
    Authentication sc1 ;
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      sc1 = new Authentication();
    }

    public  void enableTab(int usertype) throws IOException {

//if you have added a new tab, please insert the new tab onto the if statements and set disable to false
        if (usertype == 1) {//user is admin
            stockTab.setDisable(false);
            adminTab.setDisable(false);
            sPTab.setDisable(false);
            customerTab.setDisable(false);
            bookingTab.setDisable(false);
            orderPartTab.setDisable(false);
            //insert newtab.setDisable(false);
        } else {//user is system user
               sPTab.setDisable(false);
            customerTab.setDisable(false);
            bookingTab.setDisable(false);
            orderPartTab.setDisable(false);
            stockTab.setDisable(false);
            //insert newtab.setDisable(false);
        }
    }

    @FXML
    private void onButtonClick(ActionEvent event) throws IOException {

         String username=usernameTxt.getText();
         String password=passwordTxt.getText();
         String result=sc1.login(username,password);
         if(!result.equals("false")){
          output.setText("Status:Success");
          String []resultXp=result.split("-");
             setCurrentUser(new userModel(resultXp[0],resultXp[1],Integer.parseInt(resultXp[2])));
        enableTab(Integer.parseInt(resultXp[2]));
         }else{
           output.setText("Status:Fail");

         }
    }

    @FXML
    private void refreshOrderPartsTab(Event event) {
       // ne.refreshAll();
    }

    @FXML
    private void refreshStockTab(Event event) {
    }
}
*/