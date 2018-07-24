package specialist;

import java.sql.*;
/**
 *
 * @author louiraj
 */
public class db
{
    private static final db INSTANCE = new db();
    private static Connection con = null;
    private static Statement s;


    private db()
    {
        getConnection();
    }
    public static db getInstance(){
        return INSTANCE;
    }
    private static Statement getConnection(){
        if(con == null){
           try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            s = con.createStatement();
            return s;
        } catch (ClassNotFoundException ce)
        {
            System.out.println("Can't get the connection!");
            ce.getException();
        } catch (SQLException se){
            System.out.println("Can't retrieve connection!");
            se.getMessage();
        }


        }
        return s;
    }
//  
    public static ResultSet query(String sql) throws SQLException
    {
        ResultSet rs = getConnection().executeQuery(sql);
        return rs;
    }
    private static final String deleteQuery = "DELETE FROM CustomerAccounts WHERE CustomerID = '";

 
    public static void deleteRow(String uniqueIdentifier) throws SQLException{
        Statement s = con.createStatement();
        s.executeUpdate(deleteQuery + uniqueIdentifier + "'");
        con.commit();
    }

    
    public static void executeUpdate(String query) throws SQLException{
        getConnection().executeUpdate(query);
    }

}
