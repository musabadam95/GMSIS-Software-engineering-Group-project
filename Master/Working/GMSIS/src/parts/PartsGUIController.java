package parts;

import common.ConnectionDB;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author muzab
 */
public class PartsGUIController implements Initializable {

    ConnectionDB sqlConnect;
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
    private Connection connection;
    @FXML
    private TextField editPartNameTxt;
    @FXML
    private TextField editQuantityTxt;
    @FXML
    private TextField editPriceTxt;
    @FXML
    private Button editBtn;
    @FXML
    private TextArea editDescTxt;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableColumn colStockID;
    @FXML
    private AnchorPane refreshAlls;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sqlConnect = new ConnectionDB();
        itemListDropDownInt();
        refreshStockTable();
    }

    //Enable and Populate TableView
    public void refreshStockTable() {
        //connects to database
        connection = sqlConnect.returnConnection();
        //populate tableview using data from database
        ObservableList<Parts> data = FXCollections.<Parts>observableArrayList();
        String Sqlite = "SELECT * FROM `PartStock`";
        ResultSet rs;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(Sqlite);
            while (rs.next()) {
                data.add(new Parts(rs.getInt("StockID"), rs.getString("PartName"), rs.getInt("Stock"), rs.getInt("Price"), rs.getString("Description")));
            }
            colStockID.setCellValueFactory(new PropertyValueFactory<Parts, String>("StockID"));
            colPartName.setCellValueFactory(new PropertyValueFactory<Parts, String>("PartName"));
            colStock.setCellValueFactory(new PropertyValueFactory<Parts, String>("Stock"));
            colCost.setCellValueFactory(new PropertyValueFactory<Parts, String>("Cost"));
            colDesc.setCellValueFactory(new PropertyValueFactory<Parts, String>("Description"));
            StockTable.setItems(data);
            connection.close();
        } catch (SQLException ex) {
            throw new RuntimeException("Database connection failed!", ex);
        }

    }

    //Enable dynamic dropdownbox
    public void itemListDropDownInt() {
        connection = sqlConnect.returnConnection();
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
        //enable selection up to 100
        for (int i = 0; i <= 100; i++) {
            quantityTxt1.getItems().add(i);
        }
    }
    @FXML
    //Allows a brand new stock item to be added to system
    public void addNewStock(ActionEvent event) throws SQLException {
        System.gc();
        connection = sqlConnect.returnConnection();
        String query = " insert into PartStock (PartName,Stock, Price,Description)"
                + " values ( ?, ?, ?,?)";
        //create preparedStatement
        try {
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setString(1, partNameTxt.getText());
            prStatement.setInt(2, Integer.parseInt(quantityTxt.getText()));
            prStatement.setInt(3, Integer.parseInt(priceTxt.getText()));
            prStatement.setString(4, descTxt.getText());
            prStatement.execute();
            connection.close();
            itemListDropDownInt();
            refreshStockTable();
            //refresh table
        } catch (Exception ex) {
            switch (ex.getClass().getSimpleName()) {
                case "NumberFormatException":
                    JOptionPane.showMessageDialog(null, "Please Input the the Quantity and Price in number formar");
                    
                default:
                    break;

            }
        }
    }

    @FXML
    private void addToCurrentStock(ActionEvent event) {
        connection = sqlConnect.returnConnection();
        try {
            String sqlQuery = "UPDATE PartStock SET Stock=Stock+? WHERE PartName=?";
            PreparedStatement prStatement = connection.prepareStatement(sqlQuery);
            prStatement.setInt(1, quantityTxt1.getValue());
            prStatement.setString(2, itemListDropDown.getValue());
            prStatement.execute();
            connection.close();
            refreshStockTable();
        } catch (Exception ex) {
            switch (ex.getClass().getSimpleName()) {
                case "NullPointerException":
                    JOptionPane.showMessageDialog(null, "Please select the item you would like to stock into and select stock amount from the dropdown box");
                    break;

                default:
                    break;
            }
        }
    }

    @FXML
    private void editStock(ActionEvent event) {
        connection = sqlConnect.returnConnection();
        String query = "UPDATE PartStock SET PartName= ?,Stock=?,Price=?,Description=? WHERE StockID=?";
        //create preparedStatement
        try {
            PreparedStatement prStatement = connection.prepareStatement(query);
            prStatement.setInt(5, StockTable.getSelectionModel().getSelectedItem().getStockID());
            prStatement.setString(1, editPartNameTxt.getText());
            prStatement.setInt(2, Integer.parseInt(editQuantityTxt.getText()));
            prStatement.setInt(3, Integer.parseInt(editPriceTxt.getText()));
            prStatement.setString(4, editDescTxt.getText());
            prStatement.execute();
            connection.close();
            itemListDropDownInt();
            refreshStockTable();
            //refresh table
        } catch (Exception ex) {
ex.printStackTrace();
        }

    }

    @FXML
    private void fillData(MouseEvent event) {
        try {
            editPartNameTxt.setText(StockTable.getSelectionModel().getSelectedItem().getPartName());
            editQuantityTxt.setText(Integer.toString(StockTable.getSelectionModel().getSelectedItem().getStock()));
            editPriceTxt.setText(Integer.toString(StockTable.getSelectionModel().getSelectedItem().getCost()));
            editDescTxt.setText(StockTable.getSelectionModel().getSelectedItem().getDescription());
        } catch (NullPointerException e) {
JOptionPane.showMessageDialog(null,"Error in selecting data, Please try again");
        }

    }

    @FXML
    private void deleteStock(ActionEvent event) {
        connection = sqlConnect.returnConnection();
        try {
            String sqlQuery = "DELETE FROM PartStock WHERE PartName=?";
            PreparedStatement prStatement = connection.prepareStatement(sqlQuery);
            prStatement.setString(1, StockTable.getSelectionModel().getSelectedItem().getPartName());
            prStatement.execute();
            connection.close();
            refreshStockTable();
        } catch (Exception ex) {
            switch (ex.getClass().getSimpleName()) {
                case "NullPointerException":
                    JOptionPane.showMessageDialog(null, "Select item to delete from the table view");
                    break;
                default:
                    break;
            }
        }
    }

    @FXML
    private void refreshAll(ActionEvent event) {
                itemListDropDownInt();
        refreshStockTable();
    }

}
