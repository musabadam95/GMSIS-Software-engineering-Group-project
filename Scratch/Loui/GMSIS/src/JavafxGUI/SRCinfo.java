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
    private SimpleIntegerProperty EngSize;
    private SimpleStringProperty Colour;
    private SimpleFloatProperty VSRCCost;
    private SimpleStringProperty VExpSend;
    private SimpleStringProperty VExpReturn;
    private SimpleStringProperty VSRC;
    private SimpleStringProperty FirstName;

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
    public SRCinfo(int PartsID, String PartName, String Description, Float SRCCost, String SRC, String VehReg,
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
     * @param EngSize
     * @param Colour
     * @param VSRCCost
     * @param VExpSend
     * @param VExpReturn
     */
    public SRCinfo(String FirstName, String VehType, String VehModel, String VehMake, int EngSize,
            float VSRCCost, String VExpSend, String VExpReturn, String VSRC) {
                this.FirstName = new SimpleStringProperty(FirstName);

        this.VehType = new SimpleStringProperty(VehType);
        this.VehModel = new SimpleStringProperty(VehModel);
        this.VehMake = new SimpleStringProperty(VehMake);
        this.EngSize = new SimpleIntegerProperty(EngSize);
        this.VSRCCost = new SimpleFloatProperty(VSRCCost);
        this.VExpSend = new SimpleStringProperty(VExpSend);
        this.VExpReturn = new SimpleStringProperty(VExpReturn);
        this.VSRC = new SimpleStringProperty(VSRC);

    }

    
    public String getVehRegV(){
    return VehRegV.get();
    }
    public String getVehType() {
        return VehType.get();
    }

    public String getVehModel() {
        return VehModel.get();
    }

    public String getVehMake() {
        return VehMake.get();
    }

    public int getEngSize() {
        return EngSize.get();
    }

    public String getColour() {
        return Colour.get();
    }

    public float getVSRCCost() {
        return VSRCCost.get();
    }

    public String getVExpSend() {
        return VExpSend.get();
    }

    public String getVExpReturn() {
        return VExpReturn.get();
    }

    public String getVSRC() {
        return VSRC.get();
    }
    
    public String getFirstName() {
        return FirstName.get();
    }



    public void setVehRegV(String VehRegV) {
        this.VehRegV.set(VehRegV);
    }

    public void setVehType(String VehType) {
        this.VehType.set(VehType);
    }

    public void setVehModel(String VehModel) {
        this.VehModel.set(VehModel);
    }

    public void setVehMake(String VehMake) {
        this.VehMake.set(VehMake);
    }

    public void setEngSize(int EngSize) {
        this.EngSize.set(EngSize);
    }

    public void setColour(String Colour) {
        this.Colour.set(Colour);
    }

    public void setVSRCCost(float VSRCCost) {
        this.VSRCCost.set(VSRCCost);
    }

    public void setVExpSend(String VExpSend) {
        this.VExpSend.set(VExpSend);
    }

    public void setVExpReturn(String VExpReturn) {
        this.VExpReturn.set(VExpReturn);
    }

    public void setVSRC(String VSRC) {
        this.VSRC.set(VSRC);
    }
    
     public void setFirstName(String FirstName) {
        this.FirstName.set(FirstName);
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

    public float getSRCCost() {
        return SRCCost.get();
    }

    public void setSRCCost(float SRCCost) {
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

    public String getVehReg(){
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

    Object getPSRC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Object getVegReg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
