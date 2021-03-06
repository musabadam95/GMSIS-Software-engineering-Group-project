/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

// import common.ConnectionDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import JavafxGUI.SRCinfo;
import java.util.function.Predicate;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author louiraj
 */
public class SRCController implements Initializable {

    private ObservableList<SRCinfo> srcdb = FXCollections.observableArrayList();

    //  ConnectionDB sqlConnect;
    @FXML
    private Button delete_bt;
    @FXML
    private Button edit_bt;
    @FXML
    private Button print_bt;
    @FXML
    private Button bill_bt;
    @FXML
    private Button search_bt;
    @FXML
    private Button add_parts_bt;
    @FXML
    private Button add_vehicle_bt;
    @FXML
    private Button add_bt;
    @FXML
    private TableView<Parts> parts_table;
    @FXML
    private TableView<Vehicle> vehicle_table;
    @FXML
    private TableView<SRCinfo> src_table;
    @FXML
    private TableColumn<SRCinfo, Integer> src_table_id;
    @FXML
    private TableColumn<SRCinfo, String> src_table_name;
    @FXML
    private TableColumn<SRCinfo, String> src_table_1add;
    @FXML
    private TableColumn<SRCinfo, String> src_table_2add;
    @FXML
    private TableColumn<SRCinfo, String> src_table_county;
    @FXML
    private TableColumn<SRCinfo, String> src_table_pcode;
    @FXML
    private TableColumn<SRCinfo, Integer> src_table_pnumber;
    @FXML
    private TableColumn<SRCinfo, String> src_table_emailadd;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_reg;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_type;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_warranty;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_model;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_make;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_engszie;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_fueltype;
    @FXML
    private TableColumn<Vehicle, String> vehicle_table_col;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_rdate;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_lsdate;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_mile;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_dstsrc;
    @FXML
    private TableColumn<Vehicle, Integer> vehicle_table_drfsrc;
    @FXML
    private TableColumn<Parts, Integer> parts_table_id;
    @FXML
    private TableColumn<Parts, String> parts_table_name;
    @FXML
    private TableColumn<Parts, String> parts_table_des;
    @FXML
    private TableColumn<Parts, Integer> parts_table_cost;

    private ObservableList<Parts> partsdata;

    db db;
    ResultSet rs = null;
    private final int QUERY_TIMEOUT = 5;

    private static Connection con;
    private static Statement stat;
    @FXML
    private TableColumn<Parts, String> parts_table_vehreg;
    @FXML
    private TableColumn<Parts, Integer> parts_table_dstosrc;
    @FXML
    private TableColumn<Parts, Integer> parts_table_drfsrc;

    SRCinfo srcinfo;
    @FXML
    private TextField search_txt;
    private ChoiceBox src_choicebox;
    @FXML
    private Button refresh_bt;
    @FXML
    private TableColumn<Parts, String> parts_table_srcname;
    @FXML
    private ComboBox src_combobox;

