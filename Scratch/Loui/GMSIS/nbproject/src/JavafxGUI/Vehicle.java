/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import customers.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;


public class Vehicle {

    private StringProperty VehReg;
    private StringProperty VehModel;
    private StringProperty VehMake;
    private IntegerProperty EngSize;
    private StringProperty FuelType;
    private StringProperty Colour;
    private StringProperty RenewalDate;


    public Vehicle(String VehReg, String VehModel, String VehMake, int EngSize, String FuelType, String Colour, String RenewalDate) {
    
        this.VehReg = new SimpleStringProperty(VehReg);
        this.VehModel = new SimpleStringProperty(VehModel);
        this.VehMake = new SimpleStringProperty(VehMake);
        this.EngSize = new SimpleIntegerProperty(EngSize);
        this.FuelType = new SimpleStringProperty(FuelType);
        this.Colour = new SimpleStringProperty(Colour);
        this.RenewalDate = new SimpleStringProperty(RenewalDate);
    }

    

    public String getVehReg() {
        return VehReg.get();
    }

    public String getVehModel() {
        return VehModel.get();
    }

    public String getVehMake() {
        return VehMake.get();
    }

    public double getEngSize() {
        return EngSize.get();
    }

    public String getFuelType() {
        return FuelType.get();
    }

    public String getColour() {
        return Colour.get();
    }

    public void setColour(String col) {
        Colour.set(col);
    }

    public String getRenewalDate() {
        return RenewalDate.get();
    }

    public void setRenewalDate(String rdate) {
        RenewalDate.set(rdate);
    }
}
