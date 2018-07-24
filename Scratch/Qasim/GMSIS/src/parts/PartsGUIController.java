package parts;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author muzab
 */
public class PartsGUIController implements Initializable {

    @FXML
    private ComboBox<String> itemListDropDown;
    @FXML
    private TableView<Parts> StockTable;
    @FXML
    private TableColumn colPartName;
    @FXML
    private TableColumn colStock;
    @FXML
    private TableColumn colCost;

    @FXML
    private Button addnewItemBtn;
    @FXML
    private TextField partNameTxt;
    @FXML
    private TextField quantityTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    private Button addStock;
    @FXML
    private ComboBox<Integer> quantityTxt1;
    @FXML
    private TableColumn colDesc;
    @FXML
    private TextArea descTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        itemListDropDownInt();
        refreshStockTable();
        
        //connect to database

    }
    //Enable and Populate TableView
    public void refreshStockTable() {
        //connects to database
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        //populate tableview using data from database
        ObservableList<Parts> data = FXCollections.<Parts>observableArrayList();
        String Sqlite = "SELECT * FROM `PartStock`";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                data.add(new Parts(rs.getString("PartName"), rs.getInt("Stock"), rs.getInt("Price")));
            }
          
            colPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartName"));
            colStock.setCellValueFactory(new PropertyValueFactory<Parts, String>("Stock"));
            colCost.setCellValueFactory(new PropertyValueFactory<Parts, String>("Cost"));
            StockTable.setItems(data);
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

    }

    //Enable dynamic dropdownbox
    public void itemListDropDownInt() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        String Sqlite = "SELECT * FROM `PartStock`";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                itemListDropDown.getItems().add(rs.getString("PartName"));
            }

            connection.close();
        } catch (SQLException ex) {
            System.out.println("error");
        }
for(int i=0;i<=100;i++){
quantityTxt1.getItems().add(i);
}
    }

    @FXML
    //Allows a brand new stock item to be added to system
    public void addNewStock(ActionEvent event) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        String query = " insert into PartStock (PartName,Stock, Price)"
                + " values ( ?, ?, ?)";
        //create preparedStatement
        try {
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setString(1, partNameTxt.getText());
            prStatement.setInt(2, Integer.parseInt(quantityTxt.getText()));
            System.out.println(priceTxt.getText());
            prStatement.setInt(3, Integer.parseInt(priceTxt.getText()));
            prStatement.execute();
            connection.close();
            initialize(null, null);
            //refresh table
            refreshStockTable();
        } catch (SQLException ex) {
            System.err.println("error");
        }
    }
//Decreases quatity of withdrawned part
    public boolean partWithdraw(int stockID) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        try {
            String sqlQuery = "UPDATE PartStock SET Stock=Stock-1 WHERE StockID=?";
            PreparedStatement prStatement = connection.prepareStatement(sqlQuery);
            prStatement.setInt(1, stockID);
            prStatement.execute();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @FXML
    private void addToCurrentStock(ActionEvent event) {
           Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
        try {
            String sqlQuery = "UPDATE PartStock SET Stock=Stock+? WHERE PartName=?";
            PreparedStatement prStatement = connection.prepareStatement(sqlQuery);
            prStatement.setInt(1, quantityTxt1.getValue());
            prStatement.setString(2, itemListDropDown.getValue());
            prStatement.execute();
            connection.close();
            refreshStockTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
           
        }
    }

   
   
      
    
}
