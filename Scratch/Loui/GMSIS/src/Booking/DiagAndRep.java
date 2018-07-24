/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Booking;

import javax.swing.JOptionPane;

/**
 *
 * @author qhzrv
 */
public class DiagAndRep extends Booking 
{
    private int bookingDuration;
    
public DiagAndRep() // prompts the user to set the time worked on the booking. Can be called and modified.
{
  int time = Integer.parseInt(JOptionPane.showInputDialog(null,"Duration?"));
  BookingDuration = time;
}
}
