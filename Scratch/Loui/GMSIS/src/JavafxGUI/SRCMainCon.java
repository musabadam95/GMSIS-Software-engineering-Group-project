/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import common.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author louiraj
 */
public class SRCMainCon implements Initializable {

    @FXML
    private Button addPartBt;
    @FXML
    private Button editPartBt;
    @FXML
    private Button deletePartBt;
    @FXML
    private Button RefreshBt;
    @FXML
    private TableView<SRCinfo> partsTable;
    @FXML
    private TableColumn<SRCinfo, ?> partIDCol;
    @FXML
    private TableColumn<SRCinfo, ?> partNCol;
    @FXML
    private TableColumn<SRCinfo, ?> partDesCol;
    @FXML
    private TableColumn<SRCinfo, ?> partVehRegCol;

    private ObservableList<SRCinfo> partsData = FXCollections.observableArrayList();
    private ObservableList<SRCinfo> vehData = FXCollections.observableArrayList();
    private ObservableList<SRCinfo> srcData = FXCollections.observableArrayList();

    private ConnectionDB sqlConnect;

    private static Connection con;
    private static Statement stat;
    private static PreparedStatement pstat;
    ResultSet rs = null;
    db db;
    @FXML
    private TableColumn<SRCinfo, ?> partsRCCostCol;
    @FXML
    private TableColumn<SRCinfo, ?> partExpSenCol;
    @FXML
    private TableColumn<SRCinfo, ?> partExpReturnCol;
    @FXML
    private TableColumn<SRCinfo, ?> partSRCCol;
    @FXML
    private ComboBox searchInSRCComboV;
    @FXML
    private ComboBox ReturnQueryComboV;
    @FXML
    private Button addVehBt;
    @FXML
    private Button editVehBt;
    @FXML
    private Button deleteVehBt;
    @FXML
    private Button RefreshBtV;
    private TableView<SRCinfo> vehicllTable;
    @FXML
    private TableColumn<SRCinfo, ?> vehRegCol;
    @FXML
    private TableColumn<SRCinfo, ?> typeCol;
    @FXML
    private TableColumn<SRCinfo, ?> modelCol;
    @FXML
    private TableColumn<SRCinfo, ?> makeCol;
    @FXML
    private TableColumn<SRCinfo, ?> engSizeCol;
    @FXML
    private TableColumn<SRCinfo, ?> vehSRCCostCol;
    @FXML
    private TableColumn<SRCinfo, ?> vehExpSendCol;
    @FXML
    private TableColumn<SRCinfo, ?> vehExpReturnCol;
    @FXML
    private TableColumn<SRCinfo, ?> vehSRCCol;
    @FXML
    private TextField searchTxtS;
    @FXML
    private ComboBox searchInSRCComboS;
    @FXML
    private ComboBox ReturnQueryComboS;
    @FXML
    private Button addSrcBt;
    @FXML
    private Button editSrcBt;
    @FXML
    private Button deleteSrcBt;
    @FXML
    private Button RefreshBtS;
    @FXML
    private TableView<SRCinfo> srcTable;
    @FXML
    private TableColumn<SRCinfo, ?> srcIDCol;
    @FXML
    private TableColumn<SRCinfo, ?> srcNCol;
    @FXML
    private TableColumn<SRCinfo, ?> firstLineAddsCol;
    @FXML
    private TableColumn<SRCinfo, ?> countyCol;
    @FXML
    private TableColumn<SRCinfo, ?> postCodeCol;
    @FXML
    private TableColumn<SRCinfo, ?> phoneNumberCol;
    @FXML
    private TableColumn<SRCinfo, ?> emailAddCol;
    @FXML
    private TableColumn<SRCinfo, ?> secLineAddCol;
    @FXML
    private TableView<SRCinfo> vehicleTable;
    @FXML
    private TextField searchTxtP;
    @FXML
    private ComboBox searchInSRCComboP;
    @FXML
    private ComboBox ReturnQueryComboP;
    @FXML
    private TextField searchTxtV;
    @FXML
    private TableColumn<?, ?> CusNameCol;
    @FXML
    private Button custDetailsbtn;
    public static SRCinfo selected;

