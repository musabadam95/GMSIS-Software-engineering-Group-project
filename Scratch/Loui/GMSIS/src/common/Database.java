/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;
import common.ConnectionDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import common.*;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import JavafxGUI.*;
import javafx.collections.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Baz
 */
public class Database {
    
    private Connection connection;
    private ConnectionDB sqlConnect;
    private TableView tableview;
    
    public final static Database INSTANCE = new Database();
    
    private Database(){
        
    }
    
    public static Database getInstance(){
        return INSTANCE;
    }
    
    
    public ObservableList<Vehicle> getAllVehicles(){
        //PUT SQL 
        sqlConnect = new ConnectionDB();
        connection = sqlConnect.returnConnection();
        
        ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();
        
        String request = "SELECT * FROM Vehicles";
        
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        //add try..
        try
        {
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            
           
            while(rs.next()){
                               String vReg = rs.getString("VehReg");

               String vType = rs.getString("VehType");
               String vMod = rs.getString("VehModel");
               String vMake = rs.getString("VehMake");
               String vEng = rs.getString("EngSize");
               String vCost = rs.getString("VSRCCost");
               String vSend = rs.getString("VExpSend");
               String vRturn = rs.getString("VExpReturn");
               String vSRC = rs.getString("VSRC");
               
               
                Vehicle newVehicle = new Vehicle(vType,vReg,vMod,vMake,vEng,vCost,vSend,vRturn,vSRC);
                allVehicles.add(newVehicle);
            }
        
        
         
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

        
        return allVehicles;
    }
    
    
    
}
