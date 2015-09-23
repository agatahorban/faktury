package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import com.assen.invoices.gui.utils.PropertiesUtil;
import com.assen.invoices.gui.utils.RestUtil;
import com.assen.invoices.gui.validators.WarehouseValidator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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
    @FXML
    private Button clearButton;
    @FXML
    private Button addEditButton;

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
        this.stage.setOnCloseRequest((WindowEvent event) -> {
            event.consume();
            cancel();
        });
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public void setWarehouseWrapper(WarehouseWrapper warehouseWrapper) {
        this.warehouseWrapper = warehouseWrapper;
        originalWarehouse = Warehouse.copyOf(warehouseWrapper.getWarehouse());
        setBindings();
    }
    
    public void populateReferencedData() {
        if (!isEdit) {
            setWarehouseWrapper(new WarehouseWrapper(new Warehouse()));
            addEditButton.setText(props.getProperty("button.add"));
            clearButton.setVisible(true);
        } else {
            addEditButton.setText(props.getProperty("button.edit"));
            clearButton.setVisible(false);
        }
    }

    @FXML
    private void addOrEdit() {
        errorsTA.setText("");
        Client client = restUtil.getAuthorizedClient();
        if (validData()) {
            if (isEdit) {
                ClientResponse response = RestUtil.generateRestPost(client, "warehouses/update",
                        warehouseWrapper.getWarehouse());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error updating warehouse to database.");
                    errorsTA.appendText("Wystąpił błąd podczas aktualizowania obiektu do bazy.\n");
                } else {
                    warehouseWrapper.setWarehouse(response.getEntity(Warehouse.class));
                }
            } else {
                ClientResponse response = RestUtil.generateRestPost(client, "warehouses/add",
                        warehouseWrapper.getWarehouse());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error adding warehouse to database.");
                    errorsTA.appendText("Wystąpił błąd podczas dodawania nowego obiektu do bazy.\n");
                } else {
                    warehouseWrapper.setWarehouse(response.getEntity(Warehouse.class));
                    warehouses.add(new WarehouseWrapper(warehouseWrapper.getWarehouse()));
                }
            }
        }
        
        if (errorsTA.getText().equals("")) {
            stage.close();
        }
    }

    @FXML
    private void cancel() {
        errorsTA.setText("");
        if (isEdit) {
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

    private boolean validData() {
        WarehouseValidator validator = new WarehouseValidator();
        
        String errors = validator.validateData(warehouseWrapper);
        
        if (!errors.equals("")) {
            logger.info("Invalid Warehouse data: " + errors);
            errorsTA.appendText(errors);
            return false;
        }
        return true;
    }

}
