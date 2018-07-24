/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

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


    private Connection conn;
    ConnectionDB sqlConnect;
    ResultSet rs = null;
    Connection con = null;
    private Stage stage = new Stage();
    private boolean done = false;
    private SRCinfo srcinfo;

    @FXML
    private TextField srcNxx;
    @FXML
    private TextField firstLineAddxx;
    @FXML
    private TextField secLineAddxx;
    @FXML
    private TextField countryxx;
    @FXML
    private TextField postCodexx;
    @FXML
    private TextField phoneNumberxx;
    @FXML
    private Button srcSaveBt;
    @FXML
    private Button srcEditSaveBt;
    @FXML
    private Button srcCancelBt;
    @FXML
    private TextField srcIDxx;
    @FXML
    private TextField emailAddxx;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
    //    this.controller = controller;

          sqlConnect = new ConnectionDB();
    }

    public void setSRC(SRCinfo srcinfo){
        this.srcinfo = srcinfo;

        srcIDxx.setText(Integer.toString(srcinfo.getID()));
        srcNxx.setText(srcinfo.getName());
        firstLineAddxx.setText(srcinfo.getFirstLineAdd());
        secLineAddxx.setText(srcinfo.getSecondLineAdd());
        countryxx.setText(srcinfo.getCounty());
        postCodexx.setText(srcinfo.getPostCode());
        phoneNumberxx.setText(Integer.toString(srcinfo.getPhoneNo()));
        emailAddxx.setText(srcinfo.getEmail());
    }

    @FXML
     private void srcSaveBt() {


        String query = "INSERT INTO SRC (Name,FirstLineAdd,SecondLineAdd,County,PostCode,PhoneNo,Email) VALUES (" + "'" + srcNxx.getText()
                + "'," + "'" + firstLineAddxx.getText() + "'," + "'" + secLineAddxx.getText() + "'," + "'" + countryxx.getText()
                + "'," + "'" + postCodexx.getText() + "'," + "'" + phoneNumberxx.getText()
                + "'," + "'" + emailAddxx.getText() + "');";

        insertStatement(query);

        Stage stage = null;
        stage = (Stage) srcSaveBt.getScene().getWindow();
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
    private void srcCancelBt(ActionEvent event) {
        Stage stage = null;
        stage = (Stage) srcCancelBt.getScene().getWindow();
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
     private void srcEditSaveBt() throws SQLException  {

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
      System.out.println(srcNxx.getText());


      pstat.setInt(1, Integer.parseInt(srcIDxx.getText()));
      pstat.setString(2, srcNxx.getText());
      pstat.setString(3, firstLineAddxx.getText());
      pstat.setString(4, secLineAddxx.getText());
      pstat.setString(5, countryxx.getText());
      pstat.setString(6, postCodexx.getText());
      pstat.setInt(7, Integer.parseInt(phoneNumberxx.getText()));
      pstat.setString(8, emailAddxx.getText());
      pstat.setInt(9, Integer.parseInt(srcIDxx.getText()));
      pstat.execute();
      con.commit();
      con.close();

    } catch (Exception e){

      e.printStackTrace();
    }

  /*       String query = "UPDATE SRC SET  (Name,FirstLineAdd,SecondLineAdd,County,PostCode,PhoneNo,Email) VALUES ( " + "'" + srcNxx.getText()
                 + "'," + "'" + firstLineAddxx.getText() + "'," + "'" + secLineAddxx.getText() + "'," + "'" + countryxx.getText()
                 + "'," + "'" + postCodexx.getText() + "'," + "'" + phoneNumberxx.getText()
                 + "'," + "'" + emailAddxx.getText() + "')";   */

         Stage stage = null;
         stage = (Stage) srcEditSaveBt.getScene().getWindow();
         stage.close();


}
 Button srcSave(){
 return srcSaveBt;
 }

 Button srcEditSave(){
   return srcEditSaveBt;
 }




}
