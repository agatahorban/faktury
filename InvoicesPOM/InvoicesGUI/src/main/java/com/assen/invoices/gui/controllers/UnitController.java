package com.assen.invoices.gui.controllers;

import com.assen.invoices.gui.controllers.add.AddUnitController;
import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import com.assen.invoices.gui.services.api.IUnitOfMeasureService;
import com.assen.invoices.gui.utils.AlertUtil;
import com.assen.invoices.gui.utils.PropertiesUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
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
    private final PropertiesUtil props = new PropertiesUtil("messages.properties");

    @Inject
    private FXMLLoader addUnitsLoader;

    private Parent addUnitsRoot;
    private Stage addUnitsStage;
    private Scene addUnitsScene;
    private AddUnitController addUnitController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        unitsTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        unitsTV.setContextMenu(createTableViewContextMenu());
        setBingings();
        initAddUnitsWindow();

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

    @FXML
    private void deleteRecords() {
        List<UnitOfMeasureWrapper> unitsToDelete = unitsTV.getSelectionModel().getSelectedItems();
        Alert deleteDialog = AlertUtil
                .createConfirmationAlert(props.getProperty("units.delete.confirmation.title"),
                        MessageFormat.format(props.getProperty("units.delete.confirmation.body"),
                                unitsToDelete.size()));

        Optional<ButtonType> result = deleteDialog.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            boolean deleteSuccess = unitOfMeasureService.deleteData(unitsToDelete);

            if (!deleteSuccess) {
                Alert error = AlertUtil.createErrorAlert(props.getProperty("units.delete.error.title"),
                        props.getProperty("units.delete.error.body"));

                error.showAndWait();
            } else {
                obsUnits.removeAll(unitsToDelete);
            }
        }
    }

    @FXML
    private void addNewRecord() {
        addUnitController.setIsEdit(false);
        addUnitController.populateReferencedData();
        addUnitController.setObsUnits(obsUnits);

        addUnitsStage.show();
    }

    @FXML
    private void editUnits() {
        List<UnitOfMeasureWrapper> unitsToEdit = unitsTV.getSelectionModel().getSelectedItems();
        if (!unitsToEdit.isEmpty()) {
            addUnitController.setIsEdit(true);
            addUnitController.setUnit(unitsToEdit.get(0));
            addUnitController.populateReferencedData();

            addUnitsStage.showAndWait();

            unitsTV.getColumns().get(0).setVisible(false);
            unitsTV.getColumns().get(0).setVisible(true);
        } else {
            Alert warning = AlertUtil.createWarningAlert(props.getProperty("units.edit.warning.title"),
                    props.getProperty("units.edit.warning.body"));

            warning.showAndWait();
        }
    }

    private void initAddUnitsWindow() {
        try (InputStream addUnitsFXML = getClass().getResourceAsStream("/fxml/AddUnitOfMeasure.fxml")) {
            addUnitsRoot = addUnitsLoader.load(addUnitsFXML);

            addUnitsStage = new Stage();
            addUnitsStage.setTitle(props.getProperty("units.add.window.title"));
            addUnitsStage.initOwner(stage);
            addUnitsStage.initModality(Modality.APPLICATION_MODAL);
            addUnitsStage.setResizable(false);

            addUnitsScene = new Scene(addUnitsRoot);
            addUnitsStage.setScene(addUnitsScene);

            addUnitController = addUnitsLoader.getController();
            addUnitController.setStage(addUnitsStage);
        } catch (IOException ex) {
            logger.error("Error reading AddUnitOfMeasure.fxml file.");
            logger.error(ex.getMessage());
        }
    }

    private ContextMenu createTableViewContextMenu() {
        MenuItem editMenu = new MenuItem(props.getProperty("units.tableView.contextMenu.edit"));
        editMenu.setOnAction((event) -> editUnits());

        MenuItem deleteMenu = new MenuItem(props.getProperty("units.tableView.contextMenu.delete"));
        deleteMenu.setOnAction((event) -> deleteRecords());

        return new ContextMenu(editMenu, deleteMenu);
    }

    @FXML
    private void filterUnits() {
        System.out.println("Wszedlem do filter");
        obsUnits.clear();
        obsUnits.addAll(unitOfMeasureService
                .filterByShortcut(searchTF.getText()));
        if (obsUnits.isEmpty()) {
            Alert warning = AlertUtil.createWarningAlert(props.getProperty("units.filter.warning.title"),
                    props.getProperty("units.filter.warning.body"));
            warning.showAndWait();
        }
    }

    @FXML
    private void clearFilter() {
        searchTF.setText("");
        populateData();
    }
}
