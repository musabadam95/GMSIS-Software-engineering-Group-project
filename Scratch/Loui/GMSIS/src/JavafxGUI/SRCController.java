/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

// import common.ConnectionDB;
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
public class SRCController implements Initializable {

    private ObservableList<SRCinfo> srcdata = FXCollections.observableArrayList();

    //  ConnectionDB sqlConnect;
    private Button delete_bt;
    private Button edit_bt;
    @FXML
    private Button add_parts_bt;
    private Button add_bt;
    @FXML
    private TableView<SRCinfo> parts_table;
    @FXML
    private TableView<SRCinfo> vehicle_table;
    @FXML
    private TableView<SRCinfo> src_table;
    @FXML
    private TableColumn<SRCinfo, Integer> src_table_id;
    @FXML
    private TableColumn<SRCinfo, String> src_table_name;
    @FXML
    private TableColumn<SRCinfo, String> src_table_1add;
    @FXML
    private TableColumn<SRCinfo, String> src_table_2add;
    @FXML
    private TableColumn<SRCinfo, String> src_table_county;
    @FXML
    private TableColumn<SRCinfo, String> src_table_pcode;
    @FXML
    private TableColumn<SRCinfo, Integer> src_table_pnumber;
    @FXML
    private TableColumn<SRCinfo, String> src_table_emailadd;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_reg;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_type;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_model;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_make;
    @FXML
    private TableColumn<SRCinfo, Integer> parts_table_id;
    @FXML
    private TableColumn<SRCinfo, String> parts_table_name;
    @FXML
    private TableColumn<SRCinfo, String> parts_table_des;
    @FXML
    private TableColumn<SRCinfo, Integer> parts_table_cost;

    private ObservableList<SRCinfo> partsdata;

    db db;
    ResultSet rs = null;
    private final int QUERY_TIMEOUT = 5;

    private static Connection con;
    private static Statement stat;
    PreparedStatement pstat;
    @FXML
    private TableColumn<SRCinfo, String> parts_table_vehreg;

    SRCinfo srcinfo;
    @FXML
    private TextField search_txt;
    private ChoiceBox src_choicebox;
    @FXML
    private Button refresh_bt;
    @FXML
    private TableColumn<SRCinfo, String> parts_table_srcname;
    @FXML
    private ComboBox src_combobox;
    @FXML
    private TableColumn<SRCinfo, Integer> vehicle_table_engsize;
    @FXML
    private TableColumn<SRCinfo, Integer> vehicle_table_cost;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_expsend;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_expreturn;
    @FXML
    private TableColumn<SRCinfo, String> vehicle_table_src;
    @FXML
    private TableColumn<SRCinfo, String> parts_table_expsend;
    @FXML
    private TableColumn<SRCinfo, String> parts_table_expreturn;
    @FXML
    private Button add_src_bt;
    @FXML
    private Button edit_part_bt;
    @FXML
    private Button edit_src_bt;
    @FXML
    private Button delete_src_bt;
    @FXML
    private Button delete_veh_bt;
    @FXML
    private Button delete_part_bt;
    @FXML
    private Button edit_veh_bt;
    @FXML
    private Button add_veh_bt;

