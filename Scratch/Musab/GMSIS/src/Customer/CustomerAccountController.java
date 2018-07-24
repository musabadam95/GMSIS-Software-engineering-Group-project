
package Customer;

import common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Romade on 23/02/2017.
 */
public class CustomerAccountController implements Initializable{

    private Stage stage;

    @FXML private TableView<Account> accTable;
    @FXML private TableColumn<Account, Integer> billID;
    @FXML private TableColumn<Account, Integer> bookingID;
    @FXML private TableColumn<Account, String> vehReg;
    @FXML private TableColumn<Account, Double> total;
    @FXML private TableColumn<Account, String> status;

    @FXML
    private ChoiceBox<String> payStatsChoice;

    private ConnectionDB sqlconnect = new ConnectionDB();
    private Connection conn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //stage.showAndWait();
        setColumns();
        ObservableList<String> paymentData = FXCollections.observableArrayList("Paid", "Not Paid");
        payStatsChoice.setItems(paymentData);

    }



    public void setStage(Stage s){

        stage = s;
    }

    @FXML
    private void handleCancel(){

        stage.close();
    }

    @FXML
    private void handleOk()throws SQLException{
        if(payStatsChoice.getSelectionModel().getSelectedItem() != null &&
                accTable.getSelectionModel().getSelectedItem() != null){
            String stats = payStatsChoice.getSelectionModel().getSelectedItem();
            conn = sqlconnect.returnConnection();

            try{
                String saveQuery = "UPDATE CustomerAccount SET PaymentStatus =? WHERE BillID =?";
                PreparedStatement st = conn.prepareStatement(saveQuery);
                st.setString(1, stats);
                st.setInt(2, accTable.getSelectionModel().getSelectedItem().getBillID());


                st.executeUpdate();
                conn.close();

            }catch(SQLException ex){
                throw ex;
            }

            stage.close();
        } else {
            Alert fAlert = new Alert(Alert.AlertType.WARNING);
            fAlert.setTitle("Warning");
            fAlert.setHeaderText(null);
            fAlert.setContentText("Please select a Payment Status and select a row from the table!");
            fAlert.showAndWait();
        }
    }


    public void setAll(int id){
        //cusIdLbl.setText("" + id);
        String sql = "SELECT * FROM CustomerAccount INNER JOIN Bookings ON CustomerAccount.BookingID=Bookings.BookingID WHERE CustomerAccount.CusID = " + id;
        Connection con = new ConnectionDB().returnConnection();
        ArrayList<Account> accList = new ArrayList<Account>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
                accList.add(new Account(rs));
        } catch (SQLException se){
            se.printStackTrace();
        }
        accTable.setItems(FXCollections.observableArrayList(accList));
    }

    public void setColumns(){
     billID.setCellValueFactory(new PropertyValueFactory<>("billID"));
     bookingID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
     vehReg.setCellValueFactory(new PropertyValueFactory<>("vehReg"));
     total.setCellValueFactory(new PropertyValueFactory<>("total"));
     status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }





}
