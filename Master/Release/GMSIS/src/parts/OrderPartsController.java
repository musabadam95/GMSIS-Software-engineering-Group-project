package parts;

import common.ConnectionDB;
import java.net.URL;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author muzab
 */
public class OrderPartsController implements Initializable {

    @FXML
    private ComboBox<String> foundNamesList;
    @FXML
    private TextField searchFNameTxt;
    @FXML
    private Label customerIdTxt;
    @FXML
    private Label firstNameTxt;
    @FXML
    private Label lastNameTxt;
    @FXML

    private Label VehicleTxt;
    @FXML
    private Label vehicleTypeTxt;
    @FXML
    private Label vehRegTxt;
    @FXML
    private ComboBox<String> bookedTimeDateDropDown;
    @FXML
    private Label bookedTimeTxt;
    @FXML
    private Label bookedDateTxt;
    @FXML
    private Label bookedIDTxt;
    @FXML
    private ComboBox<String> partListDropDown;
    @FXML
    private Button addPrtBtn;
    @FXML
    private Button deletePrtBtn;
    @FXML
    private Button changePrtBtn;
    private Connection connection;
    @FXML
    private TableView<Parts> bookPartList;
    @FXML
    private TableColumn colPartId;
    @FXML
    private TableColumn colPartName;
    @FXML
    private TableColumn colCost;
    @FXML
    private TableColumn colDateInstal;
    @FXML
    private TableColumn colWarranty;
    private ConnectionDB sqlConnect;
    @FXML
    private Label descTxt;
    @FXML
    private Label costTxt;
    @FXML
    private Label partNmTxt;
    @FXML
    private Button refreshBtn;
    @FXML
    private Label bookingBillTxt;
    @FXML
    private TableColumn colSPC;
    @FXML
    private ComboBox<String> vehicleRegDropDown;
    @FXML
    private TextField searchPartTxt;
    @FXML
    private Label usedDescTxt;
    @FXML
    private Label usedCostTxt;
    @FXML
    private Label usedPartNmTxt;
    @FXML
    private Label usedPartNmTxt1;
    @FXML
    private Label dateInstalledTxt;
    @FXML
    private Label warrantyTxt;
    Alert alert;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        sqlConnect = new ConnectionDB();
        userListDropDown("");
        partListDropDown();
    }

    @FXML
    public void refreshAll() {
        partListDropDown();
    }

    @FXML
    private void searchName(KeyEvent event) {
        userListDropDown(searchFNameTxt.getText());
    }

    public void userListDropDown(String name) {
        //clear combo values
        foundNamesList.getItems().clear();
        connection = sqlConnect.returnConnection();
        String Sqlite = null;
        if (name.equals("")) {
            Sqlite = "SELECT * FROM Customer";
        } else {
            Sqlite = "SELECT * FROM Customer WHERE FirstName LIKE '%" + name + "%'";
        }
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                foundNamesList.getItems().add(rs.getString("FirstName") + "-" + rs.getString("LastName"));
            }
            connection.close();
        } catch (SQLException ex) {

        }

    }

    public void partListDropDown() {
        //clear combo values
        partListDropDown.getItems().clear();
        connection = sqlConnect.returnConnection();
        String Sqlite = Sqlite = "SELECT * FROM PartStock WHERE Stock >0";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                partListDropDown.getItems().add(rs.getString("PartName"));
            }
            connection.close();
        } catch (SQLException ex) {
        }

    }

    public void changeBill(String bookingID) {
        try {
            String billSql = "SELECT Bill FROM CustomerAccount WHERE BookingID='" + bookingID + "'";
            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(billSql);
            if (!rs.next()) {
                st.close();
                PreparedStatement pt = connection.prepareStatement("INSERT INTO CustomerAccount (CusID,BookingID,Bill,PaymentStatus,VehReg)  values (?, ?, ?, ?,?)");
                pt.setString(1, customerIdTxt.getText());
                pt.setString(2, bookingID);
                pt.setDouble(3, 0.0);
                pt.setString(4, "Not Paid");
                    pt.setString(5, vehRegTxt.getText() );
                pt.execute();
            } else {
                bookingBillTxt.setText(Integer.toString(rs.getInt("Bill")));
            }
            connection.close();
        } catch (SQLException ex) {
        }

    }

    private void refreshAll(String vReg, int bID) {

        connection = sqlConnect.returnConnection();
        //populate tableview using data from database
        ObservableList<Parts> data = FXCollections.<Parts>observableArrayList();
        String Sqlite = "SELECT * FROM `InstalledParts` INNER JOIN Bookings ON InstalledParts.VehReg=Bookings.VehicReg WHERE InstalledParts.VehReg='" + vReg + "' AND Bookings.BookingID=" + bID;
        ResultSet rs;

        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {

                data.add(new Parts(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("DateInstalled"), rs.getString("WarrantyEndDate"), rs.getInt("Cost"), rs.getString("PSRC")));
            }

            colPartId.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartID"));
            colPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("partName"));
            colDateInstal.setCellValueFactory(new PropertyValueFactory<Parts, String>("DateInstalled"));
            colCost.setCellValueFactory(new PropertyValueFactory<Parts, String>("cost"));
            colWarranty.setCellValueFactory(new PropertyValueFactory<Parts, String>("Warranty"));
            colSPC.setCellValueFactory(new PropertyValueFactory<Parts, String>("SPC"));
            bookPartList.setItems(data);
            connection.close();
            changeBill(bookedIDTxt.getText());
        } catch (SQLException ex) {

        }

    }

    @FXML
    private void addPart(ActionEvent event) {
        Date dateInstalled;
        Date warrantyEnd;
        Calendar today = Calendar.getInstance();
        dateInstalled = (Date) today.getTime();
        today.add(Calendar.YEAR, 1);
        warrantyEnd = (Date) today.getTime();
        String query = " insert into InstalledParts (VehReg, PartName, Description,DateInstalled, WarrantyEndDate, Cost,BookingID)"
                + " values (?, ?, ?, ?,?,?,?)";
        String cstmerQuery = "UPDATE CustomerAccount SET Bill=Bill+?  WHERE BookingID='" + bookedIDTxt.getText() + "'";
        //create preparedStatement
        double totalCost = 0;
        //ensure customeraccount record is created
        changeBill(bookedIDTxt.getText());
        try {
            connection = sqlConnect.returnConnection();
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setString(1, vehRegTxt.getText());
            prStatement.setString(2, partNmTxt.getText());
            prStatement.setString(3, descTxt.getText());
            prStatement.setString(4, dateInstalled.toString());
            prStatement.setString(5, warrantyEnd.toString());
            prStatement.setString(6, costTxt.getText());
            totalCost = +Double.parseDouble(costTxt.getText());
            prStatement.setInt(7, Integer.parseInt(bookedIDTxt.getText()));
            prStatement.execute();
            prStatement = connection.prepareStatement(cstmerQuery);
            prStatement.setDouble(1, totalCost);
            prStatement.execute();
            connection.close();
            partWithdraw(partNmTxt.getText());
            refreshAll(vehRegTxt.getText(), Integer.parseInt(bookedIDTxt.getText()));
            changeBill(bookedIDTxt.getText());
            partListDropDown();
        } catch (Exception e) {
            e.printStackTrace();
            alert.showAndWait();
            switch (e.getClass().getSimpleName()) {
                case "NumberFormatException":
                    if (partListDropDown.getValue() == null) {
                        alert.setContentText("Please Select part from drop down menu before clicking Edit");
                        alert.showAndWait();

                    }
            
                    if (bookedTimeDateDropDown.getValue() == null) {
                        alert.setContentText("Booking time has not been selected");
                        alert.showAndWait();

                    }
                default:
                    break;

            }
        }

    }

    public void partWithdraw(String partName) {
        connection = sqlConnect.returnConnection();
        try {
            String sqlQuery = "UPDATE PartStock SET Stock=Stock-1 WHERE PartName=?";
            PreparedStatement prStatement = connection.prepareStatement(sqlQuery);
            prStatement.setString(1, partName);
            prStatement.execute();
            connection.close();
        } catch (SQLException ex) {
        }
    }

    public void partRefund(String partName, int bookingID) {
        connection = sqlConnect.returnConnection();
        try {
            String sqlQuery = "UPDATE PartStock SET Stock=Stock+1 WHERE PartName=?";
            String sqlQuery2 = "UPDATE CustomerAccount SET Bill=Bill-?  WHERE BookingID='" + bookedIDTxt.getText() + "'";
            PreparedStatement prStatement = connection.prepareStatement(sqlQuery);
            prStatement.setString(1, partName);
            prStatement.execute();
            prStatement = connection.prepareStatement(sqlQuery2);
            prStatement.setDouble(1, Double.parseDouble(usedCostTxt.getText()));
            prStatement.execute();
            connection.close();
            partListDropDown();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void deletePart(ActionEvent event) {
        connection = sqlConnect.returnConnection();
        if (JOptionPane.showConfirmDialog(null, "Are you sure about deleting item No." + bookPartList.getSelectionModel().getSelectedItem().getPartID() + ":" + bookPartList.getSelectionModel().getSelectedItem().getPartName() + "?", "Choose", JOptionPane.YES_NO_OPTION) == 1) {
        } else {
            try {
                int PartID = bookPartList.getSelectionModel().getSelectedItem().getPartID();
                String sql = "DELETE FROM InstalledParts WHERE PartsID='" + PartID + "'";
                Statement st = connection.createStatement();
                st.execute(sql);
                partRefund(bookPartList.getSelectionModel().getSelectedItem().getPartName(), Integer.parseInt(bookedIDTxt.getText()));
                refreshAll(vehRegTxt.getText(), Integer.parseInt(bookedIDTxt.getText()));
            } catch (Exception e) {
                e.printStackTrace();
                switch (e.getClass().getSimpleName()) {
                    case "NullPointerException":
                        if (bookPartList.getSelectionModel().getSelectedItem() == null) {
                              alert.setContentText("Please search and select Customer and select which part you would like to edit from the table");
                              alert.showAndWait();
                        }
                        if (bookedTimeDateDropDown.getValue() == null) {
                             alert.setContentText( "Booking time has not been selected");
                             alert.showAndWait();
                        }
                    default:
                }
            }
        }
    }

    @FXML
    private void editPart(ActionEvent event) {
        try {
            deletePart(null);
            addPart(null);
        } catch (Exception e) {
            switch (e.getClass().getSimpleName()) {

                case "NullPointerException":

                    if (bookPartList.getSelectionModel().getSelectedItem() == null) {
                       alert.setContentText( "Please search and select Customer and select which part you would like to edit from the table");
                       alert.showAndWait();
                    }
                    if (bookedTimeDateDropDown.getValue() == null) {
                         alert.setContentText( "Booking time has not been selected");
                         alert.showAndWait();
                    }
                case "NumberFormatException":
                     alert.setContentText( "Please Select part from drop down menu before clicking Edit");
                     alert.showAndWait();
                default:
                    break;

            }

        }

    }

    @FXML
    private void selectPart(ActionEvent event) {

        connection = sqlConnect.returnConnection();
        String selected = partListDropDown.getValue();
        ResultSet rs;
        try {
            String sql = "SELECT * FROM PartStock WHERE PartName='" + selected + "'";
            Statement st = connection.createStatement();
            rs = st.executeQuery(sql);
            partNmTxt.setText(rs.getString("PartName"));
            costTxt.setText(Integer.toString(rs.getInt("Price")));
            descTxt.setText(rs.getString("Description"));
            connection.close();
        } catch (SQLException e) {

        }
    }

    @FXML
    private void fillCustomerData(ActionEvent event) {
        bookedTimeDateDropDown.getItems().clear();
        vehicleRegDropDown.getItems().clear();
        connection = sqlConnect.returnConnection();
        String[] list = foundNamesList.getValue().split("-");
        String Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.CusID=Vehicles.CustomerID WHERE Customer.FirstName = '" + list[0] + "'";
        ResultSet rs;
        String vReg = null;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);

            customerIdTxt.setText(Integer.toString(rs.getInt(1)));
            firstNameTxt.setText(rs.getString("FirstName"));
            lastNameTxt.setText(rs.getString("LastName"));
            while (rs.next()) {
                vehicleRegDropDown.getItems().add(rs.getString("VehReg"));
            }
            connection.close();
        } catch (SQLException ex) {

           alert.setContentText( "No Vehicles Found");
           alert.showAndWait();
            // 
        }
    }

    @FXML
    private void fillVehicleData(ActionEvent event) {
        connection = sqlConnect.returnConnection();
        String Sqlite = "SELECT * FROM Vehicles WHERE Vehicles.VehReg = '" + vehicleRegDropDown.getValue() + "'";
        ResultSet rs;
        String vReg = "";
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            VehicleTxt.setText(rs.getString("VehModel") + " " + rs.getString("VehMake"));
            vehicleTypeTxt.setText(rs.getString("VehType"));
            vehRegTxt.setText(rs.getString("VehReg"));
            bookingItemDropDownBox(rs.getString("VehReg"));
        } catch (Exception ex) {
           alert.setContentText( "No Booking Slots Found");
           alert.showAndWait();
        }

    }

    @FXML
    private void fillBookingData(ActionEvent event) {

       
        try {
             String[] array = bookedTimeDateDropDown.getValue().split("---");
        String Sqlite = "SELECT * FROM Bookings WHERE BookingTime = '" + array[0] + "' AND BookingDate = '" + array[1] + "'";

            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            bookedTimeTxt.setText(rs.getString("BookingTime"));
            bookedIDTxt.setText(Integer.toString(rs.getInt("BookingID")));
            SimpleDateFormat sdfr = new SimpleDateFormat("dd/MMM/yyyy");
            bookedDateTxt.setText(rs.getString("BookingDate"));
            refreshAll(rs.getString("VehicReg"), rs.getInt("BookingID"));
            connection.close();
            String billSql = "SELECT Bill FROM CustomerAccount WHERE BookingID='" + bookedIDTxt.getText() + "'";
            connection = sqlConnect.returnConnection();
            st = connection.createStatement();
            rs = st.executeQuery(billSql);
            bookingBillTxt.setText(rs.getString("Bill"));
            connection.close();
            changeBill(bookedIDTxt.getText());
        } catch (Exception e) {
        }
    }

    private void bookingItemDropDownBox(String vehicleReg) throws SQLException {
        bookedTimeDateDropDown.getItems().clear();
        String Sqlite = "SELECT * FROM Bookings INNER JOIN Vehicles ON Vehicles.VehReg=Bookings.VehicReg WHERE Vehicles.VehReg = '" + vehicleReg + "'";
        try {
            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                bookedTimeDateDropDown.getItems().add(rs.getString("BookingTime") + "---" + rs.getString("BookingDate"));
            }
            connection.close();
        } catch (Exception e) {
             alert.setContentText("No Booking Slots Found");
             alert.showAndWait();
        }

    }

    @FXML
    private void filterPart(KeyEvent event) {
        partListDropDown.getItems().clear();
        String Sqlite = null;

        if (searchPartTxt.getText().equals("")) {
            Sqlite = "SELECT * FROM PartStock WHERE Stock >0";

        } else {
            Sqlite = "SELECT * FROM PartStock WHERE Stock >0 AND PartName LIKE '%" + searchPartTxt.getText() + "%'";
        }
        try {
            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                partListDropDown.getItems().add(rs.getString("PartName"));
            }
            connection.close();
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void partDetails(MouseEvent event) {
        usedPartNmTxt.setText("");
        usedCostTxt.setText("");
        usedDescTxt.setText("");
        dateInstalledTxt.setText("");
        warrantyTxt.setText("");
        try {
            connection = sqlConnect.returnConnection();
            Statement st = connection.createStatement();
            usedPartNmTxt.setText(bookPartList.getSelectionModel().getSelectedItem().getPartName());
            ResultSet rs = st.executeQuery("SELECT Description From InstalledParts WHERE PartName='" + usedPartNmTxt.getText() + "'");
            usedCostTxt.setText(String.valueOf(bookPartList.getSelectionModel().getSelectedItem().getCost()));
            usedDescTxt.setText(rs.getString("Description"));
            dateInstalledTxt.setText(bookPartList.getSelectionModel().getSelectedItem().getDateInstalled());
            warrantyTxt.setText(bookPartList.getSelectionModel().getSelectedItem().getDateInstalled());
            connection.close();
        } catch (Exception e) {
            usedPartNmTxt.setText("");
            usedCostTxt.setText("");
            usedDescTxt.setText("");
            dateInstalledTxt.setText("");
            warrantyTxt.setText("");
        }
    }
}
