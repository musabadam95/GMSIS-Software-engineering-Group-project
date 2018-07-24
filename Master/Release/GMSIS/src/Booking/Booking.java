/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Booking;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Booking       
{
    private SimpleStringProperty FirstName;
    private SimpleStringProperty LastName;
    private SimpleStringProperty BookingTime;
    private SimpleIntegerProperty BookingID;
    private SimpleStringProperty BookingDate;
    private SimpleStringProperty VehReg;
    private SimpleStringProperty VehMake;
    private SimpleIntegerProperty bookingDur;
    private SimpleStringProperty CusID;
    private SimpleStringProperty BookingType;
    private SimpleIntegerProperty vehMileage;
    private SimpleIntegerProperty MechanicID;
    
    public Booking (int setBookingID,int setBookingDuration, String setCusID, String setBookingTime, String setBookingType, String setFName, String setLName, String setBookingDate, String setVehReg, String setVehMake, int setMileage, int setMechanicID)
    {
        this.bookingDur=new SimpleIntegerProperty(setBookingDuration);
        this.BookingID=new SimpleIntegerProperty(setBookingID);
        this.CusID = new SimpleStringProperty(setCusID);
        this.BookingTime =  new SimpleStringProperty(setBookingTime);
        this.BookingType = new SimpleStringProperty(setBookingType);
        this.FirstName = new SimpleStringProperty(setFName);
        this.LastName = new SimpleStringProperty(setLName);
        this.BookingDate = new SimpleStringProperty(setBookingDate);
        this.VehReg = new SimpleStringProperty(setVehReg);
        this.VehMake = new SimpleStringProperty(setVehMake);
        this.vehMileage = new SimpleIntegerProperty(setMileage);
        this.MechanicID = new SimpleIntegerProperty(setMechanicID);
    }
    
    public Booking(String setFirstname, String setLastname, String setVReg, String setVName, String setCusID)
    {   
        this.FirstName = new SimpleStringProperty(setFirstname);
        this.LastName = new SimpleStringProperty(setLastname);  
        this.VehReg = new SimpleStringProperty(setVReg);
        this.VehMake = new SimpleStringProperty(setVName);
        this.CusID = new SimpleStringProperty(setCusID);
    }   
    
    public Booking (String setVehReg, int setBookingID, int setBookingDuration, String setBookingTime, String setBookingType, String setBookingDate)
    {
        this.VehReg = new SimpleStringProperty(setVehReg);
        this.BookingID=new SimpleIntegerProperty(setBookingID);
        this.bookingDur=new SimpleIntegerProperty(setBookingDuration);
        this.BookingTime =  new SimpleStringProperty(setBookingTime);
        this.BookingType = new SimpleStringProperty(setBookingType);
        this.BookingDate = new SimpleStringProperty(setBookingDate);
    }

    public int getBookingID()
        {
            return BookingID.get();
        }
    
    public int getMileage()
    {
        return vehMileage.get();
    }
    
    public String getCusID()
        {
            return CusID.get();
        }
    
    public String getVehReg()
        {
            return VehReg.get();
        }
    
    public String getVehMake()
        {
            return VehMake.get();
        }
    
    public String getBookingTime()
        {
            return BookingTime.get();
        }
    
    public String getBookingDate()
        {
            return BookingDate.get();
        }
    
    public String getBookingType()
        {
            return BookingType.get();
        }
    

    public int getDuration()
        {
            return bookingDur.get();

        } 
    
    public String getFirstName()
        {
            return FirstName.get();
        }
    
    public String getLastName()
        {
            return LastName.get();
        }
    
    public int getMechanicID()
    {
        return MechanicID.get();
    }

}


