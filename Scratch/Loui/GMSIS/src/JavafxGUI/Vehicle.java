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
    private StringProperty Vsrc;
    private StringProperty VSRCCost;
    private StringProperty VExpSend;
    private StringProperty VExpReturn;

    public Vehicle(String vreg, String t, String vmod, String vmake, String es, String vc, String ves, String ver, String vs) {

    this.VehReg = new SimpleStringProperty(vreg);

        this.type = new SimpleStringProperty(t);
        this.VehModel = new SimpleStringProperty(vmod);
        this.VehMake = new SimpleStringProperty(vmake);
        this.EngSize = new SimpleStringProperty(es);
        this.VSRCCost = new SimpleStringProperty(vc);
        this.VExpSend = new SimpleStringProperty(ves);
        this.VExpReturn = new SimpleStringProperty(ver);
        this.Vsrc = new SimpleStringProperty(vs);

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

    public String getVsrc() {
        return Vsrc.get();
    }

    public StringProperty getVsrcProperty(){
        return Vsrc;
    }

    public String getVSRCCost() {
        return VSRCCost.get();
    }

    public StringProperty getVSRCCostProperty(){
        return VSRCCost;
    }

    public void setVSRCCost(String col) {
        VSRCCost.set(col);
    }

    public String getVExpSende() {
        return VExpSend.get();
    }

    public StringProperty getVExpSendeProperty(){
        return VExpSend;
    }

    public void setExpSend(String rdate) {
        VExpSend.set(rdate);
    }

    public String getVExpReturn() {
        return VExpReturn.get();
    }

    public StringProperty getVExpReturnProperty(){
        return VExpReturn;
    }

    public void setVExpReturn(String lsdate) {
        VExpReturn.set(lsdate);
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
