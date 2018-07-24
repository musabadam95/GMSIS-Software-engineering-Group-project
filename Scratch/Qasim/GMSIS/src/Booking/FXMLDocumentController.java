   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Booking;

import Booking.Booking;
import common.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import static javax.swing.text.html.HTML.Attribute.DECLARE;

/**
 * FXML Controller class
 *
 * @author qhzrv
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    public TextField fName;
    @FXML
    public TextField lName;
    @FXML
    private TextField vehRegBox;
    @FXML
    public TextField vehMakeBox;
    @FXML
    public TextField mileageBox;
    @FXML
    public TextField durBox;
    @FXML
    private ChoiceBox TimeSelectBox;
    @FXML
    private DatePicker myDateSelector;
    @FXML
    private TableView<Booking> BookingsTable;
    @FXML
    private TableColumn colBName;
    @FXML
    private TableColumn colBSurname;
    @FXML
    private TableColumn colBVehReg;
    @FXML
    private TableColumn bidFX;
    @FXML
    private TableColumn colBMake;
    @FXML
    private TableColumn colBDate;
    @FXML
    private TableColumn colBTime;
    @FXML
    private TableColumn colBCusID;
    @FXML
    private TableColumn colBDuration;
    @FXML
    private TableColumn colBType;
    @FXML
    private TableColumn mechanicFX;
    @FXML
    private Button deleteButton;
    @FXML
    private Button searchButton;
    ConnectionDB sqlConnect;
    @FXML
    private TextField FirstNameBox;
    @FXML
    private ToggleGroup Hello;
    @FXML
    private TextField searchTxt;
    @FXML
    private ChoiceBox searchType;
    @FXML
    private Button resetButton;
    @FXML
    private Button editButton;
    @FXML
    private Button saveButton;
    @FXML
    private RadioButton DiaRep;
    @FXML
    private RadioButton SchMen;
    @FXML
    private Button addButton;
    

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        sqlConnect = new ConnectionDB();
        searchType.getItems().add("name");
        searchType.getItems().add("VehReg");
        searchType.getItems().add("VehMake");
        saveButton.setDisable(true);
        
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleAddButton();
            }
        });
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleSaveButton();
            }
        });
        
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                handleDeleteButton();    
            }
            });
        
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) 
            {
                handleEditButton();
            }
                
        });
        
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               setTableData();
               clearTable();
               saveButton.setDisable(true);
            }
        });

        
        setDatePicker();
        setTableData();

    }

    public void setTableData() {
        Connection connection = sqlConnect.returnConnection();

        ObservableList<Booking> data = FXCollections.<Booking>observableArrayList();
        ResultSet rs;
        String Sqlite = "SELECT * FROM Bookings,Customer,Vehicles,Mechanics WHERE Bookings.MechID = Mechanics.MechanicID AND Bookings.VehicReg = Vehicles.VehReg AND Vehicles.CustomerID = Customer.CusID";
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {

                data.add(new Booking(rs.getInt("BookingID"), rs.getInt("Duration"), rs.getString("CustID"), rs.getString("BookingTime"), rs.getString("BookingType"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("BookingDate"), rs.getString("VehicReg"), rs.getString("VehMake"), rs.getInt("CurrentMileage"), rs.getInt("MechID")));

            }
            colBVehReg.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehReg"));
            colBMake.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehMake"));
            colBDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingDate"));
            colBName.setCellValueFactory(new PropertyValueFactory<Booking, String>("FirstName"));
            colBSurname.setCellValueFactory(new PropertyValueFactory<Booking, String>("LastName"));
            colBDuration.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("Duration"));
            colBTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingTime"));
            colBCusID.setCellValueFactory(new PropertyValueFactory<Booking, String>("CusID"));
            colBType.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingType"));
            mechanicFX.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("MechanicID"));
            bidFX.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("BookingID"));
            BookingsTable.setItems(data);
            st.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
         

    }
    
    public void handleDeleteButton()
    {
        Connection connection = sqlConnect.returnConnection();
                String answer = JOptionPane.showInputDialog("Would you like to delete this entry?(Y/N) ");
                if (answer.equals("Y") || answer.equals("y")) {
                    Booking selectedItem = BookingsTable.getSelectionModel().getSelectedItem();
                    int thisID = selectedItem.getBookingID();
                    BookingsTable.getItems().remove(selectedItem);
                    try {
                        PreparedStatement st1 = connection.prepareStatement("DELETE FROM Bookings WHERE Bookings.BookingID = ?");
                        st1.setInt(1, thisID);
                        st1.executeUpdate();
                        st1.close();
                        connection.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                } else {
                    return;
                }
        
    }
    
    public void handleEditButton()
    {
        Booking selectedItem = BookingsTable.getSelectionModel().getSelectedItem();
                saveButton.setDisable(false);
                String startDateString = selectedItem.getBookingDate();
                LocalDate date = LocalDate.parse(startDateString);
                myDateSelector.setValue(date);
                fName.setText(selectedItem.getFirstName());
                fName.setEditable(false);
                lName.setText(selectedItem.getLastName());
                lName.setEditable(false);
                vehRegBox.setText(selectedItem.getVehReg());
                vehRegBox.setEditable(false);
                vehMakeBox.setText(selectedItem.getVehMake());
                vehMakeBox.setEditable(false);
                TimeSelectBox.setValue(selectedItem.getBookingTime());
                if(selectedItem.getBookingType().equals("DiaRep"))
                    {
                        DiaRep.setSelected(true);
                    }
                else if(selectedItem.getBookingType().equals("SchMen"))
                {
                    SchMen.setSelected(true);
                }
                DiaRep.setDisable(true);
                SchMen.setDisable(true); 
                mileageBox.setText(Integer.toString(selectedItem.getMileage()));
                mileageBox.setEditable(true);
                durBox.setText(Integer.toString(selectedItem.getDuration()));
               
            }
    
    public void  handleAddButton()
    {
        
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
        } catch(Exception e) {
           e.printStackTrace();
          }
        
        
    }
    
    public void clearTable()
    {
                saveButton.setDisable(true);
                fName.setText(null);
                fName.setEditable(false);
                lName.setText(null);
                lName.setEditable(false);
                vehRegBox.setText(null);
                vehRegBox.setEditable(false);
                vehMakeBox.setText(null);
                vehMakeBox.setEditable(false);
                TimeSelectBox.setValue("Select time");
                DiaRep.setDisable(true);
                SchMen.setDisable(true); 
                mileageBox.setText(null);
                mileageBox.setEditable(false);
                durBox.setText(null);
        
    }
        
    
    
    public void handleSaveButton()
    {
        
        Booking selectedItem = BookingsTable.getSelectionModel().getSelectedItem();
        String newMile = (mileageBox.getText());
        int newDur = Integer.parseInt(durBox.getText());
        String newVeh = selectedItem.getVehReg();
        int bid = selectedItem.getBookingID();
        Date date1 = Date.from(myDateSelector.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int dayOfWeek = date1.getDay();
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date1);
        if(dayOfWeek==6 && (TimeSelectBox.getValue().equals("17:30")  || TimeSelectBox.getValue().equals("17:15")  || TimeSelectBox.getValue().equals("17:00")  || TimeSelectBox.getValue().equals("16:45")  || TimeSelectBox.getValue().equals("16:30")  || TimeSelectBox.getValue().equals("16:15")  || TimeSelectBox.getValue().equals("16:00")  || TimeSelectBox.getValue().equals("15:45")  || TimeSelectBox.getValue().equals("15:30")  || TimeSelectBox.getValue().equals("15:15")  || TimeSelectBox.getValue().equals("15:00")  || TimeSelectBox.getValue().equals("14:45")  || TimeSelectBox.getValue().equals("14:30")  ||  TimeSelectBox.getValue().equals("14:15")  || TimeSelectBox.getValue().equals("14:00")  || TimeSelectBox.getValue().equals("13:45")  || TimeSelectBox.getValue().equals("13:30")  || TimeSelectBox.getValue().equals("13:15")  || TimeSelectBox.getValue().equals("13:00")  || TimeSelectBox.getValue().equals("12:45")  || TimeSelectBox.getValue().equals("12:30")  || TimeSelectBox.getValue().equals("12:15") ))
        {
            JOptionPane.showMessageDialog(null,"The garage closes at 12:00 on Saturday. Please select a time between 9-12.");
        }
        else{
        String sqlite1 = "UPDATE Bookings SET  BookingDate = '" + s + "' WHERE BookingID = '" + bid + "'";
        try {
                    Connection connection = sqlConnect.returnConnection();
                    Statement st2 = connection.createStatement();
                    st2.executeUpdate(sqlite1);
                    connection.close();
                    
                } 
       
                   catch (SQLException Ex) {
                    Ex.printStackTrace();
                    throw new RuntimeException("Database connection failed!", Ex);
                }
        String sqlite2 = "UPDATE Bookings SET  Duration = '" + newDur + "' WHERE BookingID = '" + bid + "'";
        try {   
                    Connection connection = sqlConnect.returnConnection();
                    Statement st2 = connection.createStatement();
                    st2.executeUpdate(sqlite2);
                    connection.close();
                    
                } 
       
                   catch (SQLException Ex) {
                    Ex.printStackTrace();
                    throw new RuntimeException("Database connection failed!", Ex);
                }
        String sqlite3 = "UPDATE Vehicles SET  CurrentMileage = '" + newMile + "' WHERE VehReg = '" + newVeh + "'";
        Connection connection2 = sqlConnect.returnConnection();
         try {

                    Statement st3 = connection2.createStatement();
                    st3.executeUpdate(sqlite3);
                    connection2.close();
                    
                } 
       
                   catch (SQLException Ex) {
                    Ex.printStackTrace();
                    throw new RuntimeException("Database connection failed!", Ex);
                }
        
        setTableData();
        fName.setText(null);
        fName.setEditable(false);
        lName.setText(null);
        lName.setEditable(false);
        vehRegBox.setText(null);
        vehRegBox.setEditable(false);
        vehMakeBox.setText(null);
        vehMakeBox.setEditable(false);
        mileageBox.setEditable(false);
        TimeSelectBox.setValue(null);
        DiaRep.setSelected(false);
        SchMen.setSelected(false);   
        mileageBox.setText(null);
        durBox.setText(null);
        saveButton.setDisable(true);
        }
        
    }
    
    public void setDatePicker()
    {
        ObservableList<String> timesList = FXCollections.observableArrayList("Select time", "09:00", "09:15", "09;30", "09:45", "10:00", "10:15", "10:30", "10:45", "11:00", "11:15", "11:30", "11:45", "12:00", "12:15", "12:30", "12:45", "13:00", "13:15", "13:30", "13:45", "14:00", "14:15", "14:30", "14:45", "15:00", "15:15", "15:30", "15:45", "16:00", "16:15", "16:30", "16:45", "17:00", "17:15");
        Locale.setDefault(Locale.UK);
        final Callback<DatePicker, DateCell> dayCellFactory;
        dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override

                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item.isBefore(myDateSelector.getValue().plusDays(1)) || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #FF0000");
                        }

                    }
                };
            }
        };

        myDateSelector.setDayCellFactory(dayCellFactory);
        myDateSelector.setValue(LocalDate.now());
        TimeSelectBox.setValue("Select time");
        TimeSelectBox.setItems(timesList);
        
    }
    
 
    
    

    @FXML
    private void updateTable(ActionEvent event) {
        Connection connection = sqlConnect.returnConnection();

        String Sqlite = "";

        ObservableList<Booking> data = FXCollections.<Booking>observableArrayList();

        if (searchType.getValue().equals("name")) {
            Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.CusID=Vehicles.CustomerID INNER JOIN Bookings ON Customer.CusID=Bookings.CustID  WHERE Customer.FirstName LIKE '%" + searchTxt.getText() + "%'OR LastName LIKE '%" + searchTxt.getText() + "%'";
        } else {

            Sqlite = "SELECT * FROM Vehicles INNER JOIN Customer ON Vehicles.CustomerID=Customer.CusID  INNER JOIN Bookings ON Customer.CusID=Bookings.CustID WHERE " + searchType.getValue() + " LIKE '%" + searchTxt.getText() + "%'";
        }
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                data.add(new Booking(rs.getInt("BookingID"), rs.getInt("Duration"), rs.getString("CustomerID"), rs.getString("BookingTime"), rs.getString("BookingType"), rs.getString("FirstName"), rs.getString("LastName"), rs.getString("BookingDate"), rs.getString("VehReg"), rs.getString("VehMake"), rs.getInt("CurrentMileage"), rs.getInt("MechID")));
            }
            colBVehReg.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehReg"));
            colBMake.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehMake"));
            colBDate.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingDate"));
            colBName.setCellValueFactory(new PropertyValueFactory<Booking, String>("FirstName"));
            colBSurname.setCellValueFactory(new PropertyValueFactory<Booking, String>("LastName"));
            colBDuration.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("Duration"));
            colBTime.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingTime"));
            colBCusID.setCellValueFactory(new PropertyValueFactory<Booking, String>("CusID"));
            colBType.setCellValueFactory(new PropertyValueFactory<Booking, String>("BookingType"));
            bidFX.setCellValueFactory(new PropertyValueFactory<Booking, Integer>("BookingID"));
            BookingsTable.setItems(data);
            connection.close();
        } catch (SQLException Ex) {
            Ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", Ex);
        }

    }

}