    /**
     *
     */
    public SRCController() {
        this.db = db.getInstance();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //              sqlConnect = new ConnectionDB();
        vehicle_table();
        loadSRCName();
        src_table();
        parts_table();

        //    src_choicebox.setOnAction(new src_choicebox_class());


        delete_src_bt.setOnAction((ActionEvent e) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                SRCinfo Select = src_table.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("GMSIS");
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
                    src_table();
                } catch (Exception ex) {
                }
            }
        });

        delete_part_bt.setOnAction((ActionEvent e) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {


                 SRCinfo Select = parts_table.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("GMSIS");
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
                    parts_table();
                } catch (Exception ex) {
                }
            }
        });

        delete_veh_bt.setOnAction((ActionEvent e) -> {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {


                 SRCinfo Select = vehicle_table.getSelectionModel().getSelectedItem();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("GMSIS");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete the part " + Select.getVehRegV() + "?");
                Optional<ButtonType> response = alert.showAndWait();
                if (response.get() == ButtonType.OK) {
                    String sql = "DELETE FROM InstalledParts WHERE PartName=\'" + Select.getVehRegV() + "\'";
                    pstat = con.prepareStatement(sql);
                    pstat.execute();
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    con.close();
                    vehicle_table();
                } catch (Exception ex) {
                }
            }
        });

        search_txt.setOnAction(new search_class());

        edit_src_bt.setOnAction((ActionEvent e) -> {
            if (!src_edit_pop(src_table.getSelectionModel().getSelectedItem(), true)) {
                System.out.println("Nothing is Edited");
            } else {
                src_table();
            }
        });

        add_src_bt.setOnAction((ActionEvent e) -> {
            if (!src_edit_pop(null, false)) {
                System.out.println("No user added");
            } else {
                src_table();
            }
        });

        edit_part_bt.setOnAction((ActionEvent e) -> {

            try {
                if (!parts_edit_pop(parts_table.getSelectionModel().getSelectedItem(), true)) {
                    System.out.println("Nothing is Edited");
                } else {
                    parts_table();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        add_parts_bt.setOnAction((ActionEvent e) -> {

            try {
                if (!parts_edit_pop(null, false)) {
                    System.out.println("No user added");
                } else {
                    parts_table();
                }
            } catch (SQLException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }


        });

        edit_veh_bt.setOnAction((ActionEvent e) -> {

            if (!veh_edit_pop(vehicle_table.getSelectionModel().getSelectedItem(), true)) {
                System.out.println("Nothing is Edited");
            } else {
                vehicle_table();
            }

        });

        add_veh_bt.setOnAction((ActionEvent e) -> {
            if (!veh_edit_pop(null, false)) {
                System.out.println("No user added");
            } else {
                vehicle_table();
            }

        });

        /*  search_txt.textProperty().addListener((observable, oldValue, newValue) -> {


            FilteredList<SRCinfo> filteredsrc = new FilteredList<>(srcdata, p -> true);
            filteredsrc.setPredicate(src -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCase = newValue.toLowerCase();
                if (src.getName().toLowerCase().contains(lowerCase)) {
                    return true;
                }

                return false;
            });
                SortedList<SRCinfo> sortedData = new SortedList<>(filteredsrc);
                sortedData.comparatorProperty().bind(src_table.comparatorProperty());
                src_table.setItems(sortedData);


       });

        /*
        delete_bt.setOnAction((ActionEvent e) -> {
          try{
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
          } catch (SQLException ex){
            ex.printStackTrace();
          }  try {
             String sql = "DELETE FROM SRC WHERE Name=?";
             PreparedStatement prStatement = con.prepareStatement(sql);
             prStatement.setString(1, src_table.getSelectionModel().getSelectedItem().getName());
             prStatement.execute();
             con.close();
         } catch (SQLException ex) {
             ex.printStackTrace();

         }
        });

    //delete_bt.setOnAction(new EventHandlerImpl());
/*
        delete_bt.setOnAction((ActionEvent e) -> {
            try {

                String driverName = "com.jnetdirect.jsql.JSQLDriver";
               Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:data.db");


                stat = con.createStatement();
                //stat.execute(sql);

               rs = stat.executeQuery("SELECT \"ID\" FROM SRC;");
                int selectedIndex = src_table.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    src_table.getItems().remove(selectedIndex);
                   // stat.execute(sql);
                    delete_src(selectedIndex);

                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("No Selection");
                    alert.setHeaderText("You haven't Selected any element");
                    alert.setContentText("Please select an element in the table");
                    alert.showAndWait();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
              try {
                con.close();
                stat.close();

              } catch (Exception ex){

              }
            }
        });


 /*        delete_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int selectedIndex = src_table.getSelectionModel().getSelectedIndex();
                    if (selectedIndex >= 0) {
                        src_table.getItems().remove(selectedIndex);
                        theQuery("delete from SRC where id" = selectedIndex.getText());
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("No Selection");
                        alert.setHeaderText("You haven't Selected any element");
                        alert.setContentText("Please select an element in the table");
                        alert.showAndWait();
                    }

                } catch (Exception ex) {

                }
            }

        });*/
    }

    public void loadSRCName() {

        String sql = "SELECT Name FROM SRC";

        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            boolean comboBoxEmpty = src_combobox.getSelectionModel().isEmpty();
            if (comboBoxEmpty == true) {
                src_combobox.getItems().addAll("All");
                while (rs.next()) {
                    src_combobox.getItems().addAll(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error");
        }
    }

    /*
    private void add_parts_bt(ActionEvent event) throws IOException {
        try {
            FXMLLoader partsLoader = new FXMLLoader(getClass().getResource("addParts.fxml"));
            Parent root1 = (Parent) partsLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    private void add_vehicle_bt(ActionEvent event) {
        try {

            FXMLLoader vehLoader = new FXMLLoader(getClass().getResource("addVeh.fxml"));
            Parent root2 = (Parent) vehLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
     */
    private boolean src_edit_pop(SRCinfo srcinfo, boolean par) {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addSRC.fxml"));
            //      Parent root3 = (Parent) srceditloads.load();
            //      Stage stage1 = new Stage();
            //      stage1.setScene(new Scene(root3));
            //      stage1.show();
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (par) {
                popStage.setTitle("Editting");
            } else {
                popStage.setTitle("Adding");
            }

            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            AddSRCController controller = loads.getController();
            controller.setStage(popStage);
            if (par) {
                controller.setSRC(srcinfo);
                controller.src_save_edit1().setVisible(true);
                controller.src_save_edit1().requestFocus();
                controller.src_save1().setFocusTraversable(false);

            } else {
                controller.src_save1().setVisible(true);
                controller.src_save_edit1().setFocusTraversable(false);
                controller.src_save1().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean veh_edit_pop(SRCinfo srcinfo, boolean par) {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addVeh.fxml"));
            //      Parent root3 = (Parent) srceditloads.load();
            //      Stage stage1 = new Stage();
            //      stage1.setScene(new Scene(root3));
            //      stage1.show();
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (par) {
                popStage.setTitle("Editting");
            } else {
                popStage.setTitle("Adding");
            }

            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            AddVehController controller = loads.getController();
            controller.setStage(popStage);
            if (par) {
                controller.setVehicle(srcinfo);
                controller.veh_save_edit1().setVisible(true);
                controller.veh_save_edit1().requestFocus();
                controller.veh_save1().setFocusTraversable(false);

            } else {
                controller.veh_save1().setVisible(true);
                controller.veh_save_edit1().setFocusTraversable(false);
                controller.veh_save1().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean parts_edit_pop(SRCinfo srcinfo, boolean par) throws SQLException {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addParts.fxml"));
            //      Parent root3 = (Parent) srceditloads.load();
            //      Stage stage1 = new Stage();
            //      stage1.setScene(new Scene(root3));
            //      stage1.show();
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
                controller.parts_save_edit1().setVisible(true);
                controller.parts_save_edit1().requestFocus();
                controller.parts_save1().setFocusTraversable(false);

            } else {
                controller.parts_save1().setVisible(true);
                controller.parts_save_edit1().setFocusTraversable(false);
                controller.parts_save1().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void vehicle_table() {


            String sql = "SELECT * FROM Vehicles";
            try {
                rs = db.query(sql);

            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                srcdata.add(new SRCinfo(rs.getString("VehReg"), rs.getString("VehType"), rs.getString("VehModel"), rs.getString("VehMake"),
                        rs.getInt("EngSize"), rs.getInt("VSRCCost"), rs.getString("VExpSend"), rs.getString("VExpReturn"), rs.getString("VSRC")));

                vehicle_table_reg.setCellValueFactory(new PropertyValueFactory("VehRegV"));
                vehicle_table_type.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("VehType"));
                vehicle_table_model.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("VehModel"));
                vehicle_table_make.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("VehMake"));
                vehicle_table_engsize.setCellValueFactory(new PropertyValueFactory("EngSize"));
                vehicle_table_cost.setCellValueFactory(new PropertyValueFactory("VSRCCost"));
                vehicle_table_expsend.setCellValueFactory(new PropertyValueFactory("VExpSend"));
                vehicle_table_expreturn.setCellValueFactory(new PropertyValueFactory("VExpReturn"));
                vehicle_table_src.setCellValueFactory(new PropertyValueFactory("VSRC"));

            }

        } catch (Exception e) {
           //. e.printStackTrace();
            System.err.println("Error");

        } finally {
            try {
                con.close();
                stat.close();
            } catch (Exception e) {

            }
        }
        vehicle_table.setItems(srcdata);

    }

    @FXML
    private void parts_table() {
        partsdata = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM InstalledParts WHERE PSRC IS NOT NULL";
            rs = db.query(sql);
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                partsdata.add(new SRCinfo(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getFloat("PSRCCost"),
                        rs.getString("PSRC"), rs.getString("VehReg"), rs.getString("PExpSend"), rs.getString("PExpReturn")));
                parts_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PartsID"));
                parts_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PartName"));
                parts_table_des.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Description"));
                parts_table_cost.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("SRCCost"));
                parts_table_srcname.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SRC"));
                parts_table_vehreg.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("VehReg"));
                parts_table_expsend.setCellValueFactory(new PropertyValueFactory("ExpSend"));
                parts_table_expreturn.setCellValueFactory(new PropertyValueFactory("ExpReturn"));

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
        parts_table.setItems(partsdata);
    }

    @FXML
    void src_table() {
        srcdata = FXCollections.observableArrayList();
        try {

            String sql = "SELECT  Customer.FirstName, Vehicles.VehType,VehModel,VehMake,EngSize,VSRCCost,VExpSend,VExpReturn,VSRC  FROM Vehicles INNER JOIN Customer ON Vehicles.VehReg = Customer.VehReg WHERE  VSRC IS NOT NULL;";
            try {
                rs = db.query(sql);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
                srcdata.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));

                src_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("ID"));
                src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
                src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
                src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
                src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
                src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
                src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
                src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));

            
    }

    /*
    private void search() throws ClassNotFoundException{
        ObservableList<SRCinfo> srcdata=FXCollections.<SRCinfo>observableArrayList();

       String searched=search_txt.getText();
       System.out.println(searched);
       String sql="SELECT * FROM SRC WHERE  Name LIKE '%"+searched+"%' OR ID LIKE '%"+searched+"%'";
        ResultSet rs;
        try {
          rs = db.query(sql);
            //conn = sqlconnect.returnConnection();
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            //System.out.println(rs.isBeforeFirst());

            while (rs.next()) {
                srcdata.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));
                        srcdata = FXCollections.observableList(srcdata);

                src_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("ID"));
                src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
                src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
                src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
                src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
                src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
                src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
                src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));
}


        } catch (SQLException e) {
          e.printStackTrace();
          System.err.println("Error");
        } finally {
            try {
                con.close();
                stat.close();
                src_table.setItems(srcdata);

            } catch (Exception e) {

            }
    }

    }



 /*   private void delete_src(int id) throws SQLException {

        PreparedStatement pstat;
        pstat = query("DELETE FROM 'SRC' WHERE ID = ?");
        pstat.setString(1, "ID");
        pstat.executeUpdate();

    }

    private PreparedStatement query(String sql) throws SQLException {
        PreparedStatement pstat;
        pstat = con.prepareStatement(sql);
        pstat.setQueryTimeout(QUERY_TIMEOUT);
        return pstat;
    }

    /*
    private void insertStatement(String insert_query) {

        Connection con = null;
        Statement stat = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            con.setAutoCommit(false);
            stat = con.createStatement();
            stat.executeUpdate(insert_query);
            stat.close();
            con.commit();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
     */
 /*
   public void theQuery(String query) {
        Connection con = null;
        Statement st = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql:data.db");
            st = con.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Query Executed");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     */
    @FXML
    private void refresh_bt(ActionEvent event) {
        src_table();
        parts_table();
        vehicle_table();

    }

    /*
    private class src_choicebox_class implements EventHandler<ActionEvent>{
      public src_choicebox_class(){

      }
      public void handle(ActionEvent event){

        String sql = "SELECT Name FROM SRC;";
        try{
        con = DriverManager.getConnection("jdbc:sqlite:data.db");
        stat = con.createStatement();
        rs = stat.executeQuery(sql);
            src_choicebox.getItems().addAll("All");
        while(rs.next()){
        src_choicebox.getItems().addAll(rs.getString("Name"));

       // src_combobox.set
        }
        } catch (SQLException e){
        e.printStackTrace();

        }


      }
    }
     */
//    private void src_choicebox(ActionEvent event) {
//       String choicebox =  (src_choicebox.getSelectionModel().getSelectedItem().toString());
//       System.out.println(choicebox);
    //    if(choicebox.equals(choicebox)){
    //      String sql = "SELECT * FROM InstalledParts WHERE " + choicebox + "= ?";
    //      try{
    //        con = DriverManager.getConnection("jdbc:sqlite:data.db");
    //        stat = con.createStatement();
    //        rs = db.query(sql);
//  } catch (Exception e){
    //        e.printStackTrace();
    //    }
    //    }
//    }
    @FXML
    //  private void src_combobox(ActionEvent event) {

    //}
    private void src_combobox(ActionEvent event) {

        String ComboBox = (src_combobox.getSelectionModel().getSelectedItem().toString());

        FilteredList<SRCinfo> filteredsrc = new FilteredList<>(srcdata, p -> true);
        filteredsrc.setPredicate((SRCinfo src) -> {
            if (ComboBox.equals(src.getName())) {
                return true;
            } else if (ComboBox.equals("All")) {
                String lowerCase = "";

                return src.getName().toLowerCase().contains(lowerCase);
            }
            return false;
        });
        SortedList<SRCinfo> sortedDatasrc = new SortedList<>(filteredsrc);
        sortedDatasrc.comparatorProperty().bind(src_table.comparatorProperty());
        src_table.setItems(sortedDatasrc);

        FilteredList<SRCinfo> filteredparts_1 = new FilteredList<>(partsdata, p -> true);
        filteredparts_1.setPredicate((SRCinfo par_1) -> {
            if (ComboBox.equals(par_1.getSRC())) {
                search_txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    FilteredList<SRCinfo> filteredparts_2 = new FilteredList<>(partsdata, p -> true);
                    filteredparts_2.setPredicate((SRCinfo par_2) -> {

                        if (par_2.getSRC().equals(ComboBox)) {

                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                            return newValue == null || newValue.isEmpty();

                        } else if (ComboBox.equals("All")) {
                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                            return newValue == null || newValue.isEmpty();

                        } else {
                            System.out.println("Error");
                            return false;

                        }

                    });
                    SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_2);
                    sortedData.comparatorProperty().bind(parts_table.comparatorProperty());
                    parts_table.setItems(sortedData);

                });
            } else if (ComboBox.equals("All")) {
                String lowerCase = "";

                return par_1.getPartName().toLowerCase().contains(lowerCase);
            } else {
                System.out.println("Error");
                return false;
            }
            return true;

        });

        SortedList<SRCinfo> sortedData = new SortedList<>(filteredparts_1);
        sortedData.comparatorProperty().bind(parts_table.comparatorProperty());
        parts_table.setItems(sortedData);

        /*
      String ComboBox = (src_combobox.getSelectionModel().getSelectedItem().toString());
        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String Sqlite = "";

        ObservableList<SRCinfo> partsdata = FXCollections.<SRCinfo>observableArrayList();

        if (search_txt.getText().equals(ComboBox)) {
            Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.CusID=InstalledParts.PartsID INNER JOIN Bookings ON Customer.CusID=Bookings.CustomerID  WHERE Customer.FirstName LIKE '%" + search_txt.getText() + "%'OR LastName LIKE '%" + search_txt.getText() + "%'";
        } else {

            Sqlite = "SELECT * FROM Vehicles INNER JOIN Customer ON Vehicles.CusIDs=Customer.CusID  INNER JOIN Bookings ON Customer.CusID=Bookings.CustomerID WHERE " + search_txt.getText() + " LIKE '%" + search_txt.getText() + "%'";
        }
        ResultSet rs;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(Sqlite);
            while (rs.next()) {
                partsdata.add(new Parts(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getInt("SRCCost"), rs.getString("SRC"), rs.getString("VehReg"), rs.getString("ExpSend"), rs.getString("ExpReturn")));
            }
            parts_table_id.setCellValueFactory(new PropertyValueFactory<SRCinfo, Integer>("PartsID"));
            parts_table_name.setCellValueFactory(new PropertyValueFactory<SRCinfo, String>("PartsName"));
            parts_table_des.setCellValueFactory(new PropertyValueFactory<SRCinfo, String>("Description"));
            parts_table_cost.setCellValueFactory(new PropertyValueFactory<SRCinfo, Integer>("SRCCost"));
            parts_table_srcname.setCellValueFactory(new PropertyValueFactory<SRCinfo, String>("SRC"));
            parts_table_vehreg.setCellValueFactory(new PropertyValueFactory<SRCinfo, String>("VehReg"));

            parts_table_dstosrc.setCellValueFactory(new PropertyValueFactory<>("ExpSend"));
            parts_table_drfsrc.setCellValueFactory(new PropertyValueFactory<>("ExpReturn"));
            parts_table.setItems(partsdata);
            con.close();
        } catch (SQLException Ex) {
            Ex.printStackTrace();
          try {
              throw Ex;
          } catch (SQLException ex) {
              Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }

         */
    }

    private class search_class implements EventHandler<ActionEvent> {

        public search_class() {
        }

        @Override
        public void handle(ActionEvent event) {

            search_txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                FilteredList<SRCinfo> filteredsrc = new FilteredList<>(srcdata, p -> true);
                filteredsrc.setPredicate((SRCinfo src) -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (src.getName().toLowerCase().contains(lowerCase)) {
                        return true;
                    }

                    return false;
                });
                SortedList<SRCinfo> sortedData = new SortedList<>(filteredsrc);
                sortedData.comparatorProperty().bind(src_table.comparatorProperty());
                src_table.setItems(sortedData);

                FilteredList<SRCinfo> filteredparts = new FilteredList<>(partsdata, p -> true);
                filteredparts.setPredicate((SRCinfo par) -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (par.getPartName().toLowerCase().contains(lowerCase)) {
                        return true;
                    }

                    return false;
                });
                SortedList<SRCinfo> sortedDataParts = new SortedList<>(filteredparts);
                sortedDataParts.comparatorProperty().bind(parts_table.comparatorProperty());
                parts_table.setItems(sortedDataParts);
            });
        }

        private class delete_class implements EventHandler<ActionEvent> {

            public delete_class() {
            }

            @Override
            public void handle(ActionEvent event) {

                /* srcdata = FXCollections.observableArrayList();
            String[] options = null;
            try {
                String driverName = "com.jnetdirect.jsql.JSQLDriver";
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
                String sql = "DELETE FROM SRC WHERE \"ID\" =?;";
                stat.execute(sql);

                rs = stat.executeQuery("SELECT \"ID\" FROM SRC;");
                int count = 0;

                int selectedIndex = src_table.getSelectionModel().getSelectedIndex();
                //  if (selectedIndex >= 0) {
                //       src_table.getItems().remove(selectedIndex);

                while (rs.next()) {
                    options[count] = rs.getInt("ID") + "";
                    count++;
                }
                //         } else {
                //    Alert alert = new Alert(AlertType.WARNING);
                //    alert.setTitle("No Selection");
                //    alert.setHeaderText("No Element Selected");
                //    alert.setContentText("Please select an Element in the table.");
                //    alert.showAndWait();
                //   }
                rs.close();
                stat.close();
                con.close();
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println(event.getClass().getName() + ": " + ex.getMessage());
                System.exit(0);
                ex.printStackTrace();
            }

            String removeid = (String) JOptionPane.showInputDialog(null, "ID",
                    "which SRC you want to remove?", JOptionPane.QUESTION_MESSAGE, null,
                    options, // Array of choices
                    options[0]); // Initial choice

            if (removeid != null) {
                int removeidint = Integer.parseInt(removeid);
                con = null;
                stat = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    con = DriverManager.getConnection("jdbc:sqlite:data.db");
                    con.setAutoCommit(false);
                    stat = con.createStatement();

                    PreparedStatement statement = con.prepareStatement("DELETE FROM SRC WHERE \"ID\" =?;");
                    statement.setInt(1, removeidint);

                    statement.execute();
                    con.commit();
                    stat.close();
                    con.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    System.err.println(event.getClass().getName() + ": " + ex.getMessage());
                    System.exit(0);
                }
                src_table.setItems(ObservableList <SRCinfo>)load("jdbc:sqlite:data.db");

            }
                 */
            }

            //    src_table.getItems().removeAll(
            //       src_table.getSelectionModel().getSelectedItems()
            //);
        }

        /**
         * ************************************************** Deferent Mothed
         * Test *****************************************?
         *
         * /* @Override public void handle(ActionEvent event) { String[]
         * options = null; try { Class.forName("org.sqlite.JDBC"); con =
         * DriverManager.getConnection("jdbc:sqlite:data.db");
         * con.setAutoCommit(false); stat = con.createStatement(); rs =
         * stat.executeQuery("SELECT COUNT(*) AS total FROM SRC;"); while
         * (rs.next()) { options = new String[rs.getInt("total")]; }
         *
         * rs = stat.executeQuery("SELECT \"ID\" FROM SRC;"); int count = 0;
         * while (rs.next()) { options[count] = rs.getInt("ID") + ""; count++; }
         * rs.close(); stat.close(); con.close(); } catch
         * (ClassNotFoundException | SQLException ex) {
         * System.err.println(event.getClass().getName() + ": " +
         * ex.getMessage()); System.exit(0); }
         *
         * String removeid = (String) JOptionPane.showInputDialog(null, "ID",
         * "which SRC ID you want to remove?", JOptionPane.QUESTION_MESSAGE,
         * null, options, // Array of choices options[0]); // Initial choice
         *
         * if (removeid != null) { int removeidint = Integer.parseInt(removeid);
         * con = null; stat = null; try { Class.forName("org.sqlite.JDBC"); con
         * = DriverManager.getConnection("jdbc:sqlite:data.db");
         * con.setAutoCommit(false); stat = con.createStatement();
         *
         * PreparedStatement statement = con.prepareStatement("DELETE FROM SRC
         * WHERE \"ID\" =?;"); statement.setInt(1, removeidint);
         *
         * statement.execute(); con.commit(); stat.close(); con.close();
         *
         * } catch (ClassNotFoundException | SQLException ex) {
         * System.err.println(event.getClass().getName() + ": " +
         * ex.getMessage()); System.exit(0); } }
         *
         * src_table.setItems((ObservableList<SRCinfo>)
         * load("jdbc:sqlite:data.db"));
         *
         * }
         * }
         */
        private void delete_bt(ActionEvent event) {
        }
        /*                   working on it
    private void edit(ActionEvent event) throws SQLException {

        try {
            String sql = "UPDATE SRC SET Name=?, FirstLineAdd=?, SecondLineAdd = ?, County=?, PostCode=?, PhoneNo=?, Email=?";

            rs = db.query(sql);

            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                srcdata.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));

                src_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("ID"));
                src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
                src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
                src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
                src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
                src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
                src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
                src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error");
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                con.close();
                stat.close();
            } catch (Exception e) {

            }
        }

        src_table.setItems(srcdata);

        /*
  connection = sqlConnect.returnConnection();
    String query="UPDATE SRC SET Name= ?,Stock=?,Price=?,Description=? WHERE StockID=?";
    //create preparedStatement
    try {
        PreparedStatement prStatement = connection.prepareStatement(query);
        prStatement.setInt(5, StockTable.getSelectionModel().getSelectedItem().getStockID());
        prStatement.setString(1, editPartNameTxt.getText());
        prStatement.setInt(2,Integer.parseInt(editQuantityTxt.getText()));
        prStatement.setInt(3, Integer.parseInt(editPriceTxt.getText()));
        prStatement.setString(4, editDescTxt.getText());
        prStatement.execute();
        connection.close();
        itemListDropDownInt();
    refreshStockTable();
        //refresh table
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("error");

    }

}
         */
    }
}