    /**
     *
     */
    public SRCController() {
        this.db = db.getInstance();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //              sqlConnect = new ConnectionDB();

        loadSRCName();
        src_table();
        parts_table();
        //    src_choicebox.setOnAction(new src_choicebox_class());
        delete_bt.setOnAction(new delete_class());
       search_txt.setOnAction(new search_class());

        edit_bt.setOnAction((ActionEvent e) -> {

            if (!src_edit_pop(src_table.getSelectionModel().getSelectedItem(), true)) {
                System.out.println("Nothing is Edited");
            } else {
                src_table();
            }
        });

        add_bt.setOnAction((ActionEvent e) -> {
            if (!src_edit_pop(null, false)) {
                System.out.println("No user added");
            } else {
                src_table();
            }
        });

        /*  search_txt.textProperty().addListener((observable, oldValue, newValue) -> {


            FilteredList<SRCinfo> filteredsrc = new FilteredList<>(srcdb, p -> true);
            filteredsrc.setPredicate(src -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCase = newValue.toLowerCase();
                if (src.getName().toLowerCase().contains(lowerCase)) {
                    return true;
                }

                return false;
            });
                SortedList<SRCinfo> sortedData = new SortedList<>(filteredsrc);
                sortedData.comparatorProperty().bind(src_table.comparatorProperty());
                src_table.setItems(sortedData);


       });

        /*
        delete_bt.setOnAction((ActionEvent e) -> {
          try{
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
          } catch (SQLException ex){
            ex.printStackTrace();
          }  try {
             String sql = "DELETE FROM SRC WHERE Name=?";
             PreparedStatement prStatement = con.prepareStatement(sql);
             prStatement.setString(1, src_table.getSelectionModel().getSelectedItem().getName());
             prStatement.execute();
             con.close();
         } catch (SQLException ex) {
             ex.printStackTrace();

         }
        });

    //delete_bt.setOnAction(new EventHandlerImpl());
/*
        delete_bt.setOnAction((ActionEvent e) -> {
            try {

                String driverName = "com.jnetdirect.jsql.JSQLDriver";
               Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:data.db");


                stat = con.createStatement();
                //stat.execute(sql);

               rs = stat.executeQuery("SELECT \"ID\" FROM SRC;");
                int selectedIndex = src_table.getSelectionModel().getSelectedIndex();
                if (selectedIndex >= 0) {
                    src_table.getItems().remove(selectedIndex);
                   // stat.execute(sql);
                    delete_src(selectedIndex);

                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("No Selection");
                    alert.setHeaderText("You haven't Selected any element");
                    alert.setContentText("Please select an element in the table");
                    alert.showAndWait();
                }


            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
              try {
                con.close();
                stat.close();

              } catch (Exception ex){

              }
            }
        });


 /*        delete_bt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    int selectedIndex = src_table.getSelectionModel().getSelectedIndex();
                    if (selectedIndex >= 0) {
                        src_table.getItems().remove(selectedIndex);
                        theQuery("delete from SRC where id" = selectedIndex.getText());
                    } else {
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("No Selection");
                        alert.setHeaderText("You haven't Selected any element");
                        alert.setContentText("Please select an element in the table");
                        alert.showAndWait();
                    }

                } catch (Exception ex) {

                }
            }

        });*/
    }

    /*
private void delet_bt(ActionEvent event) {
     con = sqlConnect.returnConnection();
            try {
        String sqlQuery = "DELETE FROM SRC WHERE Name=?";
        PreparedStatement prStatement = con.prepareStatement(sqlQuery);
        prStatement.setString(1, src_table.getSelectionModel().getSelectedItem().getName());
        prStatement.execute();
        con.close();
    } catch (SQLException ex) {
        ex.printStackTrace();

    }
}
     */
    @FXML
    private void print_bt(ActionEvent event) {
    }

