/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.sql.Date;
import java.util.Calendar;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parts {

    private SimpleIntegerProperty stockID;
    private SimpleIntegerProperty PartID;
    private SimpleStringProperty partName;
    private SimpleIntegerProperty stockam;
    private SimpleIntegerProperty cost;
    private SimpleStringProperty Description;
    private SimpleStringProperty Warranty;
    private SimpleStringProperty DateInstalled;
    private SimpleStringProperty SPC;
    private SimpleStringProperty vehReg;

    Parts(int stockID, String partName, int stock, int cost, String Description) {
        this.stockID = new SimpleIntegerProperty(stockID);
        this.partName = new SimpleStringProperty(partName);
        this.stockam = new SimpleIntegerProperty(stock);
        this.cost = new SimpleIntegerProperty(cost);
        this.Description = new SimpleStringProperty(Description);
    }

    public Parts(int partID, String partNam, String dateInstalled, String warrantyEnd, int cost, String SPC) {
        this.partName = new SimpleStringProperty(partNam);
        this.cost = new SimpleIntegerProperty(cost);
        this.PartID = new SimpleIntegerProperty(partID);
        this.DateInstalled = new SimpleStringProperty(dateInstalled);
        this.Warranty = new SimpleStringProperty(warrantyEnd);
           this.SPC = new SimpleStringProperty(SPC);
    }

    public Parts(String vehReg,int partID, String partNam, String Description, String dateInstalled, String warrantyEnd, int cost) {
        this.vehReg=new  SimpleStringProperty(vehReg);
        this.partName = new SimpleStringProperty(partNam);
        this.cost = new SimpleIntegerProperty(cost);
        this.PartID = new SimpleIntegerProperty(partID);
        this.DateInstalled = new SimpleStringProperty(dateInstalled);
        this.Warranty = new SimpleStringProperty(warrantyEnd);
        this.Description = new SimpleStringProperty(Description);
    }
    
    
    public String getVehReg() {
        return vehReg.get();
    } 
    
    public void setVehReg(String vehReg) {
        this.vehReg.set(vehReg);
    }
    

    public String getSPC() {
        return SPC.get();
    }

    public void setSPC(String SPC) {
        this.SPC.set(SPC);
    }

    public int getStockID() {
        return stockID.get();
    }

    public void setStockID(int stockID) {
        this.stockID.set(stockID);
    }

    public int getPartID() {
        return PartID.get();
    }

    public void setPartID(int PartID) {
        this.PartID.set(PartID);
    }

    public String getWarranty() {
        return Warranty.get();
    }

    public void setWarranty(String Warranty) {
        this.Warranty.set(Warranty);
    }

    public String getDateInstalled() {
        return this.DateInstalled.get();
    }

    public void setDateInstalled(String DateInstalled) {
        this.DateInstalled.set(DateInstalled);
    }

    public String getDescription() {
        return this.Description.get();
    }

    public void setDescription(String desc) {
        this.Description.set(desc);
    }

    public String getPartName() {
        return partName.get();
    }

    public void setPartName(String pName) {
        partName.set(pName);
    }

    public void setStock(int pStock) {
        stockam.set(pStock);
    }

    public void setCost(int pCost) {
        cost.set(pCost);
    }

    public int getStock() {
        return stockam.get();
    }

    public int getCost() {
        return cost.get();
    }

}
