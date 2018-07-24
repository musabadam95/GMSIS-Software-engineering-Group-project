/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavafxGUI;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author muzab
 */
public class User {
    private SimpleIntegerProperty userID;
    private SimpleStringProperty Name;
    private SimpleStringProperty Surname;
    private SimpleIntegerProperty userType;
    
    
      public User(int userID, String Username, String Surname, int userType) {
        this.Name = new SimpleStringProperty(Username);
        this.userID = new SimpleIntegerProperty(userID);
        this.Surname = new SimpleStringProperty(Surname);
        this.userType = new SimpleIntegerProperty(userType);
    }
      
      
      public int getUserID() {
        return userID.get();
    }

    public void setUserID(int uID) {
        userID.set(uID);
    }

    public String getName() {
        return Name.get();
    }

    public void setName(String name) {
        Name.set(name);
    }

    public String getSurname() {
        return Surname.get();
    }

    public void setSurname(String LastName) {
        this.Surname.set(LastName);
    }

    public int getUserType() {
        return userType.get();
    }

    public void setUserType(int usertype) {
        this.userType.set(usertype);
    }
}
