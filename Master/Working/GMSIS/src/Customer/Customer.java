/**
 * Created by Romade on 11/02/2017.
 */

package Customer;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Customer {

    private IntegerProperty id;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty firstLineAdd;
    private StringProperty secondLineAdd;
    private StringProperty postCode;
    private StringProperty phone;
    private StringProperty email;
    private StringProperty type;


    public Customer(int id, String firstName, String lastName, String firstLineAdd, String secondLineAdd,
                    String postCode, String phone, String email, String type) {

        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.firstLineAdd = new SimpleStringProperty(firstLineAdd);
        this.secondLineAdd = new SimpleStringProperty(secondLineAdd);
        this.postCode = new SimpleStringProperty(postCode);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.type = new SimpleStringProperty(type);
    }


    public String getType(){

        return type.get();
    }

    public void setType(String type){
        this.type.set(type);

    }

    public StringProperty typeProperty(){

        return type;
    }


    public int getId(){

        return id.get();
    }

    public void setId(int id){

        this.id.set(id);

    }

    public IntegerProperty idProperty(){

        return id;
    }


    public String getFirstName(){

        return firstName.get();
    }

    public void setFirstName(String firstName){

        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty(){


        return firstName;
    }


    public String getLastName(){

        return lastName.get();
    }

    public void setLastName(String lastName){

        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty(){

        return lastName;
    }


    public String getFirstLineAdd(){

        return firstLineAdd.get();
    }

    public void setFirstLineAdd(String firstLineAdd){

        this.firstLineAdd.set(firstLineAdd);
    }

    public StringProperty firstLineAddProperty(){

        return firstLineAdd;
    }


    public String getSecondLineAdd(){

        return secondLineAdd.get();
    }

    public void setSecondLineAdd(String secondLineAdd){

        this.secondLineAdd.set(secondLineAdd);
    }

    public StringProperty secondLineAddProperty(){

        return secondLineAdd;
    }


    public String getPostCode(){

        return postCode.get();
    }

    public void setPostCode(String postCode){

        this.postCode.set(postCode);
    }

    public StringProperty postCodeProperty(){

        return postCode;
    }


    public String getPhone(){

        return phone.get();
    }

    public void setPhone(String phone){

        this.phone.set(phone);
    }

    public StringProperty phoneProperty(){

        return phone;
    }


    public String getEmail(){

        return email.get();
    }

    public void setEmail(String email){

        this.email.set(email);
    }

    public StringProperty emailProperty(){

        return email;
    }


}

