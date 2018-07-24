/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

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
    private TableColumn<Vehicle,String> vTypeCol,vRegCol,vModCol,vMakeCol,vEngCol,vFuelCol,vColourCol,vMotCol,vLSDCol,vMileCol,vWNCol,vWACol,vWECol;

    //Observable list for updating vehicle table with data
    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();
    
   private Database database;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //initialise the database
        
        database = Database.getInstance();
        
        this.initialiseVehicleTable();
        //this.initialisePartsTable();
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
        //this.updatePartsTable();
    }
    private void updateVehicleTable(){

          vehicleData = database.getAllVehicles();     
          vehicleTable.setItems(vehicleData);
        
    }
    
} 