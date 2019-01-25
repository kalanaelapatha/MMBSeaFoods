/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class DashboardController implements Initializable {

    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnFtrade;
    @FXML
    private JFXButton btnLTrade;
    @FXML
    private JFXButton btnVehicles;
    @FXML
    private JFXButton btnSettings;
    
    @FXML
    private JFXButton btnLogout;
    
    
    AnchorPane Home,Ftrade,Ltrade,Vehicles,Settings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Load all fxmls in a cache
        try {
             Home = FXMLLoader.load(getClass().getResource("../Views/Home/Home.fxml"));            
             Ftrade = FXMLLoader.load(getClass().getResource("../Views/Alerts.fxml"));
             Ltrade = FXMLLoader.load(getClass().getResource("../Views/Pricing.fxml"));
             Vehicles = FXMLLoader.load(getClass().getResource("../Views/Vehicles/Vehicles.fxml"));
             Settings = FXMLLoader.load(getClass().getResource("../Views/Home/Home.fxml"));
            setNode(Home);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
	//Set selected node to a content holder
    void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.setTopAnchor(node,0.0);
        holderPane.setRightAnchor(node, 0.0);
        holderPane.setLeftAnchor(node, 0.0);
        holderPane.setBottomAnchor(node, 0.0);
        holderPane.getChildren().addAll((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    private void switchHome(ActionEvent event) {
        setNode(Home);
    }

    @FXML
    private void switchFtrade(ActionEvent event) {
        setNode(Ftrade);
    }

    @FXML
    private void switchLtrade(ActionEvent event) {
        setNode(Ltrade);
    }

    @FXML
    private void switchVehicles(ActionEvent event) {
        setNode(Vehicles);
    }

    @FXML
    private void switchSettings(ActionEvent event) {
        setNode(Settings);
    }
    
    @FXML
    private void LogOut(ActionEvent event) {
       //
    }
    
    

    

}