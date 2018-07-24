/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import common.ConnectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author Baz
 */
public class VehiclePopUpController implements Initializable {
    
    @FXML
    private Button saveBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button doneBtn;
    
    
    @FXML
    private ComboBox<String> templateDropdown;
    @FXML
    private ComboBox<String> customerDropdown;
    
    
    @FXML
    private TextField typeField;
    @FXML
    private TextField vehRegField;
    @FXML
    private TextField modField;
    @FXML
    private TextField makeField;
    @FXML
    private TextField engSizeField;
    @FXML
    private TextField fuelTypeField;
    @FXML
    private TextField colourField;
    @FXML
    private DatePicker motDate;
    @FXML
    private DatePicker lsdDate;
    @FXML
    private TextField mileField;
    @FXML
    private TextField wNameField;
    @FXML
    private TextField wAddField;
    @FXML
    private DatePicker wEndDate;
    
    
    private Database database;
    private Connection connection;
    private ConnectionDB sqlConnect;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
         //initialise the database
        
        database = Database.getInstance();
        sqlConnect = new ConnectionDB();
        
        this.templateDropDownList();
        this.customerIDDropDown();
        
        templateDropdown.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
              fillAddVehicleData();
            }
        });

        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleCancelButtonAction();
            }
        });
    }
    
    @FXML
    public void handleCancelButtonAction() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    
    public void templateDropDownList() {
        //clear combo values
        templateDropdown.getItems().clear();
        connection = sqlConnect.returnConnection();
        String Sqlite = null;
        Sqlite = "SELECT * FROM TEMPLATE";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                templateDropdown.getItems().add(rs.getString("CLASS") + "-" + rs.getString("MODEL") + "-" + rs.getString("MAKE") + "-" + rs.getString("ENGSIZE") + "-" + rs.getString("FUELTYPE"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }
    }
    
    //From Parts.java class
    public void customerIDDropDown() {
        //clear combo values
        customerDropdown.getItems().clear();
        connection = sqlConnect.returnConnection();
        String Sqlite = "SELECT * FROM Customer";
       
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                customerDropdown.getItems().add(rs.getString("CusID"));
            }
            connection.close();
        } catch (SQLException ex) {

        }

    }
    
    @FXML
    private void fillAddVehicleData() {
        connection = sqlConnect.returnConnection();
        String[] list = templateDropdown.getValue().split("-");
        String Sqlite = "SELECT * FROM TEMPLATE WHERE MAKE = '" + list[2] + "'";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);

            typeField.setText(rs.getString("CLASS"));
            modField.setText(rs.getString("MODEL"));
            makeField.setText(rs.getString("MAKE"));
            engSizeField.setText(rs.getString("ENGSIZE"));
            fuelTypeField.setText(rs.getString("FUELTYPE"));
            
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Template Selected", Sqlite, JOptionPane.ERROR_MESSAGE);
            // 
        }
        //refresh table
    }
    
    @FXML
    public void fillEditVehicleData(Vehicle o) {
        
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        vehRegField.setText(o.getVehReg());
        typeField.setText(o.getType());
        modField.setText(o.getVehModel());
        makeField.setText(o.getVehMake());
        engSizeField.setText(o.getEngSize());
        fuelTypeField.setText(o.getFuelType());
        colourField.setText(o.getColour());
        motDate.setValue(LocalDate.parse(o.getRenewalDate(), format));
        lsdDate.setValue(LocalDate.parse(o.getLastServiceDate(), format));
        mileField.setText(o.getMileage());
        wNameField.setText(o.getWarrantyName());
        wAddField.setText(o.getWarrantyAddress());
        wEndDate.setValue(LocalDate.parse(o.getWarrantyEnd(), format));
        customerDropdown.setValue("not needed");
        
    }
    
    @FXML
    private void addVehicle(ActionEvent event) {
        String query = "INSERT into Vehicles (VehReg, VehType, VehModel, VehMake, EngSize, FuelType, Colour, RenewalDate, LastServiceDate, CurrentMileage, WarrantyName, WarrantyAddress, WarrantyEnd, CustomerID)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        //create preparedStatement
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            connection = sqlConnect.returnConnection();
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setString(1, vehRegField.getText());
            prStatement.setString(2, typeField.getText());
            prStatement.setString(3, modField.getText());
            prStatement.setString(4, makeField.getText());
            prStatement.setString(5, engSizeField.getText());
            prStatement.setString(6, fuelTypeField.getText());
            prStatement.setString(7, colourField.getText());
            prStatement.setString(8, motDate.getValue().format(format));
            prStatement.setString(9, lsdDate.getValue().format(format));
            prStatement.setString(10, mileField.getText());
            prStatement.setString(11, wNameField.getText());
            prStatement.setString(12, wAddField.getText());
            prStatement.setString(13, wEndDate.getValue().format(format));
            prStatement.setString(14, customerDropdown.getValue());
            prStatement.execute();
        
            connection.close();
            handleCancelButtonAction();
        } catch (Exception e) {
            e.printStackTrace();
            switch (e.getClass().getSimpleName()) {
                case "NumberFormatException":
                    if (vehRegField.getText() == null) {
                        JOptionPane.showMessageDialog(null, "A vehicle registration is necessary.");
                    }
                    if (typeField.getText() == null) {
                        JOptionPane.showMessageDialog(null, "A vehicle type is necessary.");
                    }
                    case "NullPointerException":
                        JOptionPane.showMessageDialog(null, "Selecting dates is necessary.");
                default:
                    break;

            }
        }

    }
    
    @FXML
    private void editVehicle(ActionEvent event) {
        String query = "UPDATE Vehicles SET VehReg = ?, VehType = ?, VehModel = ?, VehMake = ?, EngSize = ?, FuelType = ?, Colour = ?, RenewalDate = ?, LastServiceDate = ?, CurrentMileage = ?, WarrantyName = ?, WarrantyAddress = ?, WarrantyEnd = ?"
                + " WHERE VehReg = ?";
        //create preparedStatement
        try {
            connection = sqlConnect.returnConnection();
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setString(1, vehRegField.getText());
            prStatement.setString(2, typeField.getText());
            prStatement.setString(3, modField.getText());
            prStatement.setString(4, makeField.getText());
            prStatement.setString(5, engSizeField.getText());
            prStatement.setString(6, fuelTypeField.getText());
            prStatement.setString(7, colourField.getText());
            prStatement.setString(8, motDate.getValue().toString());
            prStatement.setString(9, lsdDate.getValue().toString());
            prStatement.setString(10, mileField.getText());
            prStatement.setString(11, wNameField.getText());
            prStatement.setString(12, wAddField.getText());
            prStatement.setString(13, wEndDate.getValue().toString());
            prStatement.setString(14, vehRegField.getText());
            prStatement.executeUpdate();
                
            connection.close();
            handleCancelButtonAction();
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {
                case "NumberFormatException":
                    if (vehRegField.getText() == null) {
                        JOptionPane.showMessageDialog(null, "A vehicle registration is necessary.");
                    }
                    if (typeField.getText() == null) {
                        JOptionPane.showMessageDialog(null, "A vehicle type is necessary.");
                    }
                    case "NullPointerException":
                        JOptionPane.showMessageDialog(null, "Selecting dates is necessary.");
                default:
                    break;

            }
        }

    }
}
