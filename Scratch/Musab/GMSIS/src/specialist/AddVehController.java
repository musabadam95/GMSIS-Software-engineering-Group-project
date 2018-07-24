/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddVehController implements Initializable {

    @FXML
    private TextField vehicle_make_txt;
    @FXML
    private TextField vehicle_modle_txt;
    @FXML
    private TextField vhicle_exp_return_txt;
    @FXML
    private TextArea vhicle_des_txt;
    @FXML
    private Button cancel_bt;
    @FXML
    private Button vhicle_add_bt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void vehicle_make_txt(ActionEvent event) {
    }

    @FXML
    private void vehicle_modle_txt(ActionEvent event) {
    }

    @FXML
    private void vhicle_exp_return_txt(ActionEvent event) {
    }

    @FXML
    private void cancel_bt(ActionEvent event) {
        Stage stage = null;
        stage=(Stage) cancel_bt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void vhicle_add_bt(ActionEvent event) {

      String query = "INSERT INTO SRCVEHICLE (MAKE,MODEL,MILEAGE, EXPECTEDRETURN) VALUES (" + "'" + vehicle_make_txt.getText() +
        "'," + "'" + vehicle_modle_txt.getText() + "'," + "'" + vhicle_exp_return_txt.getText() +  "');";

        insertStatement(query);

        Stage stage = null;
        stage=(Stage) vhicle_add_bt.getScene().getWindow();
        stage.close();


    }





    private void insertStatement(String insert_query){

        Connection c = null;
        Statement stmt = null;
        try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:DB.db");
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
