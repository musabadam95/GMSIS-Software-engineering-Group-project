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

    public void test_database() {

        Statement statement;
        try {
            statement = connection.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("drop table if exists `User`");

            statement.executeUpdate("create table `User` (`id` integer,`name` string,`surname` string ,`password` string)");

            ResultSet rs = statement.executeQuery("select * from `User`");

            System.out.println("id	name");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "	" + rs.getString("name"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }


    public void addUser(String Username,String Surname, String Password,boolean admin) {
    int adminDigit=0;
    //set userType
        if(admin){
        adminDigit=1;
        }
        String query=" insert into User (name, surname, password, UserType)"
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

    public void deleteUser(int id) {
        //create query to delete specified User by ID
 String query=" DELETE FROM User where id = ?";
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
        String Sqlite = "SELECT * FROM `User` WHERE name= "+"'"+Username+"' AND password= '"+Password+"'";   
        ResultSet rs;
           try {
           Statement st = connection.createStatement();
            st.setQueryTimeout(10);
            rs = st.executeQuery(Sqlite);
           if(rs.getString("name").equals(Username)&&rs.getString("password").equals(Password)){
              System.out.println(rs.getString("name")+" "+rs.getString("surname"));
              return rs.getInt("UserType");
           }
        } catch (SQLException ex) {
            System.out.println("error");
        }
        return 0;
    }

    public void updatePassword(String Username,String Password,String newPassword){
    int check=checkUser(Username,Password);
    if (check==1){
        String query=" UPDATE User set password = ? where name = ? AND password = ?";
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
            }
    }else{
            System.out.println("error");
    return;
    }
    
    }
    
    public static UserRegistry getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return this.connection;
    }
}