    public void loadSRCName() {

        String sql = "SELECT Name FROM SRC";

        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            boolean comboBoxEmpty = src_combobox.getSelectionModel().isEmpty();
            if (comboBoxEmpty == true) {
                src_combobox.getItems().addAll("All");
                while (rs.next()) {
                    src_combobox.getItems().addAll(rs.getString("Name"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error");
        }
    }

    /*
    private void updateTable(ActionEvent event) {
        Connection connection = sqlConnect.returnConnection();

        String Sqlite = "";

        ObservableList<Booking> data = FXCollections.<Booking>observableArrayList();

        if (searchType.getValue().equals("name")) {
            Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.CusID=Vehicles.CusIDs INNER JOIN Bookings ON Customer.CusID=Bookings.CustomerID  WHERE Customer.FirstName LIKE '%" + searchTxt.getText() + "%'OR LastName LIKE '%" + searchTxt.getText() + "%'";
        } else {

            Sqlite = "SELECT * FROM Vehicles INNER JOIN Customer ON Vehicles.CusIDs=Customer.CusID  INNER JOIN Bookings ON Customer.CusID=Bookings.CustomerID WHERE " + searchType.getValue() + " LIKE '%" + searchTxt.getText() + "%'";
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

/*
    public void loadSRCName () {

    String sql = "SELECT Name FROM SRC;";
    try{
    con = DriverManager.getConnection("jdbc:sqlite:data.db");
    stat = con.createStatement();
    rs = stat.executeQuery(sql);
        src_choicebox.getItems().addAll("All");
    while(rs.next()){
    src_choicebox.getItems().addAll(rs.getString("Name"));

   // src_combobox.set
    }
    } catch (SQLException e){
    e.printStackTrace();

    }

    }
     */
    @FXML
    private void bill_bt(ActionEvent event
    ) {
    }

    @FXML
    private void add_parts_bt(ActionEvent event) throws IOException {
        try {
            FXMLLoader partsLoader = new FXMLLoader(getClass().getResource("addParts.fxml"));
            Parent root1 = (Parent) partsLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void add_vehicle_bt(ActionEvent event) {
        try {

            FXMLLoader vehLoader = new FXMLLoader(getClass().getResource("addVeh.fxml"));
            Parent root2 = (Parent) vehLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.show();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    private void add_bt(ActionEvent event) {
        /*  try {

            FXMLLoader srcLoader = new FXMLLoader(getClass().getResource("addSRC.fxml"));
            Parent root2 = (Parent) srcLoader.load();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root2));
            stage1.show();
            stage1.setTitle("Adding");
        } catch (IOException e) {
            System.out.println("Error");
        }*/
    }

    @FXML

    private void edit_bt() {
        //    if(!src_edit_pop(src_table.getSelectionModel().getSelectedItem(), true)){
        //      System.out.println("Nothing is Edited");
//
        //    } else src_table();
    }

    private boolean src_edit_pop(SRCinfo srcinfo, boolean par) {
        try {
            FXMLLoader loads = new FXMLLoader(getClass().getResource("addSRC.fxml"));
            //      Parent root3 = (Parent) srceditloads.load();
            //      Stage stage1 = new Stage();
            //      stage1.setScene(new Scene(root3));
            //      stage1.show();
            Pane page = loads.load();

            //Creating stage
            Stage popStage = new Stage();
            if (par) {
                popStage.setTitle("Editting");
            } else {
                popStage.setTitle("Adding");
            }

            popStage.initModality(Modality.WINDOW_MODAL);
            //popStage.initOwner(Put Primary Window (Make it static));
            Scene scene = new Scene(page);
            popStage.setScene(scene);

            AddSRCController controller = loads.getController();
            controller.setStage(popStage);
            if (par) {
                controller.setSRC(srcinfo);
                controller.src_save_edit1().setVisible(true);
                controller.src_save_edit1().requestFocus();
                controller.src_save1().setFocusTraversable(false);

            } else {
                controller.src_save1().setVisible(true);
                controller.src_save_edit1().setFocusTraversable(false);
                controller.src_save1().requestFocus();
            }

            popStage.showAndWait();
            return controller.isDone();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void vehicle_table() {
        /*  partsdata = FXCollections.observableArrayList();
       try{

           String sql = "SELECT * FROM InstalledParts";
           rs = db.query(sql);
           con = DriverManager.getConnection("jdbc:sqlite:data.db");
           stat = con.createStatement();
           while(rs.next()){
             partsdata.add(new Parts(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getInt("SRCCost"),
                      rs.getBoolean("SRC"), rs.getString("VehReg"), rs.getDate("ExpSend"), rs.getDate("ExpReturn")));
         parts_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PartsID"));
         parts_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("PartName"));
         parts_table_des.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("Description"));
         parts_table_cost.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("SRCCost"));
         parts_table_vehreg.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("VehReg"));


       }}catch(SQLException e){
           e.printStackTrace();
           System.err.println("Error");

       }
       parts_table.setItems(partsdata);*/
    }

    private void parts_table() {
        partsdata = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM InstalledParts WHERE SRC IS NOT NULL";
            rs = db.query(sql);
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                partsdata.add(new Parts(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getInt("SRCCost"),
                        rs.getString("SRC"), rs.getString("VehReg"), rs.getString("ExpSend"), rs.getString("ExpReturn")));
                parts_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PartsID"));
                parts_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("PartName"));
                parts_table_des.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("Description"));
                parts_table_cost.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("SRCCost"));
                parts_table_srcname.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("SRC"));
                parts_table_vehreg.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Parts, String>, ObservableValue<String>>) new PropertyValueFactory("VehReg"));
                parts_table_dstosrc.setCellValueFactory(new PropertyValueFactory("ExpSend"));
                parts_table_drfsrc.setCellValueFactory(new PropertyValueFactory("ExpReturn"));

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error");
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                con.close();
                stat.close();

            } catch (Exception ex) {

            }
        }
        parts_table.setItems(partsdata);
    }

    @FXML
    void src_table() {
        srcdb = FXCollections.observableArrayList();
        try {

            String sql = "SELECT * FROM SRC";
            try {
                rs = db.query(sql);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
            }
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                srcdb.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));

                src_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("ID"));
                src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
                src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
                src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
                src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
                src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
                src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
                src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error");

        } finally {
            try {
                con.close();
                stat.close();
            } catch (Exception e) {

            }
        }
        src_table.setItems(srcdb);
    }

    /*
    private void search() throws ClassNotFoundException{
        ObservableList<SRCinfo> srcdb=FXCollections.<SRCinfo>observableArrayList();

       String searched=search_txt.getText();
       System.out.println(searched);
       String sql="SELECT * FROM SRC WHERE  Name LIKE '%"+searched+"%' OR ID LIKE '%"+searched+"%'";
        ResultSet rs;
        try {
          rs = db.query(sql);
            //conn = sqlconnect.returnConnection();
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            //System.out.println(rs.isBeforeFirst());

            while (rs.next()) {
                srcdb.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));
                        srcdb = FXCollections.observableList(srcdb);

                src_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("ID"));
                src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
                src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
                src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
                src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
                src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
                src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
                src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));
}


        } catch (SQLException e) {
          e.printStackTrace();
          System.err.println("Error");
        } finally {
            try {
                con.close();
                stat.close();
                src_table.setItems(srcdb);

            } catch (Exception e) {

            }
    }

    }



 /*   private void delete_src(int id) throws SQLException {

        PreparedStatement pstat;
        pstat = query("DELETE FROM 'SRC' WHERE ID = ?");
        pstat.setString(1, "ID");
        pstat.executeUpdate();

    }

    private PreparedStatement query(String sql) throws SQLException {
        PreparedStatement pstat;
        pstat = con.prepareStatement(sql);
        pstat.setQueryTimeout(QUERY_TIMEOUT);
        return pstat;
    }

    /*
    private void insertStatement(String insert_query) {

        Connection con = null;
        Statement stat = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            con.setAutoCommit(false);
            stat = con.createStatement();
            stat.executeUpdate(insert_query);
            stat.close();
            con.commit();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }
     */
 /*
   public void theQuery(String query) {
        Connection con = null;
        Statement st = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql:data.db");
            st = con.createStatement();
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Query Executed");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
     */
    @FXML
    private void refresh_bt(ActionEvent event) {
        src_table();
        parts_table();
    }

    /*
    private class src_choicebox_class implements EventHandler<ActionEvent>{
      public src_choicebox_class(){

      }
      public void handle(ActionEvent event){

        String sql = "SELECT Name FROM SRC;";
        try{
        con = DriverManager.getConnection("jdbc:sqlite:data.db");
        stat = con.createStatement();
        rs = stat.executeQuery(sql);
            src_choicebox.getItems().addAll("All");
        while(rs.next()){
        src_choicebox.getItems().addAll(rs.getString("Name"));

       // src_combobox.set
        }
        } catch (SQLException e){
        e.printStackTrace();

        }


      }
    }
     */
