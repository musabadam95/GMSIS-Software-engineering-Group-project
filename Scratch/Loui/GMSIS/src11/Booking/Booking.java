/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diagrep;

import java.util.Date;

public abstract class Booking       
{
    
    private final int BookingID;
    private Date BookingDate;
    private Vehicle myVehicle;

    public Booking()
        {
            BookingID = (int) Math.round(Math.random()*1000000);
        }


    public void setVehicle(Vehicle setVehicle)
        {
            myVehicle = setVehicle;
        }

    public void setBookingDate()
        {

        }   

}


