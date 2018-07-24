package JavafxGUI;

import java.sql.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author louiraj
 */
public class SRCinfo {

    private StringProperty Name, FirstLineAdd, SecondLineAdd, County, PostCode, Email;
    private IntegerProperty PhoneNo, ID;

    private SimpleIntegerProperty PartsID;
    private SimpleStringProperty PartName;
    private SimpleStringProperty Description;
    private SimpleFloatProperty SRCCost;
    private SimpleStringProperty SRC;
    private SimpleStringProperty VehReg;
    private SimpleStringProperty ExpReturn;
    private SimpleStringProperty ExpSend;

    private SimpleStringProperty VehRegV;
    private SimpleStringProperty VehType;
    private SimpleStringProperty VehModel;
    private SimpleStringProperty VehMake;
    private SimpleIntegerProperty VehEngSize;
    private SimpleStringProperty VehColour;
    private SimpleFloatProperty VehSRCCost;
    private SimpleStringProperty VehExpSend;
    private SimpleStringProperty VehExpReturn;
    private SimpleStringProperty VehSRC;

    /**
     *
     * @param Name
     * @param FirstLineAdd
     * @param SecondLineAdd
     * @param County
     * @param PostCode
     * @param PhoneNo
     * @param Email
     */
    public SRCinfo(int ID, String Name, String FirstLineAdd, String SecondLineAdd, String County, String PostCode,
            int PhoneNo, String Email) {
        this.Name = new SimpleStringProperty(Name);
        this.FirstLineAdd = new SimpleStringProperty(FirstLineAdd);
        this.SecondLineAdd = new SimpleStringProperty(SecondLineAdd);
        this.County = new SimpleStringProperty(County);
        this.PostCode = new SimpleStringProperty(PostCode);
        this.PhoneNo = new SimpleIntegerProperty(PhoneNo);
        this.Email = new SimpleStringProperty(Email);
        this.ID = new SimpleIntegerProperty(ID);

    }

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
    public SRCinfo(int PartsID, String PartName, String Description, float SRCCost, String SRC, String VehReg,
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
     * @param VehRegV
     * @param VehType
     * @param VehModel
     * @param VehMake
     * @param VehEngSize
     * @param VehColour
     * @param VehSRCCost
     * @param VehExpSend
     * @param VehExpReturn
     */
    public SRCinfo(String VehRegV, String VehType, String VehModel, String VehMake, int VehEngSize,
            String VehColour, Float VehSRCCost, String VehExpSend, String VehExpReturn, String VehSRC) {
        this.VehRegV = new SimpleStringProperty(VehRegV);
        this.VehType = new SimpleStringProperty(VehType);
        this.VehModel = new SimpleStringProperty(VehModel);
        this.VehMake = new SimpleStringProperty(VehMake);
        this.VehEngSize = new SimpleIntegerProperty(VehEngSize);
        this.VehColour = new SimpleStringProperty(VehColour);
        this.VehSRCCost = new SimpleFloatProperty(VehSRCCost);
        this.VehExpSend = new SimpleStringProperty(VehExpSend);
        this.VehExpReturn = new SimpleStringProperty(VehExpReturn);
        this.VehSRC = new SimpleStringProperty(VehSRC);

    }

    public void setVehRegV(String VehRegV) {
        this.VehRegV.set(VehRegV);
    }

    public String getVehRegV() {
        return VehRegV.get();
    }

    public void setVehType(String VehType) {
        this.VehType.set(VehType);
    }

    public String getVehTypt() {
        return VehType.get();
    }

    public void setVehModel(String VehModel) {
        this.VehModel.set(VehModel);
    }

    public String getVehModel() {
        return VehModel.get();
    }

    public void setVehMake(String VehMake) {
        this.VehMake.set(VehMake);
    }

    public String getVehMake() {
        return VehMake.get();
    }

    public void setVehEngSize(int VehEngSize) {
        this.VehEngSize.set(VehEngSize);
    }

    public int getVehEngSize() {
        return VehEngSize.get();
    }

    public void setVehColour(String VehColour) {
        this.VehColour.set(VehColour);
    }

    public String getVehColour() {
        return VehColour.get();
    }

    public void setVehSRCCost(Float VehSRCCost) {
        this.VehSRCCost.set(VehSRCCost);
    }

    public Float getVehSRCCost() {
        return VehSRCCost.get();
    }

    public void setVehExpSend(String VehExpSend) {
        this.VehExpSend.set(VehExpSend);
    }

    public String getVehExpSend() {
        return VehExpSend.get();
    }

    public void setVehExpReturn(String VehExpReturn) {
        this.VehExpReturn.set(VehExpReturn);
    }

    public String getVehExpReturn() {
        return VehExpReturn.get();
    }

    public void setVehSRC(String VehSRC) {
        this.VehSRC.set(VehSRC);
    }

    public String getVehSRC() {
        return VehSRC.get();
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

    public void setID(int ID) {
        this.ID.set(ID);
    }

    /**
     *
     * @param Name
     */
    public void setName(String Name) {
        this.Name.set(Name);
    }

    /**
     *
     * @param FirstLineAdd
     */
    public void setFirstLineAdd(String FirstLineAdd) {
        this.FirstLineAdd.set(FirstLineAdd);
    }

    /**
     *
     * @param SecondLineAdd
     */
    public void setSecondLineAdd(String SecondLineAdd) {
        this.SecondLineAdd.set(SecondLineAdd);
    }

    /**
     *
     * @param County
     */
    public void setCounty(String County) {
        this.County.set(County);
    }

    /**
     *
     * @param PostCode
     */
    public void setPostCode(String PostCode) {
        this.PostCode.set(PostCode);
    }

    /**
     *
     * @param PhoneNo
     */
    public void setPhoneNo(int PhoneNo) {
        this.PhoneNo.set(PhoneNo);
    }

    /**
     *
     * @param Email
     */
    public void setEmail(String Email) {
        this.Email.set(Email);
    }

    public int getID() {
        return ID.get();
    }

    /**
     *
     * @return
     */
    public String getName() {
        return Name.get();
    }

    /**
     *
     * @return
     */
    public String getFirstLineAdd() {
        return FirstLineAdd.get();
    }

    /**
     *
     * @return
     */
    public String getSecondLineAdd() {
        return SecondLineAdd.get();
    }

    /**
     *
     * @return
     */
    public String getCounty() {
        return County.get();
    }

    /**
     *
     * @return
     */
    public String getPostCode() {
        return PostCode.get();
    }

    /**
     *
     * @return
     */
    public int getPhoneNo() {
        return PhoneNo.get();
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return Email.get();
    }


}
