package com.assen.invoices.gui.controllers;

import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import com.assen.invoices.gui.services.api.IUnitOfMeasureService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author horbana
 */
public class UnitController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(UnitController.class);

    @FXML
    private TextField searchTF;

    @FXML
    private TableView<UnitOfMeasureWrapper> unitsTV;
    
    @FXML
    private TableColumn<UnitOfMeasureWrapper, String> nameTC;
    
    @FXML
    private TableColumn<UnitOfMeasureWrapper, String> shortcutTC;
    
    private ObservableList<UnitOfMeasureWrapper> obsUnits;
    
    @Inject
    private IUnitOfMeasureService unitOfMeasureService;
    
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        unitsTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setBingings();
      
    }

    private void setBingings() {
         nameTC.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
         shortcutTC.setCellValueFactory(cellData -> cellData.getValue().shortcutProperty());
    }

     public void populateData() {
        obsUnits = unitOfMeasureService.populateAllUnits();
        unitsTV.setItems(obsUnits);
    }

     public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
