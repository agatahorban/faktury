package com.assen.invoices.gui.controllers;

import com.assen.invoices.gui.controllers.add.AddWarehouseController;
import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import com.assen.invoices.gui.services.api.IWarehouseService;
import com.assen.invoices.gui.utils.AlertUtil;
import com.assen.invoices.gui.utils.PropertiesUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class WarehousesController implements Initializable {
    
    private static final Logger logger = LoggerFactory.getLogger(WarehousesController.class);
    private final PropertiesUtil props = new PropertiesUtil("messages.properties");

    @FXML
    private TableView<WarehouseWrapper> warehousesTV;
    @FXML
    private TableColumn<WarehouseWrapper, String> nameTC;

    @Inject
    private IWarehouseService warehouseService;
    
    @Inject
    private FXMLLoader addWarehouseLoader;
    private Parent addWarehouseRoot;
    private Stage addWarehouseStage;
    private Scene addWarehouseScene;
    private AddWarehouseController addWarehouseController;
    
    private Stage stage;
    
    private ObservableList<WarehouseWrapper> warehouses;
    
    public void setStage(Stage warehousesStage) {
        this.stage = warehousesStage;
    }
    
    @FXML
    private void addWarehouse() {
        addWarehouseController.setIsEdit(false);
        addWarehouseController.populateReferencedData();
        addWarehouseController.setWarehouses(warehouses);
        
        addWarehouseStage.show();
    }

    @FXML
    private void editWarehouse() {
        List<WarehouseWrapper> warehouseToEdit = warehousesTV.getSelectionModel().getSelectedItems();
        if (!warehouseToEdit.isEmpty()) {
            addWarehouseController.setIsEdit(true);
            addWarehouseController.setWarehouseWrapper(warehouseToEdit.get(0));
            addWarehouseController.populateReferencedData();

            addWarehouseStage.showAndWait();

            warehousesTV.getColumns().get(0).setVisible(false);
            warehousesTV.getColumns().get(0).setVisible(true);
        } else {
            Alert warning = AlertUtil.createWarningAlert(props.getProperty("goods.edit.warning.title"),
                    props.getProperty("goods.edit.warning.body"));

            warning.showAndWait();
        }
    }

    @FXML
    private void deleteWarehouse() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warehousesTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameTC.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        initAddWarehouseWindow();
    }

    public void populateWarehouses() {
        warehouses = warehouseService.getAllWarehouses();
        warehousesTV.setItems(warehouses);
    }
    
    private void initAddWarehouseWindow() {
        try (InputStream addWarehouseFXML = getClass().getResourceAsStream("/fxml/AddWarehouse.fxml")) {
            addWarehouseRoot = addWarehouseLoader.load(addWarehouseFXML);

            addWarehouseStage = new Stage();
            addWarehouseStage.setTitle("Dodaj/Edytuj magazyn");
            addWarehouseStage.initOwner(stage);
            addWarehouseStage.initModality(Modality.APPLICATION_MODAL);
            addWarehouseStage.setResizable(false);

            addWarehouseScene = new Scene(addWarehouseRoot);
            addWarehouseStage.setScene(addWarehouseScene);

            addWarehouseController = addWarehouseLoader.getController();
            addWarehouseController.setStage(addWarehouseStage);
        } catch (IOException ex) {
            logger.error("Error reading AddWarehouse.fxml file.");
            logger.error(ex.getMessage());
        }
    }
}
