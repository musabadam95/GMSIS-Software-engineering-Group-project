/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import common.ConnectionDB;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
       
        
        ObservableList<Vehicle> allVehicles = FXCollections.observableArrayList();
        
        String request = "SELECT * FROM Vehicles";
        
        PreparedStatement ps = null;
        
        ResultSet rs = null;
        
        //add try..
        try
        {
             connection = sqlConnect.returnConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            
           
            while(rs.next()){
               String vType = rs.getString("VehType");
               String vReg = rs.getString("VehReg");
               String vMod = rs.getString("VehModel");
               String vMake = rs.getString("VehMake");
               String vEng = rs.getString("EngSize");
               String vFuel = rs.getString("FuelType");
               String vCol = rs.getString("Colour");
               String vRD = rs.getString("RenewalDate");
               String vLSD = rs.getString("LastServiceDate");
               String vMile = rs.getString("CurrentMileage");
               String vWN = rs.getString("WarrantyName");
               String vWA = rs.getString("WarrantyAddress");
               String vWE = rs.getString("WarrantyEnd");
               
                Vehicle newVehicle = new Vehicle(vType,vReg,vMod,vMake,vEng,vFuel,vCol,vRD,vLSD,vMile,vWN,vWA,vWE);
                allVehicles.add(newVehicle);
            }
            
        rs.close();
        ps.close();
        connection.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }

        
        return allVehicles;
    }
    
}
