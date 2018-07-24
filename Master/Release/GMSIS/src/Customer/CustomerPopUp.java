

package Customer;

import common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.sql.PreparedStatement;

/**
 * Created by Romade on 28/02/2017.
 */


public class CustomerPopUp implements Initializable
{


    @FXML
    private TextField custIdFld;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField address1Field;
    @FXML
    private TextField address2Field;
    @FXML
    private TextField postCodeField;
    @FXML
    private TextField phoneNumberField;
    @FXML
    private TextField emailField;

    @FXML
    private Button createBtn;
    @FXML
    private Button saveBtn;
    @FXML
     private ChoiceBox<String> typeChoice;

    ConnectionDB sqlConnect = new ConnectionDB();
    private Connection connection;



    private Stage stage = new Stage();
    private Customer customer;
    private boolean done = false;






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //stage.showAndWait();
        ObservableList<String> typeData = FXCollections.observableArrayList("Business", "Individual");
        typeChoice.setItems(typeData);
    }

    public void setStage(Stage s){

        stage = s;
    }

    public void setCustomer(Customer customer){

        //customer = c;
        this.customer = customer;

        //custIdFld.setText(Integer.toString(customer.getId()));
        firstNameField.setText(customer.getFirstName());
        lastNameField.setText(customer.getLastName());
        address1Field.setText(customer.getFirstLineAdd());
        address2Field.setText(customer.getSecondLineAdd());
        postCodeField.setText(customer.getPostCode());
        phoneNumberField.setText(customer.getPhone());
        emailField.setText(customer.getEmail());
        typeChoice.setValue(customer.getType());
    }

    @FXML
    private void handleCancel(){

        stage.close();
    }

    private boolean validInputs(){ //Validate all inputs
        //if(custIdFld.getText().isEmpty() || custIdFld.getText().matches(".*[a-zA-Z].*")){
                //return false;
        //}
         if(firstNameField.getText().isEmpty() || firstNameField.getText().matches(".*[0-9].*")){
             Alert fAlert = new Alert(Alert.AlertType.WARNING);
             fAlert.setTitle("Warning");
             fAlert.setHeaderText(null);
             fAlert.setContentText("Please enter correct details");
             fAlert.showAndWait();
            return false;
        } else if(lastNameField.getText().isEmpty() || lastNameField.getText().matches(".*[0-9].*")){
             Alert lAlert = new Alert(Alert.AlertType.WARNING);
             lAlert.setTitle("Warning");
             lAlert.setHeaderText(null);
             lAlert.setContentText("Enter correct details");
             lAlert.showAndWait();
             return false;
        } else if(emailField.getText().isEmpty() || !emailField.getText().matches(".*[@].*")){
             Alert eAlert = new Alert(Alert.AlertType.WARNING);
             eAlert.setTitle("Warning");
             eAlert.setHeaderText(null);
             eAlert.setContentText("Enter correct email");
             eAlert.showAndWait();
            return false;
        }else if (address1Field.getText().isEmpty() || address1Field.getText().matches(".*[@!*£$();:./_+=%^'].*")) {

             Alert addAlert = new Alert(Alert.AlertType.WARNING);
             addAlert.setTitle("Warning");
             addAlert.setHeaderText(null);
             addAlert.setContentText("Enter correct address");
             addAlert.showAndWait();
            return false;
        }else if (address2Field.getText().isEmpty() || address2Field.getText().matches(".*[@!*£$();:./_+=%^'].*")){
            return false;
        }else if (postCodeField.getText().isEmpty() || postCodeField.getText().matches(".*[@!*£$();:./_+=%^'].*")){

             Alert pAlert = new Alert(Alert.AlertType.WARNING);
             pAlert.setTitle("Warning");
             pAlert.setHeaderText(null);
             pAlert.setContentText("Enter correct Postcode");
             pAlert.showAndWait();
            return false;
        }else if (phoneNumberField.getText().isEmpty() || phoneNumberField.getText().matches(".*[a-zA-Z].*")){

             Alert phAlert = new Alert(Alert.AlertType.WARNING);
             phAlert.setTitle("Warning");
             phAlert.setHeaderText(null);
             phAlert.setContentText("Enter correct Phone Number");
             phAlert.showAndWait();
            return false;
        }else if(typeChoice.getSelectionModel().getSelectedItem().isEmpty()){
            return false;
        }


        return true;
    }

    public boolean isDone(){

        return done;
    }

    @FXML
    private void handleCreate() throws SQLException{
        if(validInputs()){
            //Load data into the database

            try{
               connection = sqlConnect.returnConnection();

                //Statement st = connection.prepareStatement();
                String Sqlite = "INSERT INTO Customer(FirstName , LastName , FirstLineAdd , SecondLineAdd , PostCode , PhoneNo , Email, BusinessType)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


                PreparedStatement st = connection.prepareStatement(Sqlite);
//                st.setString(1, (Integer.toString(customer.getId())));
                st.setString(1, firstNameField.getText());
                st.setString(2, lastNameField.getText());
                st.setString(3, address1Field.getText());
                st.setString(4, address2Field.getText());
                st.setString(5, postCodeField.getText());
                st.setString(6, phoneNumberField.getText());
                st.setString(7, emailField.getText());
                st.setString(8, typeChoice.getSelectionModel().getSelectedItem());

                // execute the preparedstatement insert
                st.executeUpdate();
                connection.close();

            }
            catch (SQLException ex)
            {
                // log exception
                throw ex;
            }
            done = true;
            stage.close();
        }

    }

    @FXML
    private void handleSave()throws SQLException{
        if (validInputs()){


            try{
                            connection = sqlConnect.returnConnection();

                String saveQuery = "UPDATE CUSTOMER SET FirstName=?, LastName = ?, FirstLineAdd = ?, SecondLineAdd = ?, PostCode = ?, PhoneNo= ?, Email= ?, BusinessType=? WHERE CusID =?";

                PreparedStatement st = connection.prepareStatement(saveQuery);
                st.setString(1, firstNameField.getText());
                st.setString(2, lastNameField.getText());
                st.setString(3, address1Field.getText());
                st.setString(4, address2Field.getText());
                st.setString(5, postCodeField.getText());
                st.setString(6, phoneNumberField.getText());
                st.setString(7, emailField.getText());
                st.setString(8, typeChoice.getSelectionModel().getSelectedItem());
                st.setInt(9, customer.getId());

                st.executeUpdate();
                connection.close();
            }catch (SQLException ex)
            {
                throw ex;
            }
            done = true;
            stage.close();
        }

    }

    public Button getSaveBtn() {
        return saveBtn;
    }

    public Button getCreateBtn() {
        return createBtn;
    }


}
