/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.JDBC;

public class UserRegistry {

    private static final UserRegistry INSTANCE = new UserRegistry();

    private Connection connection = null;

    UserRegistry() {

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:data.db");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Database connection failed!", ex);
        }
    }

    public void addUser(String Username,String Surname, String Password,boolean admin) {
    int adminDigit=0;
    //set userType
        if(admin){
        adminDigit=1;
        }
        String query=" insert into User (Name, Surname, Password, UserType)"
        + " values (?, ?, ?, ?)";
        //create preparedStatement
        try{
        PreparedStatement prStatement=connection.prepareStatement(query);
        prStatement.setString(1, Username);
        prStatement.setString(2, Surname);
        prStatement.setString(3, Password);
        prStatement.setInt(4,1);
        prStatement.execute();
        connection.close();
        }catch(SQLException ex){
        System.err.println("error");
        }
        }
    public void editUser(int UID,String Username,String Surname,boolean admin){
     
        int adminDigit=0;
    if(admin){
    adminDigit=1;
    }
    String SQL="UPDATE User SET Name=?,Surname=?,UserType=? WHERE UserID=?";
    try{
      PreparedStatement prStatement=connection.prepareStatement(SQL);
                prStatement.setString(1, Username);
                prStatement.setString(2, Surname);
                prStatement.setInt(3, adminDigit);
                prStatement.setInt(4, UID);
                prStatement.execute();
                connection.close();
    }catch(SQLException e){
    e.printStackTrace();
    
    }
    }
    public void deleteUser(int id) {
        //create query to delete specified User by ID
 String query=" DELETE FROM User where UserId = ?";
        //create preparedStatement
        try{
        PreparedStatement prStatement=connection.prepareStatement(query);
        prStatement.setInt(1, id);
        prStatement.execute();
        connection.close();
        }catch(SQLException ex){
        System.out.println("error");       
        }
    }

    public int checkUser(String Username, String Password) {
        //create query for the specified username and password
        String Sqlite = "SELECT * FROM `User` WHERE Name= "+"'"+Username+"' AND Password= '"+Password+"'";   
        ResultSet rs;
           try {
           Statement st = connection.createStatement();
            st.setQueryTimeout(10);
            rs = st.executeQuery(Sqlite);
           if(rs.getString("name").equals(Username)&&rs.getString("password").equals(Password)){
               int result=rs.getInt("UserType");
               connection.close();
              return result;
           }
            connection.close();
        } catch (SQLException ex) {
            System.out.println("error");
        }
          
        return 0;
    }

    public boolean updatePassword(String Username,String Password,String newPassword){
    int check=checkUser(Username,Password);
    if (check==1){
        String query=" UPDATE User set Password = ? where Name = ? AND Password = ?";
        //create preparedStatement
            try{
                PreparedStatement prStatement=connection.prepareStatement(query);
                prStatement.setString(1, newPassword);
                prStatement.setString(2, Username);
                prStatement.setString(3, Password);
                prStatement.execute();
                connection.close();
            }catch(SQLException ex){
                System.out.println("error");  
                return false;
            }
    }else{
            System.out.println("error");
    return false;
    }
    return true;
    }
    
    public static UserRegistry getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
