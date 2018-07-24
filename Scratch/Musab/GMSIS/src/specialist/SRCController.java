/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

// import common.ConnectionDB;
import java.io.IOException;
import static java.lang.System.load;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import static com.oracle.webservices.internal.api.databinding.DatabindingModeFeature.ID;
import common.ConnectionDB;
import java.awt.event.ActionListener;
import static java.lang.System.in;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import vehicles.Vehicle;

/**
 * FXML Controller class
 *
 * @author louiraj
 */
public class SRCController implements Initializable {

      ConnectionDB sqlConnect;
    @FXML
    private Button delete_bt;
    @FXML
    private Button edit_bt;
    @FXML
    private Button print_bt;
    @FXML
    private Button bill_bt;
    @FXML
    private Button search_bt;
    @FXML
    private Button add_parts_bt;
    @FXML
    private Button add_vehicle_bt;
    @FXML
    private Button add_bt;
    @FXML
    private TextField first_name_txt;
    @FXML
    private TextField last_name_txt;
    @FXML
    private TextField vehicle_reg_txt;
    @FXML
    private TableView<Parts> parts_table;
    @FXML
    private TableView<Vehicle> vehicle_table;
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
    private TableColumn<Vehicle, String> vehicle_table_reg;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_type;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_warranty;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_model;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_make;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_engszie;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_fueltype;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_col;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_rdate;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_lsdate;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_mile;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_dstsrc;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_drfsrc;
    @FXML
    private TableColumn<Parts, Integer> parts_table_id;
    @FXML
    private TableColumn<Parts, String> parts_table_name;
    @FXML
    private TableColumn<Parts, String> parts_table_des;
    @FXML
    private TableColumn<Parts, Integer> parts_table_cost;

    private ObservableList<Parts> partsdata;
    private ObservableList<SRCinfo> srcdb;
    db db;
    ResultSet rs = null;
    private int QUERY_TIMEOUT = 5;

    private static Connection con;
    private static Statement stat;
    @FXML
    private TableColumn<Parts, String> parts_table_vehreg;
    @FXML
    private TableColumn<Parts, Integer> parts_table_dstosrc;
    @FXML
    private TableColumn<Parts, Integer> parts_table_drfsrc;

    /**
     *
     */


    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     sqlConnect = new ConnectionDB();

        src_table();
        parts_table();
        delete_bt.setOnAction(new EventHandlerImpl());

    }
    @FXML
    private void print_bt(ActionEvent event
    ) {
    }

    @FXML
    private void bill_bt(ActionEvent event
    ) {
    }

    @FXML
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

    @FXML
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

    @FXML
    private void add_bt(ActionEvent event) {
        try {

            FXMLLoader srcLoader = new FXMLLoader(getClass().getResource("addSRC.fxml"));
            Parent root2 = (Parent) srcLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void first_name_txt(ActionEvent event
    ) {
    }

    @FXML
    private void last_name_txt(ActionEvent event
    ) {
    }

    @FXML
    private void vehicle_reg_txt(ActionEvent event
    ) {
    }

    @FXML
    private void vehicle_table() {
    
    }

    private void parts_table() {
        partsdata = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM InstalledParts";
            rs = db.query(sql);
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                partsdata.add(new Parts(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getInt("SRCCost"),
                        rs.getBoolean("SRC"), rs.getString("VehReg"), rs.getDate("ExpSend"), rs.getDate("ExpReturn")));
                parts_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PartsID"));
                parts_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("PartName"));
                parts_table_des.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("Description"));
                parts_table_cost.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("SRCCost"));
                parts_table_vehreg.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("VehReg"));

            }

        } catch (SQLException e) {
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
        srcdb = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM SRC";
            rs = db.query(sql);
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                srcdb.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
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
        src_table.setItems(srcdb);
    }

    private void delete_src(int id) throws SQLException {

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

  
    private class EventHandlerImpl implements EventHandler<ActionEvent> {

        public EventHandlerImpl() {
        }

        @Override
        public void handle(ActionEvent event) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                String sql = "DELETE FROM SRC WHERE Name=?";
                PreparedStatement pstat = con.prepareStatement(sql);
                pstat.setString(1, src_table.getSelectionModel().getSelectedItem().getName());
                pstat.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    con.close();
                    src_table();
                } catch (Exception e) {
                }
            }

        }
    private void delete_bt(ActionEvent event) {
    }

    }
}
