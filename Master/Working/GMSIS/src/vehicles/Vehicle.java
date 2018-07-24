/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicles;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty; 


public class Vehicle {

    private StringProperty type;
    private StringProperty VehReg;
    private StringProperty VehModel;
    private StringProperty VehMake;
    private StringProperty EngSize; //maybe change to integer
    private StringProperty FuelType;
    private StringProperty Colour;
    private StringProperty RenewalDate;
    private StringProperty LastServiceDate;
    private StringProperty Mileage;
    private StringProperty WarrantyName;
    private StringProperty WarrantyAddress;
    private StringProperty WarrantyEnd;

    public Vehicle(String t, String vreg, String vmod, String vmake, String es, String ft, String c, String rd, String lsd, String m, String wn, String wa, String we) {
    
        this.type = new SimpleStringProperty(t);
        this.VehReg = new SimpleStringProperty(vreg);
        this.VehModel = new SimpleStringProperty(vmod);
        this.VehMake = new SimpleStringProperty(vmake);
        this.EngSize = new SimpleStringProperty(es);
        this.FuelType = new SimpleStringProperty(ft);
        this.Colour = new SimpleStringProperty(c);
        this.RenewalDate = new SimpleStringProperty(rd);
        this.LastServiceDate = new SimpleStringProperty(lsd);
        this.Mileage = new SimpleStringProperty(m);
        this.WarrantyName = new SimpleStringProperty(wn);
        this.WarrantyAddress = new SimpleStringProperty(wa);
        this.WarrantyEnd = new SimpleStringProperty(we);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty getTypeProperty() {
        return this.type;
    }
    
    public String getVehReg() {
        return VehReg.get();
    }
    
    public StringProperty getVehRegProperty(){
        return this.VehReg;
    }

    public String getVehModel() {
        return VehModel.get();
    }
    
    public StringProperty getVehModelProperty(){
        return VehModel;
    }

    public String getVehMake() {
        return VehMake.get();
    }
    
    public StringProperty getVehMakeProperty(){
        return VehMake;
    }
    
    public String getEngSize() {
        return EngSize.get();
    }
    
    public StringProperty getEngSizeProperty(){
        return EngSize;
    }

    public String getFuelType() {
        return FuelType.get();
    }

    public StringProperty getFuelTypeProperty(){
        return FuelType;
    }
    
    public String getColour() {
        return Colour.get();
    }

    public StringProperty getColourProperty(){
        return Colour;
    }
    
    public void setColour(String col) {
        Colour.set(col);
    }
    
    public String getRenewalDate() {
        return RenewalDate.get();
    }

    public StringProperty getRenewalDateProperty(){
        return RenewalDate;
    }
    
    public void setRenewalDate(String rdate) {
        RenewalDate.set(rdate);
    }
    
    public String getLastServiceDate() {
        return LastServiceDate.get();
    }

    public StringProperty getLastServiceDateProperty(){
        return LastServiceDate;
    }
    
    public void setLastServiceDate(String lsdate) {
        LastServiceDate.set(lsdate);
    }
    
    public String getMileage() {
        return Mileage.get();
    }

    public StringProperty getMileageProperty(){
        return Mileage;
    }
    
    public void setMileage(String mile) {
        Mileage.set(mile);
    }
    
    public String getWarrantyName() {
        return WarrantyName.get();
    }
    
    public StringProperty getWarrantyNameProperty(){
        return WarrantyName;
    }
    
    public void setWarrantyName(String wn) {
        WarrantyName.set(wn);
    }
    
    public String getWarrantyAddress() {
        return WarrantyAddress.get();
    }
    
    public StringProperty getWarrantyAddressProperty(){
        return WarrantyAddress;
    }
    
    public void setWarrantyAddress(String wa) {
        WarrantyAddress.set(wa);
    }
    
    public String getWarrantyEnd() {
        return WarrantyEnd.get();
    }
    
    public StringProperty getWarrantyEndProperty(){
        return WarrantyEnd;
    }
    
    public void setWarrantyEnd(String we) {
        WarrantyEnd.set(we);
    }
}