    public SRCMainCon() {
        this.db = db.getInstance();
        sqlConnect = new ConnectionDB();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        srcTable();
        vehicleTable();
        partsTable();
        loadSRCName();
        RefreshBt();
        
        partsTable.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selected = partsTable.getSelectionModel().getSelectedItem();
        }
        );

        //       searchTxtS.setOnAction(new search_class());
        //     searchTxtV.setOnAction(new search_class());
        //   searchTxtP.setOnAction(new search_class());
        addSrcBt.setOnAction((ActionEvent e) -> {
            if (!srcEditPopUps(null, false)) {
                System.out.println("No user added");
            } else {
                srcTable();
            }
        });

        editSrcBt.setOnAction((ActionEvent e) -> {
            if (!srcEditPopUps(srcTable.getSelectionModel().getSelectedItem(), true)) {
                System.out.println("Nothing is Edited");
            } else {
                srcTable();
            }
        });

        deleteSrcBt.setOnAction((ActionEvent e) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                SRCinfo Select = srcTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the SRC " + Select.getName() + "?");
                Optional<ButtonType> response = alert.showAndWait();
                if (response.get() == ButtonType.OK) {
                    String sql = "DELETE FROM SRC WHERE Name=\'" + Select.getName() + "\'";
                    pstat = con.prepareStatement(sql);
                    pstat.execute();

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    con.close();
                    srcTable();
                } catch (Exception ex) {
                }
            }
        });

        addVehBt.setOnAction((ActionEvent e) -> {
            if (!vehEditPopUps(null, false)) {
                System.out.println("No user added");
            } else {
                vehicleTable();
            }

        });

        editVehBt.setOnAction((ActionEvent e) -> {

            if (!vehEditPopUps(vehicleTable.getSelectionModel().getSelectedItem(), true)) {
                System.out.println("Nothing is Edited");
            } else {
                vehicleTable();
            }

        });

        deleteVehBt.setOnAction((ActionEvent e) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {

                SRCinfo Select = vehicleTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the part ?");
                Optional<ButtonType> response = alert.showAndWait();
                if (response.get() == ButtonType.OK) {
                    String sql = "DELETE FROM Vehicles WHERE VehReg= 1'";
                   pstat = con.prepareStatement(sql);
                    pstat.execute();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    con.close();
                    vehicleTable();
                } catch (Exception ex) {
                }
            }
        });

        addPartBt.setOnAction((ActionEvent e) -> {

            try {
                if (!partsEditPopUps(null, false)) {
                    System.out.println("No user added");
                } else {
                    partsTable();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        editPartBt.setOnAction((ActionEvent e) -> {

            try {
                if (!partsEditPopUps(partsTable.getSelectionModel().getSelectedItem(), true)) {
                    System.out.println("Nothing is Edited");
                } else {
                    partsTable();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        deletePartBt.setOnAction((ActionEvent e) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                SRCinfo Select = partsTable.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the part " + Select.getPartName() + "?");
                Optional<ButtonType> response = alert.showAndWait();
                if (response.get() == ButtonType.OK) {
                    String sql = "DELETE FROM InstalledParts WHERE PartName=\'" + Select.getPartName() + "\'";
                    pstat = con.prepareStatement(sql);
                    pstat.execute();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    con.close();
                    partsTable();
                } catch (Exception ex) {
                }
            }
        });

        
        searchTxtP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            FilteredList<SRCinfo> filteredpar = new FilteredList<>(partsData, p -> true);
            filteredpar.setPredicate((SRCinfo par) -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCase = newValue.toLowerCase();
                if (par.getVehReg().toLowerCase().contains(lowerCase)) {
                    return true;
                }

                return false;
            });
            SortedList<SRCinfo> sortedData = new SortedList<>(filteredpar);
            sortedData.comparatorProperty().bind(partsTable.comparatorProperty());
            partsTable.setItems(sortedData);
});
        
        searchTxtV.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            FilteredList<SRCinfo> filteredpar = new FilteredList<>(vehData, p -> true);
            filteredpar.setPredicate((SRCinfo par) -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCase = newValue.toLowerCase();
                if (par.getVehReg().toLowerCase().contains(lowerCase)) {
                    return true;
                }

                return false;
            });
            SortedList<SRCinfo> sortedData = new SortedList<>(filteredpar);
            sortedData.comparatorProperty().bind(vehicleTable.comparatorProperty());
            vehicleTable.setItems(sortedData);
});
        
        searchTxtS.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

            FilteredList<SRCinfo> filteredpar = new FilteredList<>(srcData, p -> true);
            filteredpar.setPredicate((SRCinfo par) -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCase = newValue.toLowerCase();
                if (par.getVehReg().toLowerCase().contains(lowerCase)) {
                    return true;
                }

                return false;
            });
            SortedList<SRCinfo> sortedData = new SortedList<>(filteredpar);
            sortedData.comparatorProperty().bind(partsTable.comparatorProperty());
            partsTable.setItems(sortedData);
});
         
    }

    public void loadSRCName() {
        searchInSRCComboS.getItems().clear();
        searchInSRCComboV.getItems().clear();
        searchInSRCComboP.getItems().clear();
        String sql = "SELECT Name FROM SRC";

        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            searchInSRCComboP.getItems().addAll("All");
            searchInSRCComboV.getItems().addAll("All");
            searchInSRCComboS.getItems().addAll("All");
            while (rs.next()) {
                searchInSRCComboS.getItems().addAll(rs.getString("Name"));
                searchInSRCComboP.getItems().addAll(rs.getString("Name"));
                searchInSRCComboV.getItems().addAll(rs.getString("Name"));
            }

        } catch (SQLException ex) {
            System.err.println("Error");
        }

    }

    void srcTable() {
        srcData = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM SRC";
            try {
                rs = db.query(sql);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                srcData.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));

                srcIDCol.setCellValueFactory(new PropertyValueFactory("ID"));
                srcNCol.setCellValueFactory(new PropertyValueFactory("Name"));
                firstLineAddsCol.setCellValueFactory(new PropertyValueFactory("FirstLineAdd"));
                secLineAddCol.setCellValueFactory(new PropertyValueFactory("SecondLineAdd"));
                countyCol.setCellValueFactory(new PropertyValueFactory("County"));
                postCodeCol.setCellValueFactory(new PropertyValueFactory("PostCode"));
                phoneNumberCol.setCellValueFactory(new PropertyValueFactory("PhoneNo"));
                emailAddCol.setCellValueFactory(new PropertyValueFactory("Email"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error");

        } finally {
            try {
                con.close();
                stat.close();
            } catch (Exception e) {

            }
        }
        srcTable.setItems(srcData);
    }

    private void vehicleTable() {
        vehData = FXCollections.observableArrayList();

        String sql = "SELECT  Customer.FirstName, Vehicles.VehType, VehModel, VehMake, EngSize, VSRCCost, VExpSend, VExpReturn, VSRC  FROM Vehicles INNER JOIN Customer ON Vehicles.VehReg = Customer.VehReg WHERE Vehicles.VSRC IS NULL;";
        try {
            rs = db.query(sql);
            con = sqlConnect.returnConnection();
            stat = con.createStatement();
            while (rs.next()) {
                vehData.add(new SRCinfo( rs.getString("FirstName"), rs.getString("VehType"), rs.getString("VehModel"), rs.getString("VehMake"), rs.getInt("EngSize"), rs.getFloat("VSRCCost"), rs.getString("VExpSend"), rs.getString("VExpReturn"), rs.getString("VSRC")));

                vehRegCol.setCellValueFactory(new PropertyValueFactory("VehRegV"));
                typeCol.setCellValueFactory(new PropertyValueFactory("VehType"));
                modelCol.setCellValueFactory(new PropertyValueFactory("VehModel"));
                makeCol.setCellValueFactory(new PropertyValueFactory("VehMake"));
                engSizeCol.setCellValueFactory(new PropertyValueFactory("EngSize"));
                vehSRCCostCol.setCellValueFactory(new PropertyValueFactory("VSRCCost"));
                vehExpSendCol.setCellValueFactory(new PropertyValueFactory("VExpSend"));
                vehExpReturnCol.setCellValueFactory(new PropertyValueFactory("VExpReturn"));
                vehSRCCol.setCellValueFactory(new PropertyValueFactory("VSRC"));
                CusNameCol.setCellValueFactory(new PropertyValueFactory("FirstName"));

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error");

        } finally {
            try {
                con.close();
                stat.close();
            } catch (Exception e) {

            }
        }
        vehicleTable.setItems(vehData);

    }

    private void partsTable() {
        partsData = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM InstalledParts WHERE PSRC IS NOT NULL";
            rs = db.query(sql);
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                partsData.add(new SRCinfo(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getFloat("PSRCCost"),
                        rs.getString("PSRC"), rs.getString("VehReg"), rs.getString("PExpSend"), rs.getString("PExpReturn")));
                partIDCol.setCellValueFactory(new PropertyValueFactory("PartsID"));
                partNCol.setCellValueFactory(new PropertyValueFactory("PartName"));
                partDesCol.setCellValueFactory(new PropertyValueFactory("Description"));
                partsRCCostCol.setCellValueFactory(new PropertyValueFactory("SRCCost"));
                partSRCCol.setCellValueFactory(new PropertyValueFactory("SRC"));
                partVehRegCol.setCellValueFactory(new PropertyValueFactory("VehReg"));
                partExpSenCol.setCellValueFactory(new PropertyValueFactory("ExpSend"));
                partExpReturnCol.setCellValueFactory(new PropertyValueFactory("ExpReturn"));

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error");
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                con.close();
                stat.close();

            } catch (Exception ex) {

            }
        }
        partsTable.setItems(partsData);
    }

    private boolean srcEditPopUps(SRCinfo srcinfo, boolean par) {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addSRC.fxml"));
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (par) {
                popStage.setTitle("Editting SRC");
            } else {
                popStage.setTitle("Adding SRC");
            }

            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            AddSRCController controller = loads.getController();
            controller.setStage(popStage);
            if (par) {
                controller.setSRC(srcinfo);
                controller.srcEditSave().setVisible(true);
                controller.srcEditSave().requestFocus();
                controller.srcSave().setFocusTraversable(false);

            } else {
                controller.srcSave().setVisible(true);
                controller.srcEditSave().setFocusTraversable(false);
                controller.srcSave().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean vehEditPopUps(SRCinfo srcinfo, boolean par) {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addVeh.fxml"));
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (par) {
                popStage.setTitle("Editting a Vehicle");
            } else {
                popStage.setTitle("Adding a Vehicle");
            }

            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            AddVehController controller = loads.getController();
            controller.setStage(popStage);
            if (par) {
                controller.setVehicle(srcinfo);
                controller.vehEditSave().setVisible(true);
                controller.vehEditSave().requestFocus();
                controller.vehSave().setFocusTraversable(false);

            } else {
                controller.vehSave().setVisible(true);
                controller.vehEditSave().setFocusTraversable(false);
                controller.vehSave().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean partsEditPopUps(SRCinfo srcinfo, boolean par) throws SQLException {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addParts.fxml"));
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (par) {
                popStage.setTitle("Editting A Part");
            } else {
                popStage.setTitle("Adding A Part");
            }

            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            AddPartsController controller = loads.getController();
            controller.setStage(popStage);
            if (par) {
                controller.setParts(srcinfo);
                controller.partsEditSave().setVisible(true);
                controller.partsEditSave().requestFocus();
                controller.partsSave().setFocusTraversable(false);

            } else {
                controller.partsSave().setVisible(true);
                controller.partsEditSave().setFocusTraversable(false);
                controller.partsSave().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void RefreshBt() {
        srcTable();
        vehicleTable();
        partsTable();
        loadSRCName();
    }

    @FXML
    private void searchInSRCCombo(ActionEvent event) {
       String ComboBox = (searchInSRCComboP.getSelectionModel().getSelectedItem().toString());
       
        FilteredList<SRCinfo> filteredparts_1 = new FilteredList<>(partsData, p -> true);
        filteredparts_1.setPredicate((SRCinfo par_1) -> {

            if ((par_1.getSRC().equals(ComboBox)))  {
                
                searchTxtP.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    FilteredList<SRCinfo> filteredparts_2 = new FilteredList<>(partsData, p -> true);
                    filteredparts_2.setPredicate((SRCinfo par_2) -> {

                        if (par_2.getSRC().equals(ComboBox)) {

                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                        } 
                        return false;
                               
                    });
                    SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_2);
                    sortedData.comparatorProperty().bind(partsTable.comparatorProperty());
                    partsTable.setItems(sortedData);
                });
                return true;
            } else if (ComboBox.equals("All")) {
                return true;
            }
            return false;
        });
        SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_1);
        sortedData.comparatorProperty().bind(partsTable.comparatorProperty());
        partsTable.setItems(sortedData);

    }
    
    private void searchInSRCComboV(){
        
              String ComboBox = (searchInSRCComboV.getSelectionModel().getSelectedItem().toString());
       
        FilteredList<SRCinfo> filteredparts_1 = new FilteredList<>(vehData, p -> true);
        filteredparts_1.setPredicate((SRCinfo par_1) -> {

            if ((par_1.getSRC().equals(ComboBox)))  {
                
                searchTxtV.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    FilteredList<SRCinfo> filteredparts_2 = new FilteredList<>(vehData, p -> true);
                    filteredparts_2.setPredicate((SRCinfo par_2) -> {

                        if (par_2.getSRC().equals(ComboBox)) {

                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                        } 
                        return false;
                               
                    });
                    SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_2);
                    sortedData.comparatorProperty().bind(vehicleTable.comparatorProperty());
                    vehicleTable.setItems(sortedData);
                });
                return true;
            } else if (ComboBox.equals("All")) {
                return true;
            }
            return false;
        });
        SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_1);
        sortedData.comparatorProperty().bind(vehicleTable.comparatorProperty());
        vehicleTable.setItems(sortedData);
        
    }
    
    private void searchInSRCComboS(){
        
              String ComboBox = (searchInSRCComboS.getSelectionModel().getSelectedItem().toString());
       
        FilteredList<SRCinfo> filteredparts_1 = new FilteredList<>(srcData, p -> true);
        filteredparts_1.setPredicate((SRCinfo par_1) -> {

            if ((par_1.getSRC().equals(ComboBox)))  {
                
                searchTxtS.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    FilteredList<SRCinfo> filteredparts_2 = new FilteredList<>(srcData, p -> true);
                    filteredparts_2.setPredicate((SRCinfo par_2) -> {

                        if (par_2.getSRC().equals(ComboBox)) {

                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                        } 
                        return false;
                               
                    });
                    SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_2);
                    sortedData.comparatorProperty().bind(srcTable.comparatorProperty());
                    srcTable.setItems(sortedData);
                });
                return true;
            } else if (ComboBox.equals("All")) {
                return true;
            }
            return false;
        });
        SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_1);
        sortedData.comparatorProperty().bind(srcTable.comparatorProperty());
        srcTable.setItems(sortedData);
        
    }


    @FXML
    private void ReturnQueryComboP(ActionEvent event) {
        
    }

    @FXML
    private void viewCustDetails(ActionEvent event) {
        
        SRCinfo src = partsTable.getSelectionModel().getSelectedItem();
        String selectedRow = src.getVehReg();
        
        try{
            Stage newScreen = new Stage();
            newScreen.initModality(Modality.APPLICATION_MODAL);
            Parent parent = FXMLLoader.load(getClass().getResource("/JavafxGUI/CuDo.fxml"));
            newScreen.setScene(new Scene(parent));
            newScreen.show();
        } catch (IOException e){
            System.out.println("Error in new window.");
        }
        
    }
}
