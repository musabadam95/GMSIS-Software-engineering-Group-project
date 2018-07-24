/**
 * Created by Romade on 15/02/2017.
 */
package Customer;

//import vehicles.Vehicle;
import common.ConnectionDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;



public class CustomerOverviewController implements Initializable {

    @FXML
    private TableView<Customer> CustomerTable;
    @FXML
    private TableColumn<Customer, Integer>  id;
    @FXML
    private TableColumn<Customer, String> firstNameColumn;
    @FXML
    private TableColumn<Customer, String> lastNameColumn;
    @FXML
    private TableColumn<Customer, String> address1Column;
    @FXML
    private TableColumn<Customer, String> address2Column;
    @FXML
    private TableColumn<Customer, String> postcodeColumn;
    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    @FXML
    private TableColumn<Customer, String> emailColumn;
    @FXML
    private TableColumn<Customer, String> typeColumn;
    @FXML
    private Label vehicleLabel;
    @FXML
    private Button addBtn;
    @FXML
    private Button editBtn;
    @FXML
    private Button accountBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private ListView<String> vehiclesListView;
    @FXML
    private TextField searchField;
    @FXML
    private ListView<String> bookingsListView;
    @FXML
    private ListView<String> partsListView;

    private ObservableList<Customer> custData;

    ConnectionDB sqlconnect;
    private Connection conn;

    //private Main main;
    @FXML
    public void setCustomerTable() {

        ObservableList<Customer> customerData=FXCollections.<Customer>observableArrayList();;
       // ArrayList<Customer> data = new ArrayList<Customer>();

        String Sqlite = "SELECT * FROM Customer";
        ResultSet rs;
        try {
             conn = sqlconnect.returnConnection();
            //conn = sqlconnect.returnConnection();
            Statement st = conn.createStatement();
            rs = st.executeQuery(Sqlite);
            //System.out.println(rs.isBeforeFirst());
            while (rs.next()) {
                customerData.add(new Customer(rs.getInt("CusID"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("PostCode"), rs.getString("PhoneNo"), rs.getString("Email"),
                        rs.getString("BusinessType")));
            }
            customerData = FXCollections.observableList(customerData);
            //CustomerTable.setItems(customerData);
            id.setCellValueFactory(
                    new PropertyValueFactory<Customer, Integer>("id")
            );

            firstNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("firstName")
            );
            lastNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("lastName")
            );

            address1Column.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("firstLineAdd")
            );

