package JavafxGUI;

import java.sql.*;

/**
 *
 * @author louiraj
 */
public class db {

    private static final db INSTANCE = new db();
    private static Connection con = null;
    private static Statement s;

    private db() {
        getConnection();
    }

    /**
     *
     * @return
     */
    public static db getInstance() {
        return INSTANCE;
    }

    static Statement getConnection() {
        if (con == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:data.db");
                s = con.createStatement();
                return s;
            } catch (ClassNotFoundException ce) {
                System.out.println("Can't get the connection!");
                Throwable exception = ce.getException();
            } catch (SQLException se) {
                System.out.println("Can't retrieve connection!");
                se.getMessage();
            } finally {
              try{
                con.close();
                s.close();
              } catch (Exception e){

              }
            }

        }
        return s;
    }
//    private Connection connect()
//    {
//        if(con != null)
//            return con;
//        try
//        {
//            Class.forName("org.sqlite.JDBC");
//            con = DriverManager.getConnection("jdbc:sqlite:data.db");
//        } catch (ClassNotFoundException ce)
//        {
//            System.out.println("Can't get the connection!");
//            ce.getException();
//        } catch (SQLException se){
//            System.out.println("Can't retrieve connection!");
//            se.getMessage();
//        }
//
//        return con;
//    }

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */

   /* public static ResultSet query(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            s = con.createStatement();
            con.setAutoCommit(false);
            rs = s.executeQuery(sql);
        } catch (SQLException se) {
            se.printStackTrace();

        }



        return rs;
    } */


    public ResultSet query(String sql) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        try {
                        Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:data.db");
            s = con.createStatement();
            rs = s.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("SQL failure");
            e.printStackTrace();
        }

        return rs;
    }

    void insertStatement(String insert_query) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:data.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("Our query was: " + insert_query);
            stmt.executeUpdate(insert_query);
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        } finally {
            try {
                c.close();
                stmt.close();
            } catch (Exception e) {

            }
        }

    }


    private static final String deleteQuery = "DELETE FROM SRC WHERE ID = '1";

    /**
     *
     * @param uniqueIdentifier
     * @throws SQLException
     */
    public static void deleteRow(String uniqueIdentifier) {
        try{
          Statement s = con.createStatement();
        s.executeUpdate(deleteQuery + uniqueIdentifier + "'");
        con.commit();
      } catch (SQLException e){
        e.printStackTrace();
      } finally {
        try{
          con.close();
          s.close();
        } catch (Exception e){

        }
      }
    }

    /**
     *
     * @param query
     * @throws SQLException
     */
    public static void executeUpdate(String query) throws SQLException {
        getConnection().executeUpdate(query);
    }

}
