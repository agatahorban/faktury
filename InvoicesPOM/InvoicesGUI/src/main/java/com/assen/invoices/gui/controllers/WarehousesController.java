package com.assen.invoices.gui.controllers;

import com.assen.invoices.gui.model.wrappers.WarehouseWrapper;
import com.assen.invoices.gui.services.api.IWarehouseService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javax.inject.Inject;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class WarehousesController implements Initializable {

    @FXML
    private TableView<WarehouseWrapper> warehousesTV;
    @FXML
    private TableColumn<WarehouseWrapper, String> nameTC;

    @Inject
    private IWarehouseService warehouseService;
    
    private Stage stage;
    
    private ObservableList<WarehouseWrapper> warehouses;
    
    public void setStage(Stage warehousesStage) {
        this.stage = warehousesStage;
    }
    
    @FXML
    private void addWarehouse() {

    }

    @FXML
    private void editWarehouse() {

    }

    @FXML
    private void deleteWarehouse() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warehousesTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nameTC.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    }

    public void populateWarehouses() {
        warehouses = warehouseService.getAllWarehouses();
        warehousesTV.setItems(warehouseService.getAllWarehouses());
    }
}
