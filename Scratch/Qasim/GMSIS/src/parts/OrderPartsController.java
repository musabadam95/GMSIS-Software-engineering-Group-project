/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author muzab
 */
public class OrderPartsController implements Initializable {

    @FXML
    private ComboBox<String> foundNamesList;
    @FXML
    private TextField searchFNameTxt;
    @FXML
    private Label customerIdTxt;
    @FXML
    private Label firstNameTxt;
    @FXML
    private Label lastNameTxt;
    @FXML

    private Label VehicleTxt;
    @FXML
    private Label vehicleTypeTxt;
    @FXML
    private Label vehRegTxt;
    @FXML
    private ComboBox<String> bookedTimeDateDropDown;
    @FXML
    private Label bookedTimeTxt;
    @FXML
    private Label bookedDateTxt;
    @FXML
    private Label bookedIDTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        itemListDropDown("");
    }

    @FXML
    private void searchName(KeyEvent event) {
        itemListDropDown(searchFNameTxt.getText());
    }

    public void itemListDropDown(String name) {
        //clear combo values
        foundNamesList.getItems().clear();
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            throw new RuntimeException("Database connection failed!", ex);
        }
        String Sqlite = null;
        if (name.equals("")) {
            Sqlite = "SELECT * FROM Customer";
        } else {
            Sqlite = "SELECT * FROM Customer WHERE FirstName LIKE '%" + name + "%'";
        }
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                foundNamesList.getItems().add(rs.getString("FirstName") + "-" + rs.getString("LastName"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }

    @FXML
    private void fillData(ActionEvent event) {
        Connection connection;
        // System.out.println(foundNamesList.getValue());

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        String[] list = foundNamesList.getValue().split("-");
        String Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.VehReg=Vehicles.VehReg WHERE Customer.FirstName = '" + list[0] + "'";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);

            customerIdTxt.setText(Integer.toString(rs.getInt("CusID")));
            firstNameTxt.setText(rs.getString("FirstName"));
            lastNameTxt.setText(rs.getString("LastName"));
            VehicleTxt.setText(rs.getString("VehModel") + " " + rs.getString("VehMake"));
            vehicleTypeTxt.setText(rs.getString("VehType"));
            vehRegTxt.setText(rs.getString(10));
            bookingItemDropDownBox(rs.getString(10));
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

        //refresh table
    }

    private void bookingItemDropDownBox(String vehicleReg) {
         bookedTimeDateDropDown.getItems().clear();
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        String Sqlite = "SELECT * FROM Bookings INNER JOIN Vehicles ON Vehicles.VehReg=Bookings.VehReg WHERE Vehicles.VehReg = '" + vehicleReg + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                bookedTimeDateDropDown.getItems().add(rs.getString("BookingTime") + "---" + rs.getString("BookingDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML

    private void fillBookingData(ActionEvent event) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        String[] array = bookedTimeDateDropDown.getValue().split("---");
        String Sqlite = "SELECT * FROM Bookings WHERE BookingTime = '" + array[0] + "' AND BookingDate = '" + array[1] + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            bookedTimeTxt.setText(Integer.toString(rs.getInt("BookingTime")));
            bookedIDTxt.setText(Integer.toString(rs.getInt("BookingID")));
            bookedDateTxt.setText(Integer.toString(rs.getInt("BookingTime")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