//    private void src_choicebox(ActionEvent event) {
//       String choicebox =  (src_choicebox.getSelectionModel().getSelectedItem().toString());
//       System.out.println(choicebox);
    //    if(choicebox.equals(choicebox)){
    //      String sql = "SELECT * FROM InstalledParts WHERE " + choicebox + "= ?";
    //      try{
    //        con = DriverManager.getConnection("jdbc:sqlite:data.db");
    //        stat = con.createStatement();
    //        rs = db.query(sql);
//  } catch (Exception e){
    //        e.printStackTrace();
    //    }
    //    }
//    }
    @FXML
    //  private void src_combobox(ActionEvent event) {

    //}
    private void src_combobox(ActionEvent event) {




        String ComboBox = (src_combobox.getSelectionModel().getSelectedItem().toString());

        FilteredList<Parts> filteredparts_1 = new FilteredList<>(partsdata, p -> true);
        filteredparts_1.setPredicate((Parts par_1) -> {
            if (ComboBox.equals(par_1.getSRC())) {
                search_txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                    FilteredList<Parts> filteredparts_2 = new FilteredList<>(partsdata, p -> true);
                    filteredparts_2.setPredicate((Parts par_2) -> {

                        if (par_2.getSRC().equals(ComboBox)) {

                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                            return newValue == null || newValue.isEmpty();

                        } else if (ComboBox.equals("All")) {
                            String lowerCase = newValue.toLowerCase();
                            if (par_2.getPartName().toLowerCase().contains(lowerCase)) {
                                return true;
                            }
                            return newValue == null || newValue.isEmpty();

                        } else {
                            System.out.println("Error");
                            return false;

                        }

                    });
                    SortedList<Parts> sortedData = new SortedList<>(filteredparts_2);
                    sortedData.comparatorProperty().bind(parts_table.comparatorProperty());
                    parts_table.setItems(sortedData);

                });
            } else if (ComboBox.equals("All")) {
                  String lowerCase = "";

              return par_1.getPartName().toLowerCase().contains(lowerCase);
            } else {
                System.out.println("Error");
                return false;
            }
            return true;

        });

        SortedList<Parts> sortedData = new SortedList<>(filteredparts_1);
        sortedData.comparatorProperty().bind(parts_table.comparatorProperty());
        parts_table.setItems(sortedData);





        /*
      String ComboBox = (src_combobox.getSelectionModel().getSelectedItem().toString());
        try {
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stat = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String Sqlite = "";

        ObservableList<Parts> partsdata = FXCollections.<Parts>observableArrayList();

        if (search_txt.getText().equals(ComboBox)) {
            Sqlite = "SELECT * FROM Customer INNER JOIN Vehicles ON Customer.CusID=InstalledParts.PartsID INNER JOIN Bookings ON Customer.CusID=Bookings.CustomerID  WHERE Customer.FirstName LIKE '%" + search_txt.getText() + "%'OR LastName LIKE '%" + search_txt.getText() + "%'";
        } else {

            Sqlite = "SELECT * FROM Vehicles INNER JOIN Customer ON Vehicles.CusIDs=Customer.CusID  INNER JOIN Bookings ON Customer.CusID=Bookings.CustomerID WHERE " + search_txt.getText() + " LIKE '%" + search_txt.getText() + "%'";
        }
        ResultSet rs;
        try {
            stat = con.createStatement();
            rs = stat.executeQuery(Sqlite);
            while (rs.next()) {
                partsdata.add(new Parts(rs.getInt("PartsID"), rs.getString("PartName"), rs.getString("Description"), rs.getInt("SRCCost"), rs.getString("SRC"), rs.getString("VehReg"), rs.getString("ExpSend"), rs.getString("ExpReturn")));
            }
            parts_table_id.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("PartsID"));
            parts_table_name.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartsName"));
            parts_table_des.setCellValueFactory(new PropertyValueFactory<Parts, String>("Description"));
            parts_table_cost.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("SRCCost"));
            parts_table_srcname.setCellValueFactory(new PropertyValueFactory<Parts, String>("SRC"));
            parts_table_vehreg.setCellValueFactory(new PropertyValueFactory<Parts, String>("VehReg"));

            parts_table_dstosrc.setCellValueFactory(new PropertyValueFactory<>("ExpSend"));
            parts_table_drfsrc.setCellValueFactory(new PropertyValueFactory<>("ExpReturn"));
            parts_table.setItems(partsdata);
            con.close();
        } catch (SQLException Ex) {
            Ex.printStackTrace();
          try {
              throw Ex;
          } catch (SQLException ex) {
              Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, ex);
          }
        }

         */
    }
    private class search_class implements EventHandler<ActionEvent> {

        public search_class() {
        }

        @Override
        public void handle(ActionEvent event) {

            search_txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                FilteredList<SRCinfo> filteredsrc = new FilteredList<>(srcdb, p -> true);
                filteredsrc.setPredicate((SRCinfo src) -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (src.getName().toLowerCase().contains(lowerCase)) {
                        return true;
                    }

                    return false;
                });
                SortedList<SRCinfo> sortedData = new SortedList<>(filteredsrc);
                sortedData.comparatorProperty().bind(src_table.comparatorProperty());
                src_table.setItems(sortedData);

            });

            search_txt.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {

                FilteredList<Parts> filteredparts = new FilteredList<>(partsdata, p -> true);
                filteredparts.setPredicate((Parts par) -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCase = newValue.toLowerCase();
                    if (par.getPartName().toLowerCase().contains(lowerCase)) {
                        return true;
                    }

                    return false;
                });
                SortedList<Parts> sortedData = new SortedList<>(filteredparts);
                sortedData.comparatorProperty().bind(parts_table.comparatorProperty());
                parts_table.setItems(sortedData);

            });

        }

    }

    private class delete_class implements EventHandler<ActionEvent> {

        public delete_class() {
        }

        @Override
        public void handle(ActionEvent event) {
            try {
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                String sql = "DELETE FROM SRC WHERE Name=?";
                PreparedStatement pstat = con.prepareStatement(sql);
                pstat.setString(1, src_table.getSelectionModel().getSelectedItem().getName());
                pstat.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();

            } finally {
                try {
                    con.close();
                    src_table();
                } catch (Exception e) {
                }
            }


            /* srcdb = FXCollections.observableArrayList();
            String[] options = null;
            try {
                String driverName = "com.jnetdirect.jsql.JSQLDriver";
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                stat = con.createStatement();
                String sql = "DELETE FROM SRC WHERE \"ID\" =?;";
                stat.execute(sql);

                rs = stat.executeQuery("SELECT \"ID\" FROM SRC;");
                int count = 0;

                int selectedIndex = src_table.getSelectionModel().getSelectedIndex();
                //  if (selectedIndex >= 0) {
                //       src_table.getItems().remove(selectedIndex);

                while (rs.next()) {
                    options[count] = rs.getInt("ID") + "";
                    count++;
                }
                //         } else {
                //    Alert alert = new Alert(AlertType.WARNING);
                //    alert.setTitle("No Selection");
                //    alert.setHeaderText("No Element Selected");
                //    alert.setContentText("Please select an Element in the table.");
                //    alert.showAndWait();
                //   }
                rs.close();
                stat.close();
                con.close();
            } catch (ClassNotFoundException | SQLException ex) {
                System.err.println(event.getClass().getName() + ": " + ex.getMessage());
                System.exit(0);
                ex.printStackTrace();
            }

            String removeid = (String) JOptionPane.showInputDialog(null, "ID",
                    "which SRC you want to remove?", JOptionPane.QUESTION_MESSAGE, null,
                    options, // Array of choices
                    options[0]); // Initial choice

            if (removeid != null) {
                int removeidint = Integer.parseInt(removeid);
                con = null;
                stat = null;
                try {
                    Class.forName("org.sqlite.JDBC");
                    con = DriverManager.getConnection("jdbc:sqlite:data.db");
                    con.setAutoCommit(false);
                    stat = con.createStatement();

                    PreparedStatement statement = con.prepareStatement("DELETE FROM SRC WHERE \"ID\" =?;");
                    statement.setInt(1, removeidint);

                    statement.execute();
                    con.commit();
                    stat.close();
                    con.close();

                } catch (ClassNotFoundException | SQLException ex) {
                    System.err.println(event.getClass().getName() + ": " + ex.getMessage());
                    System.exit(0);
                }
                src_table.setItems(ObservableList <SRCinfo>)load("jdbc:sqlite:data.db");

            }
             */
        }

        //    src_table.getItems().removeAll(
        //       src_table.getSelectionModel().getSelectedItems()
        //);
    }

    /**
     * ************************************************** Deferent Mothed Test
     * *****************************************?
     *
     * /* @Override public void handle(ActionEvent event) { String[] options =
     * null; try { Class.forName("org.sqlite.JDBC"); con =
     * DriverManager.getConnection("jdbc:sqlite:data.db");
     * con.setAutoCommit(false); stat = con.createStatement(); rs =
     * stat.executeQuery("SELECT COUNT(*) AS total FROM SRC;"); while
     * (rs.next()) { options = new String[rs.getInt("total")]; }
     *
     * rs = stat.executeQuery("SELECT \"ID\" FROM SRC;"); int count = 0; while
     * (rs.next()) { options[count] = rs.getInt("ID") + ""; count++; }
     * rs.close(); stat.close(); con.close(); } catch (ClassNotFoundException |
     * SQLException ex) { System.err.println(event.getClass().getName() + ": " +
     * ex.getMessage()); System.exit(0); }
     *
     * String removeid = (String) JOptionPane.showInputDialog(null, "ID", "which
     * SRC ID you want to remove?", JOptionPane.QUESTION_MESSAGE, null, options,
     * // Array of choices options[0]); // Initial choice
     *
     * if (removeid != null) { int removeidint = Integer.parseInt(removeid); con
     * = null; stat = null; try { Class.forName("org.sqlite.JDBC"); con =
     * DriverManager.getConnection("jdbc:sqlite:data.db");
     * con.setAutoCommit(false); stat = con.createStatement();
     *
     * PreparedStatement statement = con.prepareStatement("DELETE FROM SRC WHERE
     * \"ID\" =?;"); statement.setInt(1, removeidint);
     *
     * statement.execute(); con.commit(); stat.close(); con.close();
     *
     * } catch (ClassNotFoundException | SQLException ex) {
     * System.err.println(event.getClass().getName() + ": " + ex.getMessage());
     * System.exit(0); } }
     *
     * src_table.setItems((ObservableList<SRCinfo>)
     * load("jdbc:sqlite:data.db"));
     *
     * }
     * }
     */
    private void delete_bt(ActionEvent event) {
    }
    /*                   working on it
    private void edit(ActionEvent event) throws SQLException {

        try {
            String sql = "UPDATE SRC SET Name=?, FirstLineAdd=?, SecondLineAdd = ?, County=?, PostCode=?, PhoneNo=?, Email=?";

            rs = db.query(sql);

            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            stat = con.createStatement();
            while (rs.next()) {
                srcdb.add(new SRCinfo(rs.getInt("ID"), rs.getString("Name"), rs.getString("FirstLineAdd"), rs.getString("SecondLineAdd"),
                        rs.getString("County"), rs.getString("PostCode"), rs.getInt("PhoneNo"), rs.getString("Email")));

                src_table_id.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("ID"));
                src_table_name.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Name"));
                src_table_1add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("FirstLineAdd"));
                src_table_2add.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("SecondLineAdd"));
                src_table_county.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("County"));
                src_table_pcode.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("PostCode"));
                src_table_pnumber.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, Integer>, ObservableValue<Integer>>) new PropertyValueFactory("PhoneNo"));
                src_table_emailadd.setCellValueFactory((Callback<TableColumn.CellDataFeatures<SRCinfo, String>, ObservableValue<String>>) new PropertyValueFactory("Email"));

            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error");
            Logger.getLogger(SRCController.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                con.close();
                stat.close();
            } catch (Exception e) {

            }
        }

        src_table.setItems(srcdb);

        /*
  connection = sqlConnect.returnConnection();
    String query="UPDATE SRC SET Name= ?,Stock=?,Price=?,Description=? WHERE StockID=?";
    //create preparedStatement
    try {
        PreparedStatement prStatement = connection.prepareStatement(query);
        prStatement.setInt(5, StockTable.getSelectionModel().getSelectedItem().getStockID());
        prStatement.setString(1, editPartNameTxt.getText());
        prStatement.setInt(2,Integer.parseInt(editQuantityTxt.getText()));
        prStatement.setInt(3, Integer.parseInt(editPriceTxt.getText()));
        prStatement.setString(4, editDescTxt.getText());
        prStatement.execute();
        connection.close();
        itemListDropDownInt();
    refreshStockTable();
        //refresh table
    } catch (SQLException ex) {
        ex.printStackTrace();
        System.err.println("error");

    }

}
     */
}
