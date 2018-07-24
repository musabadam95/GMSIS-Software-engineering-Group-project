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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author louiraj
 */
public class AddSRCController implements Initializable {


    @FXML
    private Button src_save;
    @FXML
    private TextField src_phone_number_txt;
    @FXML
    private TextField src_email_add_txt;
    @FXML
    private TextField src_post_code_txt;
    @FXML
    private TextField src_county_txt;
    @FXML
    private TextField src_2add_txt;
    @FXML
    private TextField src_1add_txt;
    @FXML
    private TextField src_name_txt;
    @FXML
    private Button src_cancel;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void src_save(ActionEvent event) {
      String query = "INSERT INTO SRC (Name,FirstLineAdd,SecondLineAdd,County,PostCode,PhoneNo,Email) VALUES (" + "'" + src_name_txt.getText() +
      "'," + "'" + src_1add_txt.getText() + "'," + "'" + src_2add_txt.getText() + "'," + "'" + src_county_txt.getText() +
      "'," + "'" + src_post_code_txt.getText() + "'," + "'" + src_phone_number_txt.getText() +
      "'," + "'" + src_email_add_txt.getText() +  "');";

      insertStatement(query);

      Stage stage = null;
      stage=(Stage) src_save.getScene().getWindow();
      stage.close();
    }

    @FXML
    private void src_phone_number_txt(ActionEvent event) {
    }

    @FXML
    private void src_email_add_txt(ActionEvent event) {
    }

    @FXML
    private void src_post_code_txt(ActionEvent event) {
    }

    @FXML
    private void src_county_txt(ActionEvent event) {
    }

    @FXML
    private void src_2add_txt(ActionEvent event) {
    }

    @FXML
    private void src_1add_txt(ActionEvent event) {
    }

    @FXML
    private void src_name_txt(ActionEvent event) {
    }

    @FXML
    private void src_cancel(ActionEvent event) {
      Stage stage = null;
      stage=(Stage) src_cancel.getScene().getWindow();
      stage.close();
    }

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

      }catch ( Exception e ) {
          
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        System.exit(0);
      }
    }

}
