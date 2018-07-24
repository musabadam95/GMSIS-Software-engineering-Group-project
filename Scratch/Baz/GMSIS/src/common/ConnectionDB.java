package common;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

//Use this to create a connection
//Tutorial
//Create an instance of the ConnectionDB class
//use the method returnConnection() for a connection to database
public class ConnectionDB {

    Connection connection;

    public ConnectionDB() {

    }
//returns connection

    public Connection returnConnection() {
 System.gc();
        try {          
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
            return connection;
        } catch (SQLException ex) {
            throw new RuntimeException("Database connection failed!", ex);
        }
    }
}
