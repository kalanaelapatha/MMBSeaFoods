package application.Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import application.Models.LFish_stock;
import application.Models.LocalBoat;
import application.Models.Local_Fish_types;
import application.Services.LFish_stockService;
import application.Services.LocalBoatService;
import application.Services.Local_Fish_typesServices;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.fxml.Initializable;

public class AddLocalStockController implements Initializable  {  //

	 @FXML
	    private AnchorPane NewStocks;

	    @FXML
	    private JFXTextField Lfweight;

	    @FXML
	    private JFXComboBox<String> cmbLftype;
	    
	    @FXML
	    private JFXComboBox<String> cmbLsBoat;
	    
	    // TableView
	    
	    @FXML
	    private TableView<LFish_stock> clmFishTable;

	    @FXML
	    private TableColumn<?, ?> clmfishtype;

	    @FXML
	    private TableColumn<?, ?> clmTotalWeight;

	    @FXML
	    private TableColumn<?, ?> clmTotalPrice;
	    
	    
	    

	    @FXML
	    private JFXButton AddLFish;

	    
	    AnchorPane add;
    
    ObservableList<String> LocalFishTypeList =FXCollections.observableArrayList();
    ObservableList<String> LocalBoatList =FXCollections.observableArrayList();
    
    Local_Fish_typesServices serviceC= new Local_Fish_typesServices();
    LFish_stockService serviceB= new LFish_stockService();
    LocalBoatService serviceD = new LocalBoatService();
    
    ArrayList<Local_Fish_types> local_fishtype=null;
    ArrayList<LocalBoat> local_boat=null;
    
    ObservableList<LFish_stock> local_fishStock = FXCollections.observableArrayList();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		local_fishStock.clear();
		//Load DataS to the Combo Boxs
		try {
			local_fishtype=serviceC.getLocalfishTypes();
			
			for(Local_Fish_types Ltyp:local_fishtype) {
				
				
				LocalFishTypeList.add(Ltyp.getName());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			local_boat= serviceD.getLocalBoat();
			
			for(LocalBoat lboat:local_boat) {
				
				LocalBoatList.add(lboat.getBoatNameorNumber());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		cmbLsBoat.setItems(LocalBoatList);
		cmbLftype.setItems(LocalFishTypeList);
		// end the Data to Combo Box
		
		
		clmfishtype.setCellValueFactory(new PropertyValueFactory<>("FishName"));
		clmTotalWeight.setCellValueFactory(new PropertyValueFactory<>("Total_Weight"));
		clmTotalPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		clmFishTable.setItems(local_fishStock);
		
	
		
	}
	
	
	void setNode(Node node) {
		
		 NewStocks.getChildren().clear();
		 NewStocks.setTopAnchor(node,0.0);
		 NewStocks.setRightAnchor(node, 0.0);
		 NewStocks.setLeftAnchor(node, 0.0);
		 NewStocks.setBottomAnchor(node, 0.0);
		 NewStocks.getChildren().addAll((Node) node);

	        FadeTransition ft = new FadeTransition(Duration.millis(1500));
	        ft.setNode(node);
	        ft.setFromValue(0.1);
	        ft.setToValue(1);
	        ft.setCycleCount(1);
	        ft.setAutoReverse(false);
	        ft.play();
	        
		
	    }


	
	
	public void AddLocalFishActions(ActionEvent event)throws SQLException {
		
		
		LFish_stock local_Fish = new LFish_stock();
		Local_Fish_types types =serviceC.getLocalfishTypes(cmbLftype.getValue());
		
		if(cmbLftype.getSelectionModel().getSelectedItem() != null && Lfweight.getText() != null) {
		String Local_Fish_Type=	cmbLftype.getValue();
		
			local_Fish.setFishName(cmbLftype.getValue());
			local_Fish.setTotal_Weight(Double.parseDouble(Lfweight.getText()));
			local_Fish.setPrice(local_Fish.getTotal_Weight()*types.getPrice());
		
			local_fishStock.add(local_Fish);
			
		}
		
		

    }
	
	public void AddFinalizeStock(ActionEvent event)throws SQLException {
		
		LFish_stock localFStock= new LFish_stock();
		
		localFStock.getFish_Type();
		localFStock.getPrice();
		localFStock.getTotal_Weight();
		System.out.println(localFStock.getFish_Type());
		System.out.println(localFStock.getPrice());
		System.out.println(	localFStock.getTotal_Weight());
		
		serviceB.addFish_Stock(localFStock);
		
		
		
		
		
	}
	
	
	  @FXML
	   public void back(ActionEvent event)throws IOException {
		  
		    add=FXMLLoader.load(getClass().getResource("../Views/Ltrade/LStocks.fxml"));
	        setNode(add);

	    }

 

}

