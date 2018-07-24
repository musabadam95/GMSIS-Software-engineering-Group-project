/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication5;

import JavafxGUI.SRCinfo;
import JavafxGUI.db;
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
public class FXMLDocumentController implements Initializable {

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
    private TableView<?> parts_table;
    @FXML
    private TableView<?> vehicle_table;
    @FXML
    private TextField src_name_txt;
    @FXML
    private TextField src_phone_number_txt;
    @FXML
    private TextField src_email_add_txt;
    @FXML
    private TextField src_1add_txt;
    @FXML
    private TextField src_2add_txt;
    @FXML
    private TextField src_post_code_txt;
    @FXML
    private TextField src_county_txt;
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

    //private ObservableList<repairParts> data;
    private ObservableList<SRCinfo> srcdb;
    db db;
    ResultSet rs = null;

    private static Connection con;
    private static Statement stat;

    /**
     *
     */
    public FXMLDocumentController() {
        this.db = db.getInstance();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        src_table();
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
        Parent root1 = (Parent) partsLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();
      } catch(IOException e){
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
                } catch(IOException e){
            System.out.println("Error");
        }
    }

    @FXML
    private void add_bt(ActionEvent event) {
      String query = "INSERT INTO SRC (Name,FirstLineAdd,SecondLineAdd,County,PostCode,PhoneNo,Email) VALUES (" + "'" + src_name_txt.getText() +
      "'," + "'" + src_1add_txt.getText() + "'," + "'" + src_2add_txt.getText() + "'," + "'" + src_county_txt.getText() +
      "'," + "'" + src_post_code_txt.getText() + "'," + "'" + src_phone_number_txt.getText() +
      "'," + "'" + src_email_add_txt.getText() +  "');";

      insertStatement(query);

      Stage stage = null;
      stage=(Stage) add_bt.getScene().getWindow();
      stage.close();
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
    private void parts_table() {
    }

    @FXML
    private void vehicle_table() {
    }

    @FXML
    private void src_name_txt(ActionEvent event) {
    }

    @FXML
    private void src_phone_number_txt(ActionEvent event) {
    }

    @FXML
    private void src_email_add_txt(ActionEvent event) {
    }

    @FXML
    private void src_1add_txt(ActionEvent event) {
    }

    @FXML
    private void src_2add_txt(ActionEvent event) {
    }

    @FXML
    private void src_post_code_txt(ActionEvent event) {
    }

    @FXML
    private void src_county_txt(ActionEvent event) {
    }

    @FXML
    private void src_table() {
      srcdb = FXCollections.observableArrayList();
       try{

           String sql = "SELECT * FROM SRC";
           rs = db.query(sql);
           con = DriverManager.getConnection("jdbc:sqlite:DB.db");
           stat = con.createStatement();
           while(rs.next()){
             srcdb.add(new SRCinfo( rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                     rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));

         src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
         src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
         src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
         src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
         src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
         src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
         src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));


        // partsTable.setItems(data);

       }}catch(Exception e){
           System.err.println("Error");

       }
       src_table.setItems(srcdb);
    }

       private void insertStatement(String insert_query){

        Connection c = null;
        Statement stmt = null;
        try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:DB.db");
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
