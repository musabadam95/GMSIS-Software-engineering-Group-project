/**
 * Created by Romade on 06/03/2017.
 */

package Customer;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TableView;
import java.sql.Connection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import java.util.function.Predicate;


import java.io.IOException;

public class Main extends Application{

    public static Stage mainStage;

    public static void main(String[] args){

        Application.launch(args);
    }


   // public void buildData(){



   // }


    @Override
    public void start (Stage primaryStage) throws Exception{


        mainStage = primaryStage;
        //FXMLLoader loader - new FXMLLoader();
        //loader.setLocation(getClass().getResource())

        Parent p = FXMLLoader.load(getClass().getResource("CustomerGUI.fxml"));
        Scene scene = new Scene(p);
        primaryStage.setTitle("Customer Account");
        primaryStage.setScene(scene);
        //stage.show();
        primaryStage.show();


        //this.primaryStage = primaryStage;

        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(Main.class.getResource("CustomerGUI.fxml"));



            //AnchorPane customerOverview = (AnchorPane) loader.load();

           // CustomerOverviewController controller = loader.getController();
            //controller.setMain(this);




                //loader.getRoot();

        //Stage stage = new Stage();
        //primaryStage.setScene(new Scene(p));





    }


//    public  searchField(){
//
//        FilteredList<Customer> filteredData = new FileteredList<customerData, e-> true);
//        searchField.setOnKeyReleased(e ->{
//
//            searchField.textProperty().addListener(((observable, oldValue, newValue) ->
//            {
//                filteredData.setPredicate((Predicate<? super Customer>) customer ->
//
//                {
//                    if (newValue == null || newValue.isEmpty()){
//                        return true;
//                    }
//                    String lowerCaseFilter = newValue.toLowerCase();
//                    if (Customer.getFirstName().contains(lowerCaseFilter)){
//                        return true;
//                    }
//                    else if (Customer.getLastName().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }
//                    else if (Customer.getId().contains(newValue)){
//                        return true;
//                    }
//
//                        return false;
//
//                });
//            }));
//            SortedList<Customer>sortedData = new SortedList<>(filteredData);
//            sortedData.comparatorProperty().bind(customerTable.comparatorProperty());
//            customerTable.setItems(sortedData);
//        });
//
//
//    }

    /**public void showCustomer(){

       ** try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("CustomerGUI.fxml"));
            AnchorPane customerOverview = (AnchorPane) loader.load();

            CustomerOverviewController controller = loader.getController();
            controller.setMain(this);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }**/
}
