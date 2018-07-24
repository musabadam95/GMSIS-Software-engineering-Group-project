package JavafxGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author muzab
 */
public class FXMLDocument extends Application{   
    @Override
    public void start(Stage stage) throws Exception {
       Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root, 1000, 500);
        stage.setTitle("GM-SIS Application");
                    scene.getStylesheets().add(getClass().getResource("FXMLDocument.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    } 
    public static void main(String[]args){ 
    Application.launch(); 
    }
}
