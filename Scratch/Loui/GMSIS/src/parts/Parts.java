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

    /**
     * @return the dateInstalled
     */
    public Date getDateInstalled() {
        return dateInstalled;
    }

    /**
     * @param dateInstalled the dateInstalled to set
     */
    public void setDateInstalled(Date dateInstalled) {
        this.dateInstalled = dateInstalled;
    }

    /**
     * @return the warrantyEnd
     */
    public Date getWarrantyEnd() {
        return warrantyEnd;
    }

    private final SimpleStringProperty partName;
    private final SimpleIntegerProperty stockam;
    private final SimpleIntegerProperty cost;

    private int partID;
    private String partNam;
    private String partDesc;
    private int partCost;
    private Date dateInstalled;
    private Date warrantyEnd;
    private boolean SPC;
    
    Parts(String partName, int stock, int cost) {
        this.partName = new SimpleStringProperty(partName);
        this.stockam = new SimpleIntegerProperty(stock);
        this.cost = new SimpleIntegerProperty(cost);
    }

    public Parts(int partID, String partNam, String partDesc, int partCost,boolean SPC) {
        this.partNam = partNam;
        this.partID = partID;
        this.partDesc = partDesc;
        this.partCost = partCost;
        this.SPC=SPC;
        Calendar today = Calendar.getInstance();
        this.dateInstalled = (Date) today.getTime();
        today.add(Calendar.YEAR, 1);
        this.warrantyEnd = (Date) today.getTime();
        //initiliase the rest to null
        this.partName = null;
        this.stockam = null;
        this.cost = null;
    }

       public Parts(int partID, String partNam, Date dateInstalled,Date warrantyEnd, int partCost) {
        this.partNam = partNam;
        this.partID = partID;
        this.partDesc = partDesc;
        this.partCost = partCost;
        this.SPC=SPC;
        Calendar today = Calendar.getInstance();
        this.dateInstalled = (Date) today.getTime();
        today.add(Calendar.YEAR, 1);
        this.warrantyEnd = (Date) today.getTime();
        //initiliase the rest to null
        this.partName = null;
        this.stockam = null;
        this.cost = null;
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

    /**
     * @return the partID
     */
    public int getPartID() {
        return partID;
    }

    /**
     * @return the partNam
     */
    public String getPartNam() {
        return partNam;
    }

    /**
     * @return the partDesc
     */
    public String getPartDesc() {
        return partDesc;
    }

    /**
     * @return the partCost
     */
    public int getPartCost() {
        return partCost;
    }
}
