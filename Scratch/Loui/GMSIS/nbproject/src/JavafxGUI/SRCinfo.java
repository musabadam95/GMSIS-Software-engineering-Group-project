package JavafxGUI;

import java.sql.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author louiraj
 */
public class SRCinfo {

	private final StringProperty Name, FirstLineAdd, SecondLineAdd, County, PostCode,Email;
	private final IntegerProperty PhoneNo, ID;


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
    public SRCinfo(int ID,String Name, String FirstLineAdd, String SecondLineAdd, String County, String PostCode,
                        int PhoneNo, String Email){
                    this.Name = new SimpleStringProperty(Name);
                    this.FirstLineAdd = new SimpleStringProperty(FirstLineAdd);
                    this.SecondLineAdd = new SimpleStringProperty(SecondLineAdd);
                    this.County = new SimpleStringProperty(County);
                    this.PostCode = new SimpleStringProperty(PostCode);
                    this.PhoneNo = new SimpleIntegerProperty(PhoneNo);
                    this.Email = new SimpleStringProperty(Email);
                    this.ID = new SimpleIntegerProperty(ID);

                }

  




		public void setID(int ID){this.ID.set(ID);}
    /**
     *
     * @param Name
     */
    public void setName(String Name){this.Name.set(Name);}

    /**
     *
     * @param FirstLineAdd
     */
    public void setFirstLineAdd(String FirstLineAdd){this.FirstLineAdd.set(FirstLineAdd);}

    /**
     *
     * @param SecondLineAdd
     */
    public void setSecondLineAdd(String SecondLineAdd){this.SecondLineAdd.set(SecondLineAdd);}

    /**
     *
     * @param County
     */
    public void setCounty(String County){this.County.set(County);}

    /**
     *
     * @param PostCode
     */
    public void setPostCode(String PostCode){this.PostCode.set(PostCode);}

    /**
     *
     * @param PhoneNo
     */
    public void setPhoneNo(int PhoneNo){this.PhoneNo.set(PhoneNo);}

    /**
     *
     * @param Email
     */
    public void setEmail(String Email){this.Email.set(Email);}


		public int getID(){return ID.get();}
    /**
     *
     * @return
     */
    public String getName(){return Name.get();}

    /**
     *
     * @return
     */
    public String getFirstLineAdd(){return FirstLineAdd.get();}

    /**
     *
     * @return
     */
    public String getSecondLineAdd(){return SecondLineAdd.get();}

    /**
     *
     * @return
     */
    public String getCounty(){return County.get();}

    /**
     *
     * @return
     */
    public String getPostCode(){return PostCode.get();}

    /**
     *
     * @return
     */
    public int getPhoneNo(){return PhoneNo.get();}

    /**
     *
     * @return
     */
    public String getEmail(){return Email.get();}

    /**
     *
     */
    public void getDeliveryDate() {

		throw new UnsupportedOperationException();
	}

    /**
     *
     */
    public void getReturnDate() {
		// TODO - implement SPCinfo.getReturnDate
		throw new UnsupportedOperationException();
	}

    /**
     *
     */
    public void getOutstanding() {
		// TODO - implement SPCinfo.getOutstanding
		throw new UnsupportedOperationException();
	}

    /**
     *
     */
    public void getCost() {
		// TODO - implement SPCinfo.getCost
		throw new UnsupportedOperationException();
	}

    /**
     *
     */
    public void getCustomer() {
		// TODO - implement SPCinfo.getCustomer
		throw new UnsupportedOperationException();
	}

    SRCinfo getInstance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
