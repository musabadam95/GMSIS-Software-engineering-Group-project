/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import java.sql.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPartsController implements Initializable {

    @FXML
    private TextField part_name_txt;
    @FXML
    private DatePicker part_exp_sent_txt;
    @FXML
    private DatePicker part_exp_return_txt;
    @FXML
    private TextArea part_des_txt;
    @FXML
    private Button part_save;
    @FXML
    private Button part_cancel;
    @FXML
    private TextField part_src_cost_txt;
    @FXML
    private TextField part_vehreg_txt;

    private LocalDate sentDate = null;

    private LocalDate returnDate = null;
    @FXML
    private ChoiceBox parts_choicebox;

    String parts_choicebox_string = null;
    Connection con;
    Statement stat;
    ResultSet rs;

    public void home() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSRCName();
    }

    @FXML
    private void src_name_txt(ActionEvent event) {
    }

    @FXML
    private void src_save(ActionEvent event) {
       
        String query = "INSERT INTO InstalledParts (PartName,Description,Cost,VehReg,SRC,ExpSend,ExpReturn) VALUES (" + "'" + part_name_txt.getText()
                + "'," + "'" + part_des_txt.getText() + "'," + "'" + part_src_cost_txt.getText() + "'," + "'" + part_vehreg_txt.getText() +
      "'," + "'" + parts_choicebox.getSelectionModel().getSelectedItem() + "'," + "'" + part_exp_sent_txt.getValue() + "'," + "'" + part_exp_sent_txt.getValue() + "');";

        insertStatement(query);

        Stage stage = null;
        stage = (Stage) part_save.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void src_cancel(ActionEvent event) {
        Stage stage = null;
        stage = (Stage) part_cancel.getScene().getWindow();
        stage.close();
    }

    public void loadSRCName () {

    String sql = "SELECT Name FROM SRC;";
    try{
    con = DriverManager.getConnection("jdbc:sqlite:data.db");
    stat = con.createStatement();
    rs = stat.executeQuery(sql);
        
    while(rs.next()){
    parts_choicebox.getItems().addAll(rs.getString("Name"));

   // src_combobox.set
    }
    } catch (SQLException e){
    e.printStackTrace();

    }

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
