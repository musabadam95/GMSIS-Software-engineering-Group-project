/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

import java.sql.Date;
import java.util.Calendar;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parts {

    private final SimpleIntegerProperty PartsID;
    private final SimpleStringProperty PartName;
    private final SimpleStringProperty Description;
    private final SimpleIntegerProperty SRCCost;
    private final SimpleBooleanProperty SRC;
    private final SimpleStringProperty VehReg;
    private  Date ExpReturn;
    private  Date ExpSend;
    


    /**
     *
     * @param PartsID
     * @param PartsName
     * @param Description
     * @param SRCCost
     * @param SRC
     * @param VehReg
     */
    public Parts(int PartsID, String PartName, String Description, int SRCCost, boolean SRC, String VehReg, Date ExpSend, Date ExpReturn) {
        this.PartsID = new SimpleIntegerProperty(PartsID);
        this.PartName = new SimpleStringProperty(PartName);
        this.Description = new SimpleStringProperty(Description);
        this.SRCCost = new SimpleIntegerProperty(SRCCost);
        this.SRC = new SimpleBooleanProperty(SRC);
        this.VehReg = new SimpleStringProperty(VehReg);
 
    }


    public String getPartName() {
        return PartName.get();
    }

    public void setPartName(String PartName) {
        this.PartName.set(PartName);
    }

    public int getSRCCost(){ return SRCCost.get();}

    public void setSRCCost(int SRCCost) {
        this.SRCCost.set(SRCCost);
    }

    /**
     * @return the PartsID
     */
    public int getPartsID() {
        return PartsID.get();
    }



    /**
     * @return the Description
     */
    public String getDescription() {
        return Description.get();
    }

    public void setDescription(String Description){
      this.Description.set(Description);
    }

    public boolean getSRC(){
        return SRC.get();
    }

    public void setSRC(boolean SRC){
        this.SRC.set(SRC);
    }

    public String getVehReg(){
      return VehReg.get();
    }

    public void setVehReg(String VehReg){
      this.VehReg.set(VehReg);
    }

    public Date getExpSend(){
      return ExpSend;
    }

    public void setExpSend(Date ExpSend){
      this.ExpSend = ExpSend;
    }

    public Date getExpReturn(){
      return ExpReturn;
    }

    public void setExpReturn(Date ExpReturn){
      this.ExpReturn = ExpReturn;
    }
}