            address2Column.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("secondLineAdd")
            );

            postcodeColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("postCode")
            );

            phoneNumberColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("phone")
            );

            emailColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("email")
            );

            typeColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("type")
            );

            CustomerTable.setItems(customerData);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

    }

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sqlconnect = new ConnectionDB();
        setCustomerTable();
        //This listener can be added-on-to, to get the value of the selected customer.

        editBtn.setDisable(true);
        deleteBtn.setDisable(true);
        CustomerTable.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) -> {
            if (newValue != null) {
                setVehiclesListView(CustomerTable.getSelectionModel().getSelectedItem().getId());
                accountBtn.setDisable(false);
                editBtn.setDisable(false);
                deleteBtn.setDisable(false);
            } else{
                accountBtn.setDisable(true);
                editBtn.setDisable(true);
                deleteBtn.setDisable(true);
            }
        });
        vehiclesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if(newValue != null) {
                    setBookingsListView();
                    vehicleLabel.setText(vehiclesListView.getSelectionModel().getSelectedItem());
                }
                else{
                    bookingsListView.setItems(FXCollections.observableArrayList());
                    vehicleLabel.setText(null);
                }
        });

        bookingsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
               if(newValue != null) setPartsListView();
               else partsListView.setItems(FXCollections.observableArrayList());
        });
        //deleteBtn.setDisable(true);

    }

    @FXML
    private void handleDeleteButton() throws SQLException {

        int cid = CustomerTable.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = CustomerTable.getSelectionModel().getSelectedIndex();

        Connection conn = sqlconnect.returnConnection();


        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Delete Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure to delete");
        Optional<ButtonType> action = alert.showAndWait();
        if(action.get() == ButtonType.OK) {

            String delQuery = "DELETE FROM CUSTOMER WHERE CusID = ?";
            try (PreparedStatement st = conn.prepareStatement(delQuery)) {

                st.setInt(1, cid);
                st.executeUpdate();
                //conn.close();
                //CustomerTable.getSelectionModel().clearSelection();
                CustomerTable.getItems().remove(selectedIndex);
                setCustomerTable();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
       
    }

//    private void refreshTable() {
//
//        CustomerTable.setItems(custData);
//    }

    @FXML
    private void handleEdit() {
        if (!popup( CustomerTable.getSelectionModel().getSelectedItem(), true)) {
            System.out.println("Nothing is Edited");
        } else setCustomerTable();
    }

    @FXML
    private void handleAdd() {
        if (!popup(null, false)) {
            System.out.println("No User Added");
        } else setCustomerTable();

    }

    @FXML
    private void handleAccount() {
        try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("CustomerAccount.fxml"));
                Pane page = fxmlLoader.load();

                Stage accountStage = new Stage();
                accountStage.setTitle("View Account");
                accountStage.initModality(Modality.WINDOW_MODAL);
                Scene scene = new Scene(page);
                accountStage.setScene(scene);

                CustomerAccountController controller = fxmlLoader.getController();
                controller.setAll(CustomerTable.getSelectionModel().getSelectedItem().getId());
                controller.setStage(accountStage);
                accountStage.showAndWait();
                //return controller.isDone();

            } catch (IOException e) {

                e.printStackTrace();
                //return false;
            }

    }


    private boolean popup(Customer customer, boolean isEditing) {
        try {
            FXMLLoader loads = new FXMLLoader();
            loads.setLocation(getClass().getResource("AddCustomer.fxml"));
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (isEditing) {
                popStage.setTitle("Edit Customer");
            } else {
                popStage.setTitle("Add new Customer");
            }
            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            CustomerPopUp controller = loads.getController();
            controller.setStage(popStage);
            if (isEditing) {
                controller.setCustomer(customer);
                controller.getSaveBtn().setVisible(true);
                controller.getSaveBtn().requestFocus();
                controller.getCreateBtn().setFocusTraversable(false);

            } else {
                controller.getCreateBtn().setVisible(true);
                controller.getSaveBtn().setFocusTraversable(false);
                controller.getCreateBtn().requestFocus();
            }
            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    private void setVehiclesListView(int cusID) {

          Connection connection = sqlconnect.returnConnection();

        //ArrayList<Vehicle> data = new ArrayList<Vehicle>();
        ObservableList<String> vehReg = FXCollections.observableArrayList();
        String Sqlite = "SELECT VehReg FROM Vehicles WHERE CustomerID = '" + cusID + "'";
        ResultSet rs;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);

            while (rs.next()) {
                vehReg.add(rs.getString(1));
            }
            vehiclesListView.setItems(FXCollections.observableList(vehReg));
            //vehReg.set()
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

    }


    public void setBookingsListView(){
        //Customer customer = CustomerTable.getSelectionModel().getSelectedItem();
        String reg = vehiclesListView.getSelectionModel().getSelectedItem();
        vehicleLabel.setText(reg);
        conn = sqlconnect.returnConnection();
        ArrayList<String> bookingData= new ArrayList<>();
        String sqlBookings = "SELECT BookingID FROM Bookings WHERE VehReg = '" + reg + "'";
        //String sqlPart = "SELECT PartsID FROM InstalledParts WHERE VehReg ="
        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlBookings);

            while(rs.next()){
                bookingData.add("Booking ID: " + rs.getInt(1));
            }
        } catch (SQLException se){
            se.printStackTrace();
        }

        bookingsListView.setItems(FXCollections.observableArrayList(bookingData));
    }

    public void setPartsListView(){
        conn= sqlconnect.returnConnection();
        ArrayList<String> partsData = new ArrayList<>();
        String sqlParts = "SELECT PartsID from InstalledParts WHERE BookingID = '" + bookingsListView.getSelectionModel().getSelectedItem().split(": ")[1] + "'";

        try{
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sqlParts);

            while(rs.next()){
                partsData.add("Part ID: " + rs.getInt(1));
            }

        }catch (SQLException se){
            se.printStackTrace();
        }
        partsListView.setItems(FXCollections.observableArrayList(partsData));
    }


    @FXML
    private void filter(ActionEvent event) {
             ObservableList<Customer> customerData=FXCollections.<Customer>observableArrayList();;

       String searched=searchField.getText();
       System.out.println(searched);
       String srhQuery="SELECT * FROM Customer WHERE  FirstName LIKE '%"+searched+"%' OR LastName LIKE '%"+searched+"%' OR CusID LIKE '%"+searched+"%'";
        ResultSet rs;
        try {
             conn = sqlconnect.returnConnection();
            //conn = sqlconnect.returnConnection();
            Statement st = conn.createStatement();
          
           
            rs = st.executeQuery(srhQuery);
            //System.out.println(rs.isBeforeFirst());
            while (rs.next()) {
                customerData.add(new Customer(rs.getInt("CusID"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("PostCode"), rs.getString("PhoneNo"), rs.getString("Email"),
                        rs.getString("BusinessType")));
            }
            customerData = FXCollections.observableList(customerData);
            //CustomerTable.setItems(customerData);
            id.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id")
            );

            firstNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("firstName")
            );
            lastNameColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("lastName")
            );

            address1Column.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("firstLineAdd"));

            address2Column.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("secondLineAdd")
            );

            postcodeColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("postCode")
            );

            phoneNumberColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("phone")
            );

            emailColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("email")
            );

            typeColumn.setCellValueFactory(
                    new PropertyValueFactory<Customer,String>("type")
            );
            CustomerTable.setItems(customerData);
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

    
    }

}
