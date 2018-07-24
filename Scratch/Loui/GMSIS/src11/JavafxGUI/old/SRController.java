/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author louiraj
 */
public class SRController implements Initializable {

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
    private TableColumn<Vehicle, String> vehicle_table_warranty;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_model;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_make;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_engszie;
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
    private TableColumn<Parts, String> parts_table_vehreg;
    @FXML
    private TableColumn<Parts, String> parts_table_name;
    @FXML
    private TableColumn<Parts, String> parts_table_des;
    @FXML
    private TableColumn<Parts, Integer> parts_table_dinstalled;
    @FXML
    private TableColumn<Parts, Integer> parts_table_wedate;
    @FXML
    private TableColumn<Parts, Integer> parts_table_cost;



    //private ObservableList<repairParts> data;
    private ObservableList<SRCinfo> srcdata;
//    private ObservableList<Parts> partsdata;
    db db;
    ResultSet rs = null;
    private static Connection con;
    private static Statement stat;

    /**
     *
     */
    public SRController() {
        this.db = db.getInstance();
    }


    @FXML
    private void delete_bt(ActionEvent event) {
    }

    @FXML
    private void print_bt(ActionEvent event) {
    }

    @FXML
    private void bill_bt(ActionEvent event) {
    }

    @FXML
    private void add_parts_bt(ActionEvent event) throws IOException {
      try{
        FXMLLoader partsLoader = new FXMLLoader(getClass().getResource("addParts.fxml"));
        Parent root = (Parent) partsLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      } catch(IOException e){
          System.out.println("Error");
      }
    }

    @FXML
    private void add_vehicle_bt(ActionEvent event) {
        try {

        FXMLLoader vehLoader = new FXMLLoader(getClass().getResource("addVeh.fxml"));
        Parent root1 = (Parent) vehLoader.load();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(root1));
        stage1.show();
                } catch(IOException e){
            System.out.println("Error");
        }
    }

    @FXML
    private void add_bt(ActionEvent event) {
                try {

        FXMLLoader srcLoader = new FXMLLoader(getClass().getResource("addSRC.fxml"));
        Parent root2 = (Parent) srcLoader.load();
        Stage stage2 = new Stage();
        stage2.setScene(new Scene(root2));
        stage2.show();
                } catch(IOException e){
            System.out.println("Error");
        }

    }

    @FXML
    private void first_name_txt(ActionEvent event) {
    }

    @FXML
    private void last_name_txt(ActionEvent event) {
    }

    @FXML
    private void vehicle_reg_txt(ActionEvent event) {
    }


    @FXML
    private void vehicle_table() {
    }

    @FXML
     void src_table() {
      srcdata = FXCollections.observableArrayList();
       try{

           String sql = "SELECT * FROM SRC";
           rs = db.query(sql);
           con = DriverManager.getConnection("jdbc:sqlite:data.db");
           stat = con.createStatement();
           while(rs.next()){
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


       }}catch(SQLException e){
           e.printStackTrace();
           System.err.println("Error");

       }
       src_table.setItems(srcdata);
    }


  /*  void parts_table() {
      partsdata = FXCollections.observableArrayList();
       try{

           String sql = "SELECT * FROM InstalledParts";
           rs = db.query(sql);
           con = DriverManager.getConnection("jdbc:sqlite:data.db");
           stat = con.createStatement();
           while(rs.next()){
             partsdata.add(new Parts(rs.getInt("PartsID"), rs.getString("VehReg"), rs.getString("PartsName"), rs.getString("Description"),
                     rs.getString("DateInstalled"), rs.getString("WarrantyEndDate"), rs.getInt("SRCCost")));
         parts_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PartsID"));
         parts_table_vehreg.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("VehReg"));
         parts_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("PartsName"));
         parts_table_des.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("Description"));
         parts_table_dinstalled.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("DateInstalled"));
         parts_table_wedate.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("WarrantyEndDate"));
         parts_table_cost.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("SRCCost"));
         //.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));


       }}catch(SQLException e){
           e.printStackTrace();
           System.err.println("Error");

       }
       parts_table.setItems(partsdata);
    }  */


       private void insertStatement(String insert_query){

        Connection c = null;
        Statement stmt = null;
        try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:data.db");
      c.setAutoCommit(false);
      stmt = c.createStatement();
      stmt.executeUpdate(insert_query);
      stmt.close();
      c.commit();
      c.close();
    }catch ( ClassNotFoundException | SQLException e ) {

                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
        }

    }

 




    }
