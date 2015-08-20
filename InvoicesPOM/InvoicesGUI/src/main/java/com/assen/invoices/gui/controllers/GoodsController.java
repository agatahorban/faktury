package com.assen.invoices.gui.controllers;

import com.assen.invoices.dto.GoodsListDto;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
//import com.sun.jersey.api.client.WebResource;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;
//import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class GoodsController implements Initializable {
    
    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    
    @FXML
    private TextField searchTF;
    
    @FXML
    private TableView<GoodsWrapper> goodsTV;
    @FXML
    private TableColumn<GoodsWrapper, String> index1TC;
    @FXML
    private TableColumn<GoodsWrapper, String> index2TC;
    @FXML
    private TableColumn<GoodsWrapper, String> nameTC;
    @FXML
    private TableColumn<GoodsWrapper, String> unitOfMeasureTC;
    @FXML
    private TableColumn<GoodsWrapper, Number> capacityTC;
    @FXML
    private TableColumn<GoodsWrapper, String> supplierTC;
    @FXML
    private TableColumn<GoodsWrapper, Number> quantityTC;
    @FXML
    private TableColumn<GoodsWrapper, Number> priceTC;
    
    @Inject
    private RestUtil restUtil;
    
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setBingings();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    private void setBingings() {
        index1TC.setCellValueFactory(cellData -> cellData.getValue().index1Property());
        index2TC.setCellValueFactory(cellData -> cellData.getValue().index2Property());
        nameTC.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        unitOfMeasureTC.setCellValueFactory(cellData -> cellData.getValue()
                .getUnitOfMeasureWrapper().nameProperty());
        capacityTC.setCellValueFactory(cellData -> cellData.getValue()
                .getCollectivePackageWrapper().capacityProperty());
        supplierTC.setCellValueFactory(cellData -> cellData.getValue()
                .getSupplierWrapper().cutNameProperty());
        quantityTC.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        priceTC.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
    }
    
    public void populateData() {
        GoodsListDto goodsListDto = new GoodsListDto();
        Client client = restUtil.getAuthorizedClient();
        
        //WebResource webResource = client.resource(RestUtil.URL + "goods/all");
        
        ClientResponse response = RestUtil.generateRestGetResponse(client, "goods/all");
                //webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        
        if(RestUtil.responseHasErrors(response)) {
            logger.error("Error getting all goods from database. Error status: " + response.getClientResponseStatus().getStatusCode());
        } else {
            logger.info("Populating all goods from database.");
            
            goodsListDto = response.getEntity(GoodsListDto.class);
        }
        
        ObservableList<GoodsWrapper> obsGoods = FXCollections.observableArrayList();
        for (Goods goods : goodsListDto.getList()) {
            GoodsWrapper goodsWrapper = new GoodsWrapper(goods);
            obsGoods.add(goodsWrapper);
        }
        
        goodsTV.setItems(obsGoods);
    }
}
