/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author muzab
 */
public class userModel {

    private String Username;
    private String Surname;
    private int userType;



    public userModel(String Username, String Surname, int userType) {
        this.Username = Username;
        this.Surname = Surname;
        this.userType = userType;

    }

    public String getUsername() {
        return Username;
    }

    /**
     * @return the Surname
     */
    public String getSurname() {
        return Surname;
    }

    /**
     * @return the userType
     */
    public int getUserType() {
        return userType;
    }
}
