/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import common.ConnectionDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private Label lastNameTxt1;
    @FXML
    private ComboBox<String> partListDropDown;
    @FXML
    private Button addPrtBtn;
    @FXML
    private Button deletePrtBtn;
    @FXML
    private Button changePrtBtn;
    private Connection connection;
    @FXML
    private TableView<Parts> bookPartList;
    @FXML
    private TableColumn colPartId;
    @FXML
    private TableColumn colPartName;
    @FXML
    private TableColumn colCost;
    @FXML
    private TableColumn colDateInstal;
    @FXML
    private TableColumn colWarranty;
    ConnectionDB sqlConnect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sqlConnect = new ConnectionDB();
        userListDropDown("");
        partListDropDown();
    }

    @FXML
    public void refreshAll() {
        foundNamesList.getItems().clear();
        partListDropDown.getItems().clear();
        userListDropDown("");
        partListDropDown();

    }

    @FXML
    private void searchName(KeyEvent event) {
        userListDropDown(searchFNameTxt.getText());
    }

    public void userListDropDown(String name) {
        //clear combo values
        foundNamesList.getItems().clear();
        connection = sqlConnect.returnConnection();
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

    public void partListDropDown() {
        //clear combo values
        //partListDropDown.getItems().clear();
        connection = sqlConnect.returnConnection();
        String Sqlite = null;

        Sqlite = "SELECT * FROM PartStock";

        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                partListDropDown.getItems().add(rs.getString("PartName") + "-" + rs.getInt("Price"));
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }

    @FXML
    private void fillData(ActionEvent event) {
        connection = sqlConnect.returnConnection();
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
        connection = sqlConnect.returnConnection();
        String Sqlite = "SELECT * FROM Bookings INNER JOIN Vehicles ON Vehicles.VehReg=Bookings.VehReg WHERE Vehicles.VehReg = '" + vehicleReg + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                bookedTimeDateDropDown.getItems().add(rs.getString("BookingTime") + "---" + rs.getString("BookingDate"));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML

    private void fillBookingData(ActionEvent event) throws SQLException, ParseException {
        connection = sqlConnect.returnConnection();
        String[] array = bookedTimeDateDropDown.getValue().split("---");
        String Sqlite = "SELECT * FROM Bookings WHERE BookingTime = '" + array[0] + "' AND BookingDate = '" + array[1] + "'";
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            bookedTimeTxt.setText(Integer.toString(rs.getInt("BookingTime")));
            bookedIDTxt.setText(Integer.toString(rs.getInt("BookingID")));
            
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
           
            bookedDateTxt.setText( rs.getString("BookingDate"));

            refreshPartTable(rs.getString("VehReg"), rs.getInt("BookingID"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    private void refreshPartTable(String vReg, int bID) {
        connection = sqlConnect.returnConnection();
        //populate tableview using data from database
        ObservableList<Parts> data = FXCollections.<Parts>observableArrayList();
        String Sqlite = "SELECT * FROM `InstalledParts` INNER JOIN Bookings ON InstalledParts.VehReg=Bookings.VehReg WHERE InstalledParts.VehReg='" + vReg + "' AND Bookings.BookingID=" + bID;
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                data.add(new Parts(rs.getInt(1), rs.getString("PartName"), rs.getDate("DateInstalled"), rs.getDate("WarrantyEndDate"), rs.getInt("Cost")));
            }
            colPartId.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartID"));
            colPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartName"));
            colDateInstal.setCellValueFactory(new PropertyValueFactory<Parts, String>("DateInstalled"));
            colCost.setCellValueFactory(new PropertyValueFactory<Parts, String>("Cost"));
            colWarranty.setCellValueFactory(new PropertyValueFactory<Parts, String>("Warranty"));
            bookPartList.setItems(data);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

    }

    @FXML
    private void addPart(ActionEvent event) {
        partListDropDown.getValue();
        connection = sqlConnect.returnConnection();
        try{
        
      String query=" insert into InstalledParts (VehReg, PartName, Password, UserType)"
        + " values (?, ?, ?, ?)";
        //create preparedStatement
        try{
        PreparedStatement prStatement=connection.prepareStatement(query);
        prStatement.setString(1, );
        prStatement.setString(2, );
        prStatement.setString(3, );
        prStatement.setInt(4,1);
        prStatement.execute();
        connection.close();
        }catch(SQLException ex){
        System.err.println("error");
        }
        
        }
        catch(SQLException e){
        
        
        }
    }

    @FXML
    private void deletePart(ActionEvent event) {
    }

    @FXML
    private void editPart(ActionEvent event) {
    }

}
