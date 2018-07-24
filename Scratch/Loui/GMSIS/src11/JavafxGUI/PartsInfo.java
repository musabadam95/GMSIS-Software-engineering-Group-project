package JavafxGUI;

import java.sql.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PartsInfo {

    private SimpleIntegerProperty PartsID;
    private SimpleStringProperty PartName;
    private SimpleStringProperty Description;
    private SimpleFloatProperty SRCCost;
    private SimpleStringProperty SRC;
    private SimpleStringProperty VehReg;
    private SimpleStringProperty ExpReturn;
    private SimpleStringProperty ExpSend;

    /**
     *
     * @param PartsID
     * @param PartName
     * @param Description
     * @param SRCCost
     * @param SRC
     * @param VehReg
     * @param ExpSend
     * @param ExpReturn
     */
    public PartsInfo(int PartsID, String PartName, String Description, float SRCCost, String SRC, String VehReg,
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

    /**
     *
     * @return
     */
    public String getPartName() {
        return PartName.get();
    }

    public void setPartName(String PartName) {
        this.PartName.set(PartName);
    }

    public Float getSRCCost() {
        return SRCCost.get();
    }

    public void setSRCCost(Float SRCCost) {
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
