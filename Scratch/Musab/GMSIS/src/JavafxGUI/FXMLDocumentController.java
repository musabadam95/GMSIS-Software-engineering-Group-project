package JavafxGUI;

import common.ConnectionDB;
import common.userModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author louiraj
 */
public class FXMLDocumentController implements Initializable {
    private ConnectionDB sqlConnect;
        private Connection connection;
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
    @FXML
    private Tab loginTab;
    @FXML
    private Button logOutBtn;
    @FXML
    private Label currentUserName;
    @FXML
    private Tab vehicleTab;

    private userModel getCurrentUser() {
        return currentUser;
    }

    private void setCurrentUser(userModel currentUser) {
        this.currentUser = currentUser;
    }

    private Label label;
    @FXML
    private Tab adminTab;
    private userModel currentUser;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          sqlConnect = new ConnectionDB();
    }

    public void enableTab(int usertype) throws IOException {

//if you have added a new tab, please insert the new tab onto the if statements and set disable to false
        if (usertype == 1) {//user is admin
            stockTab.setDisable(false);
            adminTab.setDisable(false);
            sPTab.setDisable(false);
            customerTab.setDisable(false);
            bookingTab.setDisable(false);
            orderPartTab.setDisable(false);
            vehicleTab.setDisable(false);
        } else {//user is system user
            sPTab.setDisable(false);
            customerTab.setDisable(false);
            bookingTab.setDisable(false);
            orderPartTab.setDisable(false);
            stockTab.setDisable(false);
              vehicleTab.setDisable(false);
            //insert newtab.setDisable(false);
        }
        logOutBtn.setDisable(false);
    }

    @FXML
    private void onButtonClick(ActionEvent event) throws IOException {
 connection = sqlConnect.returnConnection();
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String result = login(username, password);
        
        if (!result.equals("false")) {
               String Sqlite = "SELECT * FROM `User` WHERE UserID= " + "'" + username + "' AND Password= '" + password + "'";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            usernameTxt.clear();
            passwordTxt.clear();
            setCurrentUser(new userModel(rs.getString("Name"),rs.getString("Surname") ,rs.getInt("UserType")));
           if(currentUser.getUserType()==1){
           currentUserName.setText(rs.getString("Name") + ": Administrator");
           }else{
             currentUserName.setText(rs.getString("Name") + ": System User");
           }
            enableTab(rs.getInt("UserType"));
            Tabs.getSelectionModel().select(3);
            loginTab.setDisable(true);
            connection.close();
        }catch(Exception e){}
        } else {
            output.setText("Status:Fail");
        }
    }
    @FXML
    private void logOut(ActionEvent event) {
        currentUserName.setText("Please Login");
        Tabs.getSelectionModel().select(0);
        loginTab.setDisable(false);
        stockTab.setDisable(true);
        adminTab.setDisable(true);
        sPTab.setDisable(true);
        customerTab.setDisable(true);
        bookingTab.setDisable(true);
        orderPartTab.setDisable(true);
          vehicleTab.setDisable(true);
    }

    private String login(String username, String password) {
      connection = sqlConnect.returnConnection();
            //create query for the specified username and password
            String Sqlite = "SELECT * FROM `User` WHERE UserID= " + "'" + usernameTxt.getText() + "' AND Password= '" + passwordTxt.getText() + "'";
            ResultSet rs;
            try {
                Statement st = connection.createStatement();
                 st.setQueryTimeout(10);
                rs = st.executeQuery(Sqlite);
                if (rs.getString("UserID").equals(passwordTxt.getText()) && rs.getString("password").equals(passwordTxt.getText())) {
                    int result = rs.getInt("UserType");
                    connection.close();
                    if (result == -1) {
                        JOptionPane.showMessageDialog(null, "Wrong Password");
                  return "false";
                    }
                }
            } catch (SQLException e) {

            }
        return "true";
        
        
    }
}
