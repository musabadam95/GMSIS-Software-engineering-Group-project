package JavafxGUI;

import common.ConnectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private Button addBtn;
    @FXML
    private TextField uIDTxt;
    @FXML
    private TextField passwordTxt;
    @FXML
    private CheckBox adminBox;
    private Connection connection;
    ConnectionDB sqlConnect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sqlConnect = new ConnectionDB();
        refreshUserTable();
    }

    public void refreshUserTable() {
        //populate tableview using data from database
        ObservableList<User> data = FXCollections.<User>observableArrayList();
        String Sqlite = "SELECT * FROM `User`";
        ResultSet rs;
        try {
              connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
             st.setQueryTimeout(10);
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
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
      
        //create query to delete specified User by ID
        String query = " DELETE FROM User where UserId = ?";
        //create preparedStatement
        try {
              connection = sqlConnect.returnConnection();
            PreparedStatement prStatement = connection.prepareStatement(query);
             prStatement.setQueryTimeout(10);
            prStatement.setInt(1, Integer.parseInt(uIDTxt.getText()));
            prStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            System.out.println("error");
        }
        refreshUserTable();
    }

    @FXML
    private void editUser(ActionEvent event) {
        
        int adminDigit = 0;
        if (adminBox.isSelected()) {
            adminDigit = 1;
        }
        String SQL = "UPDATE User SET Name=?,Surname=?,UserType=? WHERE UserID=?";
        try {
            connection = sqlConnect.returnConnection();
            PreparedStatement prStatement = connection.prepareStatement(SQL);
             prStatement.setQueryTimeout(10);
            prStatement.setString(1, firstNameTxt.getText());
            prStatement.setString(2, lastNameTxt.getText());
            prStatement.setInt(3, adminDigit);
            prStatement.setInt(4, Integer.parseInt(uIDTxt.getText()));
            prStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        refreshUserTable();
    }

    @FXML
    private void changePassword(ActionEvent event) {
        if (passwordTxt.getText() == null || passwordTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter the old Password");
        } else {
            boolean flag=true;
            int check=-1;
           
            //create query for the specified username and password
            String Sqlite = "SELECT * FROM `User` WHERE UserID= " + "'" + firstNameTxt.getText() + "' AND Password= '" + passwordTxt.getText() + "'";
            ResultSet rs;
            try {
                 connection = sqlConnect.returnConnection();
                Statement st = connection.createStatement();
                 st.setQueryTimeout(10);
                rs = st.executeQuery(Sqlite);
                if (rs.getString("UserID").equals(firstNameTxt.getText()) && rs.getString("password").equals(passwordTxt.getText())) {
                    int result = rs.getInt("UserType");
                    connection.close();
                    if (result == -1) {
                        JOptionPane.showMessageDialog(null, "Wrong Password");
                        return;
                    }
                }
            } catch (SQLException e) {

            }
            String newPassword = null;
            String query = null;
            try {
                    query = " UPDATE User set Password = ? where Name = ? AND Password = ?";
                    //create preparedStatement
                    newPassword = JOptionPane.showInputDialog("Enter New Password");    
            } catch (Exception e) {
                flag = false;
            }
            if (newPassword == null || newPassword.equals("")) {
                flag = false;
            } else {
                try {
                    connection = sqlConnect.returnConnection();
                    PreparedStatement prStatement = connection.prepareStatement(query);
                     prStatement.setQueryTimeout(10);
                    prStatement.setString(1, newPassword);
                    prStatement.setString(2, firstNameTxt.getText());
                    prStatement.setString(3, passwordTxt.getText());
                    prStatement.execute();
                    connection.close();
                } catch (Exception ex) {
                    flag = false;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "Success");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to change password");
            }
        }
    }

    @FXML
    private void addUser(ActionEvent event) {
        int flag;
        if (firstNameTxt.getText().equals("") || lastNameTxt.getText().equals("") || passwordTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Please check if all requered fields have been filled in");
        } else {
            if (adminBox.isSelected()) {
                flag = 1;
            } else {
                flag = 0;
            }
            
        //set userType

        String query = " insert into User (Name, Surname, Password, UserType)"
                + " values (?, ?, ?, ?)";
        //create preparedStatement
        try {
            
             connection = sqlConnect.returnConnection();
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setQueryTimeout(10);
            prStatement.setString(1, firstNameTxt.getText());
            prStatement.setString(2, lastNameTxt.getText());
            prStatement.setString(3, passwordTxt.getText());
            prStatement.setInt(4, flag);
            prStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
            refreshUserTable();
        }
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
