/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddPartsController implements Initializable {


    public void home(){

    }


    @FXML
    private TextField part_name_txt;
    @FXML
    private TextField part_id_txt;
    @FXML
    private TextField exp_return_txt;
    @FXML
    private TextArea description_txt;
    @FXML
    private Button cancel_bt;
    @FXML
    private Button add_part_bt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void part_name_txt(ActionEvent event) {
    }

    @FXML
    private void part_id_txt(ActionEvent event) {
    }

    @FXML
    private void exp_return_txt(ActionEvent event) {
    }

    @FXML
    private void cancel_bt(ActionEvent event) {
        Stage stage = null;
        stage=(Stage) cancel_bt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void add_part_bt(ActionEvent event) {
            String query = "INSERT INTO SRCPARTS (IDNUMBER,NAME,DESCRIPTION,RETURN) VALUES (" + "'" + part_name_txt.getText() +
             "'," + "'" + part_name_txt.getText() + "'," + "'" + description_txt.getText() + "'," + "'" +
             exp_return_txt.getText() + "');";

             insertStatement(query);

        Stage stage = null;
        stage=(Stage) add_part_bt.getScene().getWindow();
        stage.close();
    }

    private void insertStatement(String insert_query){

        Connection c = null;
        Statement stmt = null;
        try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:/Users/hemantpunni/Documents/Database//garage.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      stmt = c.createStatement();
      System.out.println("Our query was: " + insert_query);
      stmt.executeUpdate(insert_query);
      stmt.close();
      c.commit();
      c.close();
    }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
        }

    }
}
