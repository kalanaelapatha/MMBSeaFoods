
package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Models.Commition;
import application.Models.LocalSales;
import application.Services.AccountServices;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AccountCommitionController implements Initializable{
	
	@FXML
	private AnchorPane Accounts;
	
	AnchorPane add;
	
	@FXML private TableView<Commition> tblvSales;
	
	@FXML private TableColumn<?, ?> tblcDate;
	@FXML private TableColumn<?, ?> tblcReason;
	@FXML private TableColumn<?, ?> tblcToPay;
	@FXML private TableColumn<?, ?> tblcPaid;
	
	
	private ObservableList<Commition> commitionAccountEntries = FXCollections.observableArrayList();
	
	AccountServices accountServices=new AccountServices();

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		commitionAccountEntries.clear();
		
		
		ArrayList<Commition> entryList = accountServices.getAllCommitionList();
		
		for( Commition entry : entryList ) {
			if(entry.getTo_Pay()!=0) {
				entry.setSTo_Pay("Rs."+String.format ("%2.0f", entry.getTo_Pay())+".00");
			}else {
				entry.setSTo_Pay("Rs 0.00");
			}
			if(entry.getPaid()!=0) {
				entry.setSPaid("Rs. "+String.format ("%2.0f", entry.getSPaid())+".00");
			}else {
				entry.setSPaid("Rs 0.00");
			}
			
			commitionAccountEntries.add(entry);
		} 
		
		
		tblcDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
		tblcReason.setCellValueFactory(new PropertyValueFactory<>("Reason"));
		tblcToPay.setCellValueFactory(new PropertyValueFactory<>("STo_Pay"));
		tblcPaid.setCellValueFactory(new PropertyValueFactory<>("SPaid"));

		tblvSales.setItems(commitionAccountEntries);
		
	}
	
	public void back(ActionEvent event) throws IOException {
		
		
	
		
		add=FXMLLoader.load(getClass().getResource("../Views/Accounts/Accounts.fxml"));
		setNode(add);
		
		
		
	}
	
	public void makePayment(ActionEvent event) throws IOException {
	
		add=FXMLLoader.load(getClass().getResource("../Views/Accounts/FCommitionPayment.fxml"));
		setNode(add);

	}
	
	
	void setNode(Node node) {
		
		Accounts.getChildren().clear();
		Accounts.setTopAnchor(node,0.0);
		Accounts.setRightAnchor(node, 0.0);
		Accounts.setLeftAnchor(node, 0.0);
		Accounts.setBottomAnchor(node, 0.0);
		Accounts.getChildren().addAll((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
        
	
    }

}

