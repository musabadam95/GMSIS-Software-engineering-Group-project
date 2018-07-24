/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import common.ConnectionDB;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddVehController implements Initializable {


    private SRCinfo srcinfo;

    ConnectionDB sqlConnect;
    ResultSet rs = null;
    Connection con = null;
    Statement stat;
    private Stage stage = new Stage();
    private boolean done = false;


    @FXML
    private TextField vehRegxx;
    @FXML
    private DatePicker vehExpSendDate;
    @FXML
    private DatePicker vehExpReturnDate;
    @FXML
    private TextField typexx;
    @FXML
    private TextField modelxx;
    @FXML
    private TextField makexx;
    @FXML
    private TextField engSizexx;
    @FXML
    private TextField vehSPCCostxx;
    @FXML
    private ComboBox vehComboBox;
    @FXML
    private Button vehSaveBt;
    @FXML
    private Button vehEditSaveBt;
    @FXML
    private Button vehCancelBt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      sqlConnect = new ConnectionDB();
      loadSRCName();

    }


    public void setVehicle(SRCinfo srcinfo) {
  //    PreparedStatement pstat;
    //  this.srcinfo = srcinfo;



  //        con = DriverManager.getConnection("jdbc:sqlite:data.db");
    //      con.setAutoCommit(false);

      //      String query = "SELECT * from Vehicles WHERE Vid =?";
        //    pstat = con.prepareStatement(query);

        vehRegxx.setText(srcinfo.getVehRegV());
        typexx.setText(srcinfo.getVehType());
        modelxx.setText(srcinfo.getVehModel());
        makexx.setText(srcinfo.getVehMake());
        engSizexx.setText(Integer.toString(srcinfo.getEngSize()));
        vehSPCCostxx.setText(Float.toString(srcinfo.getVSRCCost()));

     //   part_exp_sent_txt.setVapart_engsize_txtlue(expSend.toLocalDate());
     //   part_exp_return_txt.setValue(expReturn.toLocalDate());
      //  parts_combobox.addItem(rs.getString("SRC"));

    }

    @FXML
    private void vehCancelBt(ActionEvent event) {
        Stage stage = null;
        stage=(Stage) vehCancelBt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void vehSaveBt(ActionEvent event) {
        
         System.out.println("add");
         
      String query = "INSERT INTO Vehicles (VehReg,VehType,VehModel,VehMake,EngSize,VSRCCost,VExpSend,VExpReturn,VSRC) VALUES (" + "'" + vehRegxx.getText()
              + "'," + "'" + typexx.getText() + "'," + "'" + modelxx.getText() + "'," + "'" + makexx.getText()
              + "'," + "'" + engSizexx.getText() + "'," + "'" + vehSPCCostxx.getText() + "'," + "'" + vehExpSendDate.getValue()
               + "'," + "'" + vehExpReturnDate.getValue() + "'," + "'" + vehComboBox.getSelectionModel().getSelectedItem() + "');";

      insertStatement(query);

      Stage stage = null;
      stage = (Stage) vehSaveBt.getScene().getWindow();
      stage.close();
}

public void loadSRCName() {

    String sql = "SELECT Name FROM SRC;";
    try {
        con = DriverManager.getConnection("jdbc:sqlite:data.db");
        stat = con.createStatement();
        rs = stat.executeQuery(sql);

        while (rs.next()) {
            vehComboBox.getItems().addAll(rs.getString("Name"));

            // src_combobox.set
        }
    } catch (SQLException e) {
        e.printStackTrace();

    }

}

public boolean isDone() {

    return done;
}

public void setStage(Stage s) {

    stage = s;
}

    @FXML
    private void vehEditSaveBt(ActionEvent event) {
        System.out.println("update");
    PreparedStatement pstat;

    //   try {
    //     c = DriverManager.getConnection("jdbc:sqlite:data.db");
    //     c.setAutoCommit(false);
    //     stmt = c.createStatement();
    try {
      con = DriverManager.getConnection("jdbc:sqlite:data.db");
      con.setAutoCommit(false);

        String query = "UPDATE Vehicles SET VehReg = ?, VehType = ?, VehModel = ?, VehMake = ?, EngSize = ?, VSRCCost = ?, VExpSend = ?, VExpReturn = ?, VSRC = ? WHERE VehReg =?";
        pstat = con.prepareStatement(query);

        pstat.setString(1, vehRegxx.getText());
        pstat.setString(2, typexx.getText());
        pstat.setString(3, modelxx.getText());
        pstat.setString(4, makexx.getText());
        pstat.setInt(5, Integer.parseInt(engSizexx.getText()));
        pstat.setDouble(6, Double.parseDouble(vehSPCCostxx.getText()));
        
                System.out.println(vehExpSendDate.getValue());

        pstat.setString(7, vehExpSendDate.getValue().toString());
        pstat.setString(8, vehExpReturnDate.getValue().toString());
        pstat.setObject(9, vehComboBox.getSelectionModel().getSelectedItem());
        pstat.setString(10, vehRegxx.getText());
        pstat.execute();
        con.commit();
        con.close();

    } catch (Exception e) {

        e.printStackTrace();
    }
     Stage stage = null;
      stage = (Stage) vehEditSaveBt.getScene().getWindow();
      stage.close();
}

Button vehSave() {
    return vehSaveBt;
}

Button vehEditSave() {
    return vehEditSaveBt;
}


    private void insertStatement(String insert_query){

        Connection c = null;
        Statement stmt = null;
        try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:data.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      stmt = c.createStatement();
      System.out.println("Our query was: " + insert_query);
      stmt.executeUpdate(insert_query);
      stmt.close();
      c.commit();
      c.close();
    }catch ( Exception e ) {
        e.printStackTrace();
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
        }

    }

}
