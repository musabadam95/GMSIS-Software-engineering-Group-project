/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Booking;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import common.ConnectionDB;
import java.net.URL;
import java.util.Random;
import java.sql.Connection;
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
import static java.time.temporal.TemporalQueries.localDate;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author qhzrv
 */
public class AddController implements Initializable {

    @FXML
    public TextField fName;
    @FXML
    public TextField sName;
    @FXML
    private TextField vReg;
    @FXML
    public TextField vMake;
    @FXML
    public TextField searchBox;
    @FXML
    private ChoiceBox bTime;
    @FXML
    private DatePicker bDate;
    @FXML
    private TableColumn cName;
    @FXML
    private TableColumn cSName;
    @FXML
    private TableColumn cVReg;
    @FXML
    private TableColumn cVMake;
    @FXML
    private TableColumn cID;
    @FXML
    private TableView<Booking> cTable;
    @FXML
    private Button selectButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button searchButton;
    @FXML
    private Button resetButton;
    @FXML
    private ToggleGroup go;
    @FXML
    private ToggleButton DiaRep;
    @FXML
    private ToggleButton SchMen;
    ConnectionDB sqlConnect;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        sqlConnect = new ConnectionDB();
        ToggleButton tb1 = new ToggleButton("DiaRep");
        ToggleButton tb2 = new ToggleButton("SchMen");
        tb1.setToggleGroup(go);
        tb2.setToggleGroup(go);
        setDatePicker();
        setTableData();
        saveButton.setDisable(true);
        
        selectButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleSelectButton();
               saveButton.setDisable(false);
            }
        });
        
        resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               setTableData();
            }
        });
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleSaveButton();
            }
        });
        
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               handleSearchButton();
            }
        });
        
       
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

                        if (item.isBefore(bDate.getValue().plusDays(1)) || item.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #FF0000");
                        }

                    }
                };
            }
        };

        bDate.setDayCellFactory(dayCellFactory);
        bDate.setValue(LocalDate.now());
        bTime.setValue("Select time");
        bTime.setItems(timesList);
        
    }
    
    public void  handleSelectButton()
    {
                Booking selectedItem = cTable.getSelectionModel().getSelectedItem();
                saveButton.setDisable(false);
                fName.setText(selectedItem.getFirstName());
                fName.setEditable(false);
                sName.setText(selectedItem.getLastName());
                sName.setEditable(false);
                vReg.setText(selectedItem.getVehReg());
                vReg.setEditable(false);
                vMake.setText(selectedItem.getVehMake());
                vMake.setEditable(false);
                
         
    }
    @FXML
    private void handleSearchButton() {
        Connection connection = sqlConnect.returnConnection();

        String Sqlite = "";

        ObservableList<Booking> data = FXCollections.<Booking>observableArrayList();

            Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.CusID=Vehicles.CustomerID WHERE Customer.FirstName LIKE '%" + searchBox.getText() + "%'OR LastName LIKE '%" + searchBox.getText() + "%'OR VehMake LIKE '%" + searchBox.getText() + "%'OR VehReg LIKE '%" + searchBox.getText() + "%'";
        
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                data.add(new Booking(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("VehReg"), rs.getString("VehMake"), rs.getString("CustomerID")));
            }
            cName.setCellValueFactory(new PropertyValueFactory<Booking, String>("FirstName"));
            cSName.setCellValueFactory(new PropertyValueFactory<Booking, String>("LastName"));
            cVReg.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehReg"));
            cVMake.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehMake"));
            cID.setCellValueFactory(new PropertyValueFactory<Booking, String>("CusID"));
            cTable.setItems(data);
            st.close();
            connection.close();
        } catch (SQLException Ex) {
            throw new RuntimeException("Database connection failed!", Ex);
        }

    }
    
    
    public void  handleSaveButton()
    {
        Booking selectedItem = cTable.getSelectionModel().getSelectedItem();
        Random rand = new Random();
        int  n = rand.nextInt(1000) + 1;
        Date date1 = Date.from(bDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        System.out.println(date1.getDay());
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String s = formatter.format(date1);
        int dayOfWeek = date1.getDay();
        String mR = vReg.getText();
        String BookingType="";
        if(go.getSelectedToggle()==DiaRep)
        {
            BookingType="DiaRep";
        }
        else if(go.getSelectedToggle()==SchMen)
        {
            BookingType="SchMen";
        }
        String output = bTime.getSelectionModel().getSelectedItem().toString();
       
        if(dayOfWeek==6 && (bTime.getValue().equals("17:30")  || bTime.getValue().equals("17:15")  || bTime.getValue().equals("17:00")  || bTime.getValue().equals("16:45")  || bTime.getValue().equals("16:30")  || bTime.getValue().equals("16:15")  || bTime.getValue().equals("16:00")  || bTime.getValue().equals("15:45")  || bTime.getValue().equals("15:30")  || bTime.getValue().equals("15:15")  || bTime.getValue().equals("15:00")  || bTime.getValue().equals("14:45")  || bTime.getValue().equals("14:30")  ||  bTime.getValue().equals("14:15")  || bTime.getValue().equals("14:00")  ||bTime.getValue().equals("13:45")  ||bTime.getValue().equals("13:30")  || bTime.getValue().equals("13:15")  ||bTime.getValue().equals("13:00")  ||bTime.getValue().equals("12:45")  ||bTime.getValue().equals("12:30")  ||bTime.getValue().equals("12:15") ))
        {
            JOptionPane.showMessageDialog(null,"The garage closes at 12:00 on Saturday. Please select a time between 9-12.");
        }
        
        else if(go.getSelectedToggle()==null)
        {
            JOptionPane.showMessageDialog(null,"Please select a booking type!");
        }
        
        else if(bTime.getValue().equals("Select time"))
        {
            JOptionPane.showMessageDialog(null, "Please select a time!");
        }
        else
        {
            try
            {
            String myQuery = "INSERT INTO Bookings (BookingID,  VehicReg, BookingTime, BookingDate, BookingType, CustID, MechID) VALUES (?,?,?,?,?,?,?)";
            Connection connection = sqlConnect.returnConnection();
            PreparedStatement st = connection.prepareStatement(myQuery);
            st.setInt(1,n);
            st.setString(2, vReg.getText());
            st.setString(3, output);
            st.setString(4, s);
            st.setString(5, BookingType);
            st.setString(6, selectedItem.getCusID());
            st.setInt(7,123);
            st.executeUpdate();
            connection.close();
            JOptionPane.showMessageDialog(null,"Booking sucessfully created!");
            }
            
            catch (SQLException Ex) {
            throw new RuntimeException("Database connection failed!", Ex);
        }

      
        }
        
         
    }
    
    public void setTableData() {
        
        Connection connection = sqlConnect.returnConnection();

        ObservableList<Booking> data = FXCollections.<Booking>observableArrayList();
        ResultSet rs;
        String Sqlite = "SELECT * FROM Customer,Vehicles WHERE Vehicles.CustomerID = Customer.CusID";
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {

                data.add(new Booking(rs.getString("FirstName"), rs.getString("LastName"), rs.getString("VehReg"), rs.getString("VehMake"), rs.getString("CustomerID")));

            }
            cName.setCellValueFactory(new PropertyValueFactory<Booking, String>("FirstName"));
            cSName.setCellValueFactory(new PropertyValueFactory<Booking, String>("LastName"));
            cVReg.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehReg"));
            cVMake.setCellValueFactory(new PropertyValueFactory<Booking, String>("VehMake"));
            cID.setCellValueFactory(new PropertyValueFactory<Booking, String>("CusID"));
            cTable.setItems(data);
            st.close();
            connection.close();
        } catch (SQLException ex) {

            throw new RuntimeException("Database connection failed!", ex);
        }
         
    }
    
   
    
}
