/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import parts.*;
import Booking.*;
import common.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.ResourceBundle;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author Baz
 */
public class VehicleGUIController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    //FXML variables for table and collumns
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableView<Parts> partsTable;
    @FXML
    private TableView<Booking> bookingsTable;
    
    @FXML
    private TableColumn<Vehicle,String> vTypeCol,vRegCol,vModCol,vMakeCol,vEngCol,vFuelCol,vColourCol,vMotCol,vLSDCol,vMileCol,vWNCol,vWACol,vWECol;
    
    
    @FXML
    private Button addVehBtn;
    @FXML
    private Button delVehBtn;
    @FXML
    private Button editVehBtn;
    @FXML
    private Button refreshBtn;
    @FXML
    private Button selectBtn;
   
    
    @FXML
    private TextField searchVehRegTxt;
    
    
    @FXML
    private TableColumn pVRegCol;
    @FXML
    private TableColumn pIDCol;
    @FXML
    private TableColumn pNameCol;
    @FXML
    private TableColumn pDescCol;
    @FXML
    private TableColumn pDateCol;
    @FXML
    private TableColumn pWEDCol;
    @FXML
    private TableColumn pCostCol;
    
    
    @FXML
    private TableColumn bVRegCol;
    @FXML
    private TableColumn bIDCol;
    @FXML
    private TableColumn bDurCol;
    @FXML
    private TableColumn bTimeCol;
    @FXML
    private TableColumn bTypeCol;
    @FXML
    private TableColumn bDateCol;

    //Observable list for updating tables with data
    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
    
    private Database database;
    private Connection connection;
    private ConnectionDB sqlConnect;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialise the database
        
        database = Database.getInstance();
        sqlConnect = new ConnectionDB();
        
        this.initialiseVehicleTable();
        this.initialisePartsTable();
        this.initialiseBookingsTable();
        
        addVehBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleAddButton();
            }
        });
        
        editVehBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleEditButton();
            }
        });
        
        refreshBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                refreshTable();
            }
        });
        
        delVehBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteVehicle();
                refreshTable();
            }
        });
        
        selectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showParts();
                showBookings();
            }
        });
    }    
    
    private void initialiseVehicleTable(){
        
        vTypeCol.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        vRegCol.setCellValueFactory(cellData -> cellData.getValue().getVehRegProperty());
        vModCol.setCellValueFactory(cellData -> cellData.getValue().getVehModelProperty());
        vMakeCol.setCellValueFactory(cellData -> cellData.getValue().getVehMakeProperty());
        vEngCol.setCellValueFactory(cellData -> cellData.getValue().getEngSizeProperty());
        vFuelCol.setCellValueFactory(cellData -> cellData.getValue().getFuelTypeProperty());
        vColourCol.setCellValueFactory(cellData -> cellData.getValue().getColourProperty());
        vMotCol.setCellValueFactory(cellData -> cellData.getValue().getRenewalDateProperty());
        vLSDCol.setCellValueFactory(cellData -> cellData.getValue().getLastServiceDateProperty());
        vMileCol.setCellValueFactory(cellData -> cellData.getValue().getMileageProperty());
        vWNCol.setCellValueFactory(cellData -> cellData.getValue().getWarrantyNameProperty());
        vWACol.setCellValueFactory(cellData -> cellData.getValue().getWarrantyAddressProperty());
        vWECol.setCellValueFactory(cellData -> cellData.getValue().getWarrantyEndProperty());
        this.updateVehicleTable();
    }
    private void updateVehicleTable(){

          vehicleData = database.getAllVehicles();     
          vehicleTable.setItems(vehicleData);
        
    }
    
    private void initialisePartsTable(){
        
        String Sqlite = "SELECT * FROM 'InstalledParts'";        
        ObservableList<Parts> allParts = FXCollections.<Parts>observableArrayList();
        
        ResultSet rs;
        Statement st;

        try {
            connection = sqlConnect.returnConnection();
            st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                
                allParts.add(
                    new Parts(
                        rs.getString("VehReg"),
                        rs.getInt("PartsID"),
                        rs.getString("PartName"),
                        rs.getString("Description"),
                        rs.getString("DateInstalled"),
                        rs.getString("WarrantyEndDate"),
                        rs.getInt("Cost")
                    )
                );

            }
        
        pVRegCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("vehReg"));
        pIDCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartID"));
        pNameCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("partName"));
        pDescCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("Description"));
        pDateCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("DateInstalled"));
        pWEDCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("Warranty"));
        pCostCol.setCellValueFactory(new PropertyValueFactory<Parts, String>("cost"));
        partsTable.setItems(allParts);
        rs.close();
        st.close();
        connection.close();
        
        }
        catch (SQLException ex) {
            
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        
    }
    
    private void initialiseBookingsTable(){
        
        ObservableList<Booking> allBookings = FXCollections.<Booking>observableArrayList();
        String Sqlite = "SELECT * FROM Bookings";
        ResultSet rs;

        try {
            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                
                allBookings.add(
                    new Booking(
                        rs.getString("VehicReg"),
                        rs.getInt("BookingID"),
                        rs.getInt("Duration"),
                        rs.getString("BookingTime"),
                        rs.getString("BookingType"),
                        rs.getString("BookingDate")
                    )
                );

            }
        
        bVRegCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehReg"));
        bIDCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingID"));
        bDurCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("bookingDur"));
        bTimeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingTime"));
        bTypeCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingType"));
        bDateCol.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingDate"));
        bookingsTable.setItems(allBookings);
        connection.close();
        
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
    }
    
    public void handleAddButton() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addVehicles.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.setTitle("Add Vehicle");
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
        }   
    }
    
    public void handleEditButton() {
        
        Vehicle selectedItem = vehicleTable.getSelectionModel().getSelectedItem();
        String Sqlite = "SELECT * FROM Vehicles WHERE VehReg = '" + selectedItem.getVehRegProperty() + "'";
        /*
        try {   
            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);

            vehRegField.setText(rs.getString("VehReg"));
            typeField.setText(rs.getString("VehType"));
            modField.setText(rs.getString("VehModel"));
            makeField.setText(rs.getString("VehMake"));
            engSizeField.setText(rs.getString("EngSize"));
            fuelTypeField.setText(rs.getString("FuelType"));
            colourField.setText(rs.getString("Colour"));
            //motDate.setText(rs.getString("RenewalDate"));
            //lsdDate.setText(rs.getString("LastServiceDate"));
            mileField.setText(rs.getString("CurrentMileage"));
            wNameField.setText(rs.getString("WarrantyName"));
            wAddField.setText(rs.getString("WarrantyAddress"));
            //wEndDate.setText(rs.getString("WarrantyEnd"));
            
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No Vehicle Selected", Sqlite, JOptionPane.ERROR_MESSAGE);
        }
        */

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editVehicles.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                VehiclePopUpController vcontroller = fxmlLoader.<VehiclePopUpController>getController();
                vcontroller.fillEditVehicleData(selectedItem);
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.setTitle("Edit Vehicle");
                stage.showAndWait();
                
        } catch(Exception e) {
           e.printStackTrace();
        }
    }
    
    @FXML
    private void searchVehReg() {
        System.out.println(1);
        System.out.println(searchVehRegTxt.getText());
        searchVehicleTable(searchVehRegTxt.getText());
    }
    
    public void searchVehicleTable(String vr) {
        //clear combo values
        vehicleTable.getItems().clear();

        PreparedStatement st;
        String Sqlite = null;
        try {
            connection = sqlConnect.returnConnection();
            if (vr.equals("")) {
                Sqlite = "SELECT * FROM Vehicles;";
                st = connection.prepareStatement(Sqlite);
            } else {
                Sqlite = "SELECT * FROM Vehicles WHERE VehReg like ?;";
                st = connection.prepareStatement(Sqlite);
                st.setString(1,"%"+vr+"%");
            }
            
            ResultSet rs;
       
            rs = st.executeQuery();
            while (rs.next()) {
                vehicleTable.getItems().add(
                    new Vehicle(
                        rs.getString("VehType"),
                        rs.getString("VehReg"),
                        rs.getString("VehModel"),
                        rs.getString("VehMake"),
                        rs.getString("EngSize"),
                        rs.getString("FuelType"),
                        rs.getString("Colour"),
                        rs.getString("RenewalDate"),
                        rs.getString("LastServiceDate"),
                        rs.getString("CurrentMileage"),
                        rs.getString("WarrantyName"),
                        rs.getString("WarrantyAddress"),
                        rs.getString("WarrantyEnd")
                    )
                ); 
            }
        connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }
    
    private void deleteVehicle() {
        String answer = JOptionPane.showInputDialog("Would you like to delete this entry?(Y/N)");
        if (answer.equals("Y") || answer.equals("y")) {
            Vehicle selectedItem = vehicleTable.getSelectionModel().getSelectedItem();
            vehicleTable.getItems().remove(selectedItem);
            try {
                connection = sqlConnect.returnConnection();
                PreparedStatement st1 = connection.prepareStatement("DELETE FROM Vehicles WHERE Vehicles.VehReg = ?");
                st1.setString(1, selectedItem.getVehReg() );
                st1.executeUpdate();
                st1.close();
                connection.close();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else {
            return;
        }
    }
    
    public void refreshTable() {
        //clear combo values
        vehicleTable.getItems().clear();
        PreparedStatement st;
        String Sqlite = "SELECT * FROM Vehicles";
        try {
            connection = sqlConnect.returnConnection();
            st = connection.prepareStatement(Sqlite);
            
            ResultSet rs;
       
            rs = st.executeQuery();
            while (rs.next()) {
                vehicleTable.getItems().add(
                    new Vehicle(
                        rs.getString("VehType"),
                        rs.getString("VehReg"),
                        rs.getString("VehModel"),
                        rs.getString("VehMake"),
                        rs.getString("EngSize"),
                        rs.getString("FuelType"),
                        rs.getString("Colour"),
                        rs.getString("RenewalDate"),
                        rs.getString("LastServiceDate"),
                        rs.getString("CurrentMileage"),
                        rs.getString("WarrantyName"),
                        rs.getString("WarrantyAddress"),
                        rs.getString("WarrantyEnd")
                    )
                ); 
            }
        connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }
    
    public void showParts() {
        //clear combo values
        partsTable.getItems().clear();
        Vehicle selectedItem = vehicleTable.getSelectionModel().getSelectedItem();
        String Sqlite = "SELECT * FROM 'InstalledParts' INNER JOIN Vehicles ON InstalledParts.VehReg = Vehicles.VehReg WHERE InstalledParts.VehReg = '" + selectedItem.getVehReg()+"'";

        try {
            connection = sqlConnect.returnConnection();
            PreparedStatement st;
            ResultSet rs;
       
            st = connection.prepareStatement(Sqlite);
            rs = st.executeQuery();
            while (rs.next()) {
                partsTable.getItems().add(
                    new Parts(
                        rs.getString(2),
                        rs.getInt("PartsID"),
                        rs.getString("PartName"),
                        rs.getString("Description"),
                        rs.getString("DateInstalled"),
                        rs.getString("WarrantyEndDate"),
                        rs.getInt("Cost")
                    )
                ); 
            }
        connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }
    
    public void showBookings() {
        //clear combo values
        bookingsTable.getItems().clear();
        Vehicle selectedItem = vehicleTable.getSelectionModel().getSelectedItem();
        String Sqlite = "SELECT * FROM 'Bookings' INNER JOIN Vehicles ON Bookings.VehicReg = Vehicles.VehReg WHERE Bookings.VehicReg = '" + selectedItem.getVehReg()+"'";

        try {
            connection = sqlConnect.returnConnection();
            PreparedStatement st;
            ResultSet rs;
       
            st = connection.prepareStatement(Sqlite);
            rs = st.executeQuery();
            while (rs.next()) {
                bookingsTable.getItems().add(
                    new Booking(
                        rs.getString("VehicReg"),
                        rs.getInt("BookingID"),
                        rs.getInt("Duration"),
                        rs.getString("BookingTime"),
                        rs.getString("BookingType"),
                        rs.getString("BookingDate")
                    )
                ); 
            }
        connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("error");
        }

    }
    
}