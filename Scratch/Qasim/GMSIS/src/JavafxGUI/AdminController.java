/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import common.UserRegistry;
import common.userModel;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import parts.Parts;

/**
 * FXML Controller class
 *
 * @author muzab
 */
public class AdminController implements Initializable {

    @FXML
    private TextField firstNameTxt;
    @FXML
    private TextField lastNameTxt;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn colUserID;
    @FXML
    private TableColumn colFirstName;
    @FXML
    private TableColumn colLastName;
    @FXML
    private TableColumn colUserType;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button newPassBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    @FXML
    private TextField uIDTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private CheckBox adminBox;
    @FXML
    private TextField newPasswordTxt;
    private UserRegistry connectUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connectUser = UserRegistry.getInstance();
        refreshUserTable();
    }

    public void refreshUserTable() {
        //connects to database
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        //populate tableview using data from database
        ObservableList<User> data = FXCollections.<User>observableArrayList();
        String Sqlite = "SELECT * FROM `User`";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                System.out.println(rs.getString("Name"));
                data.add(new User(rs.getInt("userID"), rs.getString("Name"), rs.getString("Surname"), rs.getInt("UserType")));
            }
            colUserID.setCellValueFactory(new PropertyValueFactory<User, String>("UserID"));
            colFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
            colLastName.setCellValueFactory(new PropertyValueFactory<User, String>("Surname"));
            colUserType.setCellValueFactory(new PropertyValueFactory<User, String>("UserType"));
            userTable.setItems(data);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

    }

    @FXML
    private void deleteUser(ActionEvent event) {
       connectUser = UserRegistry.getInstance();
        connectUser.deleteUser(Integer.parseInt(uIDTxt.getText()));
        refreshUserTable();
    }

    @FXML
    private void editUser(ActionEvent event) {
      
       connectUser.editUser(Integer.parseInt(uIDTxt.getText()),firstNameTxt.getText(),lastNameTxt.getText() , adminBox.isSelected());
 refreshUserTable();
    }

    @FXML
    private void changePassword(ActionEvent event) {
        connectUser = UserRegistry.getInstance();
        if (passwordTxt.getText() == null) {
            JOptionPane.showMessageDialog(null, "Please enter the old Password");
        } else if (newPasswordTxt.getText() == null) {
            JOptionPane.showMessageDialog(null, "Please enter the old Password");
        } else {
            connectUser = UserRegistry.getInstance();
            boolean flag = connectUser.updatePassword(firstNameTxt.getText(), passwordTxt.getText(), newPasswordTxt.getText());
            if (flag) {
                JOptionPane.showMessageDialog(null, "Success");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to change password");
            }
        }
    }

    @FXML
    private void addUser(ActionEvent event) {
        connectUser = UserRegistry.getInstance();
        connectUser.addUser(firstNameTxt.getText(), lastNameTxt.getText(), passwordTxt.getText(), adminBox.isSelected());
        refreshUserTable();
    }

    @FXML
    private void fillData(MouseEvent event) {
        firstNameTxt.setText(userTable.getSelectionModel().getSelectedItem().getName());
        lastNameTxt.setText(userTable.getSelectionModel().getSelectedItem().getSurname());
        int ID = userTable.getSelectionModel().getSelectedItem().getUserType();
        if (ID == 0) {
            adminBox.setSelected(false);
        } else {
            adminBox.setSelected(true);

        }
        uIDTxt.setText(String.valueOf(userTable.getSelectionModel().getSelectedItem().getUserID()));

    }

}
