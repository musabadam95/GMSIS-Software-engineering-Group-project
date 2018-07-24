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
private Connection conn;
ConnectionDB sqlConnect;
    ResultSet rs = null;
    Connection con = null;
    private Stage stage = new Stage();
    private boolean done = false;
    private SRCinfo srcinfo;
    @FXML
    private Button src_save_edit;;
  //  SRCController controller = new SRCController();
    @FXML
    private TextField src_id_txt;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //    this.controller = controller;

          sqlConnect = new ConnectionDB();
    }

    public void setSRC(SRCinfo srcinfo){
        this.srcinfo = srcinfo;

        src_id_txt.setText(Integer.toString(srcinfo.getID()));
        src_name_txt.setText(srcinfo.getName());
        src_1add_txt.setText(srcinfo.getFirstLineAdd());
        src_2add_txt.setText(srcinfo.getSecondLineAdd());
        src_county_txt.setText(srcinfo.getCounty());
        src_post_code_txt.setText(srcinfo.getPostCode());
        src_phone_number_txt.setText(Integer.toString(srcinfo.getPhoneNo()));
        src_email_add_txt.setText(srcinfo.getEmail());
    }

    @FXML
     private void src_save() {


        String query = "INSERT INTO SRC (Name,FirstLineAdd,SecondLineAdd,County,PostCode,PhoneNo,Email) VALUES (" + "'" + src_name_txt.getText()
                + "'," + "'" + src_1add_txt.getText() + "'," + "'" + src_2add_txt.getText() + "'," + "'" + src_county_txt.getText()
                + "'," + "'" + src_post_code_txt.getText() + "'," + "'" + src_phone_number_txt.getText()
                + "'," + "'" + src_email_add_txt.getText() + "');";

        insertStatement(query);

        Stage stage = null;
        stage = (Stage) src_save.getScene().getWindow();
        stage.close();

    //    controller.src_table();




  }
    public boolean isDone(){

        return done;
    }

    public void setStage(Stage s){

        stage = s;
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
        stage = (Stage) src_cancel.getScene().getWindow();
        stage.close();
    }

    private void insertStatement(String insert_query) {

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

        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                c.close();
                stmt.close();
            } catch (Exception e) {

            }
        }
    }

    @FXML
     private void src_save_edit() throws SQLException  {

       PreparedStatement pstat;

    //   try {
    //     c = DriverManager.getConnection("jdbc:sqlite:data.db");
    //     c.setAutoCommit(false);
    //     stmt = c.createStatement();

    try {
      con =sqlConnect.returnConnection();
      con.setAutoCommit(false);

      String query = "UPDATE SRC SET ID = ?, Name = ?, FirstLineAdd = ?, SecondLineAdd = ?, County = ?, PostCode = ?, PhoneNo = ?, Email = ? WHERE ID =?";
      pstat = con.prepareStatement(query);
      System.out.println(src_name_txt.getText());


      pstat.setInt(1, Integer.parseInt(src_id_txt.getText()));
      pstat.setString(2, src_name_txt.getText());
      pstat.setString(3, src_1add_txt.getText());
      pstat.setString(4, src_2add_txt.getText());
      pstat.setString(5, src_county_txt.getText());
      pstat.setString(6, src_post_code_txt.getText());
      pstat.setInt(7, Integer.parseInt(src_phone_number_txt.getText()));
      pstat.setString(8, src_email_add_txt.getText());
      pstat.setInt(9, Integer.parseInt(src_id_txt.getText()));
      pstat.execute();
      con.commit();
      con.close();

    } catch (Exception e){

      e.printStackTrace();
    }

  /*       String query = "UPDATE SRC SET  (Name,FirstLineAdd,SecondLineAdd,County,PostCode,PhoneNo,Email) VALUES ( " + "'" + src_name_txt.getText()
                 + "'," + "'" + src_1add_txt.getText() + "'," + "'" + src_2add_txt.getText() + "'," + "'" + src_county_txt.getText()
                 + "'," + "'" + src_post_code_txt.getText() + "'," + "'" + src_phone_number_txt.getText()
                 + "'," + "'" + src_email_add_txt.getText() + "')";   */

         Stage stage = null;
         stage = (Stage) src_save_edit.getScene().getWindow();
         stage.close();


}
 Button src_save1(){
 return src_save;
 }

 Button src_save_edit1(){
   return src_save_edit;
 }

    @FXML
    private void src_id_txt(ActionEvent event) {
    }

}
