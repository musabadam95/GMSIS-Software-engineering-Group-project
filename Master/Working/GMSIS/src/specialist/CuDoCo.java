/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package specialist;

import common.ConnectionDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author louiraj
 */
public class CuDoCo implements Initializable {

    @FXML
    private Label CusDexx;
    @FXML
    private Label firstNamexx;
    @FXML
    private Label secNamexx;
    @FXML
    private Label secineSupp;
    @FXML
    private Label postCodexx;
    @FXML
    private Button srcCancelBt;
    
    private final SRCinfo selectedSRC = SRCMainCon.selected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     

// String sql = "SELECT Customer.FirstName, Vehicles.VehType,VehModel,VehMake,EngSize,VSRCCost,VExpSend,VExpReturn,VSRC  FROM Vehicles INNER JOIN Customer ON Vehicles.VehReg = Customer.VehReg WHERE  VSRC IS NOT NULL";
    }

    @FXML
    private void firstName(MouseEvent event) {
    }

    @FXML
    private void srcCancelBt(ActionEvent event) {
    }
}
