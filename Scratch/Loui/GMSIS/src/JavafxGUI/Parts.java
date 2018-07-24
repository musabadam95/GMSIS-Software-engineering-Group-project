/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import java.sql.Date;
import java.util.Calendar;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parts {

    private final SimpleIntegerProperty PartsID;
    private final SimpleStringProperty PartName;
    private final SimpleStringProperty Description;
    private final SimpleFloatProperty SRCCost;
    private final SimpleStringProperty SRC;
    private final SimpleStringProperty VehReg;
    private final SimpleStringProperty ExpReturn;
    private final SimpleStringProperty ExpSend;

    public Parts(int PartsID, String PartName, String Description, Float SRCCost, String SRC, String VehReg,
            String ExpSend, String ExpReturn) {
        this.PartsID = new SimpleIntegerProperty(PartsID);
        this.PartName = new SimpleStringProperty(PartName);
        this.Description = new SimpleStringProperty(Description);
        this.SRCCost = new SimpleFloatProperty(SRCCost);
        this.SRC = new SimpleStringProperty(SRC);
        this.VehReg = new SimpleStringProperty(VehReg);
        this.ExpSend = new SimpleStringProperty(ExpSend);
        this.ExpReturn = new SimpleStringProperty(ExpReturn);


    }
    
    public int getPartsID(){
        return PartsID.get();
    }

    public String getPartName() {
        return PartName.get();
    }

    public void setPartName(String PartName) {
        this.PartName.set(PartName);
    }

    public float getSRCCost() {
        return SRCCost.get();
    }

    public void setSRCCost(int SRCCost) {
        this.SRCCost.set(SRCCost);
    }


    /**
     * @return the Description
     */
    public String getDescription() {
        return Description.get();
    }

    public void setDescription(String Description) {
        this.Description.set(Description);
    }

    public String getSRC() {
        return SRC.get();
    }

    public void setSRC(String SRC) {
        this.SRC.set(SRC);
    }

    public String getVehReg() {
        return VehReg.get();
    }

    public void setVehReg(String VehReg) {
        this.VehReg.set(VehReg);
    }

    public String getExpSend() {
        return ExpSend.get();
    }

    public void setExpSend(String ExpSend) {
        this.ExpSend.set(ExpSend);
    }

    public String getExpReturn() {
        return ExpReturn.get();
    }

    public void setExpReturn(String ExpReturn) {
        this.ExpReturn.set(ExpReturn);
    }
}
