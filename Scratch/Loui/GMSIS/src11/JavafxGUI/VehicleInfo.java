package JavafxGUI;

import java.sql.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VehicleInfo {


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
public VehicleInfo(String VehRegV, String VehType, String VehModel, String VehMake, int VehEngSize,
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

}
