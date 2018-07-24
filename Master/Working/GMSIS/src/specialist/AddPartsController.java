/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

import common.ConnectionDB;
import java.sql.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static javax.management.Query.value;

public class AddPartsController implements Initializable {



    private LocalDate sentDate = null;

    private LocalDate returnDate = null;

    String parts_choicebox_string = null;
    Connection con;
    Statement stat;
    ResultSet rs;
    private Stage stage = new Stage();
    private boolean done = false;
    private SRCinfo srcinfo;
    ConnectionDB sqlConnect;
    PreparedStatement pstat;


    db db;
    @FXML
    private TextField partsIDxx;
    @FXML
    private TextField partsNxx;
    @FXML
    private DatePicker expSendDate;
    @FXML
    private DatePicker expReturnDate;
    @FXML
    private TextArea partsDesxx;
    @FXML
    private TextField vehRegxx;
    @FXML
    private TextField PartsSRCCostxx;
    @FXML
    private ComboBox partsSRCCombo;
    @FXML
    private Button partsCancelBt;
    @FXML
    private Button partsSaveBt;
    @FXML
    private Button partsEditSaveBt;

    public void home() {
      this.db = db.getInstance();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      sqlConnect = new ConnectionDB();
        loadSRCName();

    }

    public void setParts(SRCinfo srcinfo) throws SQLException {
        this.srcinfo = srcinfo;
       // PreparedStatement pstat;
        //String combo = srcinfo.getSRC();

        //   try {
        //     c = DriverManager.getConnection("jdbc:sqlite:data.db");
        //     c.setAutoCommit(false);
        //     stmt = c.createStatement();
    //    try {
      //    con = DriverManager.getConnection("jdbc:sqlite:data.db");
        //  con.setAutoCommit(false);


          //  String query = "SELECT * from InstalledParts WHERE PartsID =?";
      //      pstat = con.prepareStatement(query);
        //    System.out.println(partsNxx.getText());

    //      java.sql.Date expSend = rs.getDate("ExpSend");
    //        java.sql.Date expReturn = rs.getDate("ExpReturn");

        partsIDxx.setText(Integer.toString(srcinfo.getPartsID()));
        partsNxx.setText(srcinfo.getPartName());
        partsDesxx.setText(srcinfo.getDescription());
        vehRegxx.setText(srcinfo.getVehReg());
        PartsSRCCostxx.setText(Float.toString(srcinfo.getSRCCost()));
     //   expSendDate.setValue(expSend.toLocalDate());
     //   expReturnDate.setValue(expReturn.toLocalDate());
      //  partsIDxx.addItem(rs.getString("SRC"));
      //} catch (Exception e){

    //  }
    }


    @FXML
    private void partsSaveBt(ActionEvent event) {

        String query = "INSERT INTO InstalledParts (PartName,Description,PSRCCost,VehReg,PSRC,PExpSend,PExpReturn) VALUES (" + "'" + partsNxx.getText()
                + "'," + "'" + partsDesxx.getText() + "'," + "'" + PartsSRCCostxx.getText() + "'," + "'" + vehRegxx.getText()
                + "'," + "'" + partsSRCCombo.getSelectionModel().getSelectedItem() + "'," + "'" + expSendDate.getValue() + "'," + "'" + expReturnDate.getValue() + "');";

        insertStatement(query);

        Stage stage = null;
        stage = (Stage) partsSaveBt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void partsCancelBt(ActionEvent event) {
        Stage stage = null;
        stage = (Stage) partsCancelBt.getScene().getWindow();
        stage.close();
    }

    public void loadSRCName() {

        String sql = "SELECT Name FROM SRC;";
        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                partsSRCCombo.getItems().addAll(rs.getString("Name"));

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
    private void partsEditSaveBt(ActionEvent event)  {

        PreparedStatement pstat;


        try {
          con = DriverManager.getConnection("jdbc:sqlite:data.db");
          con.setAutoCommit(false);

            String query = "UPDATE InstalledParts SET PartsID = ?, PartName = ?, Description = ?, VehReg = ?, PSRCCost = ?, PExpSend = ?, PExpReturn = ?, PSRC = ? WHERE PartsID =?";

            pstat = con.prepareStatement(query);
            System.out.println(partsNxx.getText());

            pstat.setInt(1, Integer.parseInt(partsIDxx.getText()));
            pstat.setString(2, partsNxx.getText());
            pstat.setString(3, partsDesxx.getText());
            pstat.setString(4, vehRegxx.getText());
            pstat.setFloat(5, Float.parseFloat(PartsSRCCostxx.getText()));
            
            System.out.println(expReturnDate.getValue().toString());
            
            pstat.setString(6, expSendDate.getValue().toString());
            pstat.setString(7, expReturnDate.getValue().toString());
            pstat.setObject(8, partsSRCCombo.getSelectionModel().getSelectedItem());
            pstat.setInt(9, Integer.parseInt(partsIDxx.getText()));
            pstat.execute();
            con.commit();
            con.close();


        } catch (Exception e) {

            e.printStackTrace();
        }
        Stage stage = null;
        stage = (Stage) partsEditSaveBt.getScene().getWindow();
        stage.close();
    }

    Button partsSave() {
        return partsSaveBt;
    }

    Button partsEditSave() {
        return partsEditSaveBt;
    }

    private void insertStatement(String insert_query) {

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
            c.commit();
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






}
