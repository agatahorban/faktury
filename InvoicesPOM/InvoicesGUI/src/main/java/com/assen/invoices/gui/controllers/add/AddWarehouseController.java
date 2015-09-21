package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import com.assen.invoices.gui.utils.PropertiesUtil;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class AddWarehouseController implements Initializable {
    
    private static final Logger logger = LoggerFactory.getLogger(AddWarehouseController.class);
    private final PropertiesUtil props = new PropertiesUtil("messages.properties");
    
    @FXML
    private TextField nameTF;
    @FXML
    private TextArea errorsTA;
    
    @Inject
    private RestUtil restUtil;
    
    private ObservableList<WarehouseWrapper> warehouses;
    
    private Stage stage;
    
    private boolean isEdit;
    private WarehouseWrapper warehouseWrapper;
    private Warehouse originalWarehouse;

    public ObservableList<WarehouseWrapper> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(ObservableList<WarehouseWrapper> warehouses) {
        this.warehouses = warehouses;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void setWarehouseWrapper(WarehouseWrapper warehouseWrapper) {
        this.warehouseWrapper = warehouseWrapper;
        originalWarehouse = Warehouse.copyOf(warehouseWrapper.getWarehouse());
        setBindings();
    }
    
    @FXML
    private void addOrEdit() {
        errorsTA.setText("");
        Client client = restUtil.getAuthorizedClient();
        //TODO validate
        if(isEdit) {
            //TODO edit
        } else {
            ClientResponse response = RestUtil.generateRestPost(client, "warehouses/add", 
                    warehouseWrapper.getWarehouse());
            if(RestUtil.responseHasErrors(response)) {
                logger.error("Error adding warehouse to database.");
            } else {
                warehouseWrapper.setWarehouse(response.getEntity(Warehouse.class));
                warehouses.add(new WarehouseWrapper(warehouseWrapper.getWarehouse()));
            }
        }
    }
    
    @FXML
    private void cancel() {
        errorsTA.setText("");
        if(isEdit) {
            warehouseWrapper.setWarehouse(originalWarehouse);
        }
        stage.close();
    }
    
    @FXML
    private void clear() {
        errorsTA.setText("");
        warehouseWrapper.setWarehouse(new Warehouse());
        setBindings();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isEdit = false;
    }    

    private void setBindings() {
        nameTF.textProperty().bindBidirectional(warehouseWrapper.nameProperty());
    }
    
}
