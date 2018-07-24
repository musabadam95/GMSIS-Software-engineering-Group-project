package Customer;

import javafx.beans.property.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Romade on 17/03/2017.
 */
public class Account {

    private IntegerProperty billID;
    private IntegerProperty bookID;
    private StringProperty vehReg;
    private DoubleProperty total;
    private StringProperty status;

    public Account(ResultSet rs){
        try{
            billID = new SimpleIntegerProperty(rs.getInt("BillID"));
            bookID = new SimpleIntegerProperty(rs.getInt("BookingID"));
            vehReg = new SimpleStringProperty(rs.getString("VehReg"));
            total = new SimpleDoubleProperty(rs.getDouble("Bill"));
            status = new SimpleStringProperty(rs.getString("PaymentStatus"));
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    public int getBillID() {
        return billID.get();
    }

    public IntegerProperty billIDProperty() {
        return billID;
    }

    public int getBookID() {
        return bookID.get();
    }

    public IntegerProperty bookIDProperty() {
        return bookID;
    }

    public String getVehReg() {
        return vehReg.get();
    }

    public StringProperty vehRegProperty() {
        return vehReg;
    }

    public double getTotal() {
        return total.get();
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }
}
