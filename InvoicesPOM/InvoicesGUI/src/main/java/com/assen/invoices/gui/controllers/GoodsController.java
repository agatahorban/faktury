package com.assen.invoices.gui.controllers;

import com.assen.invoices.gui.controllers.add.AddGoodsController;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.services.api.IGoodsService;
import com.assen.invoices.gui.utils.AlertUtil;
import com.assen.invoices.gui.utils.PropertiesUtil;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
public class GoodsController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
    private final PropertiesUtil props = new PropertiesUtil("messages.properties");

    @FXML
    private TextField searchTF;
    @FXML
    private TreeView<String> contractorGroupTrV;

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

    private ObservableList<GoodsWrapper> obsGoods;

    @Inject
    private IGoodsService goodsService;
    
    private Stage stage;

    @Inject
    private FXMLLoader addGoodsLoader;

    private Parent addGoodsRoot;
    private Stage addGoodsStage;
    private Scene addGoodsScene;
    private AddGoodsController addGoodsController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        goodsTV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        goodsTV.setContextMenu(createTableViewContextMenu());
        setBingings();
        initAddGoodsWindow();

        contractorGroupTrV.getSelectionModel()
                .selectedItemProperty().addListener(new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue,
                            Object newValue) {
                        if (((TreeItem<String>) newValue).getParent() != null) {
                            obsGoods = goodsService.filterByGroupOrContractor(newValue);
                        } else {
                            populateTreeView();
                        }
                    }
                });
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void addNewRecord() {
        addGoodsController.setIsEdit(false);
        addGoodsController.populateReferencedData();
        addGoodsController.setObsGoods(obsGoods);

        addGoodsStage.show();
    }

    @FXML
    private void editGoods() {
        List<GoodsWrapper> goodsToEdit = goodsTV.getSelectionModel().getSelectedItems();
        if (!goodsToEdit.isEmpty()) {
            addGoodsController.setIsEdit(true);
            addGoodsController.setGoods(goodsToEdit.get(0));
            addGoodsController.populateReferencedData();

            addGoodsStage.showAndWait();

            goodsTV.getColumns().get(0).setVisible(false);
            goodsTV.getColumns().get(0).setVisible(true);
        } else {
            Alert warning = AlertUtil.createWarningAlert(props.getProperty("goods.edit.warning.title"),
                    props.getProperty("goods.edit.warning.body"));

            warning.showAndWait();
        }
    }

    @FXML
    private void deleteRecords() {
        List<GoodsWrapper> goodsToDelete = goodsTV.getSelectionModel().getSelectedItems();
        Alert deleteDialog = AlertUtil
                .createConfirmationAlert(props.getProperty("goods.delete.confirmation.title"),
                        MessageFormat.format(props.getProperty("goods.delete.confirmation.body"), 
                                goodsToDelete.size()));

        Optional<ButtonType> result = deleteDialog.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            boolean deleteSuccess = goodsService.deleteData(goodsToDelete);

            if (!deleteSuccess) {
                Alert error = AlertUtil.createErrorAlert(props.getProperty("goods.delete.error.title"),
                        props.getProperty("goods.delete.error.body"));

                error.showAndWait();
            } else {
                obsGoods.removeAll(goodsToDelete);
            }
        }
    }

    @FXML
    private void filterGoods() {
        obsGoods.clear();
        obsGoods.addAll(goodsService
                .filterByIndex1(searchTF.getText()));
        if (obsGoods.isEmpty()) {
            Alert warning = AlertUtil.createWarningAlert(props.getProperty("goods.filter.warning.title"),
                    props.getProperty("goods.filter.warning.body"));
            warning.showAndWait();
        }
    }

    @FXML
    private void clearFilter() {
        searchTF.setText("");
        populateData();
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
        obsGoods = goodsService.populateAllGoods();
        goodsTV.setItems(obsGoods);
        populateTreeView();
    }

    private void populateTreeView() {
        contractorGroupTrV.setRoot(goodsService.generateRootView());
    }

    private void initAddGoodsWindow() {
        try (InputStream addGoodsFXML = getClass().getResourceAsStream("/fxml/AddGoods.fxml")) {
            addGoodsRoot = addGoodsLoader.load(addGoodsFXML);

            addGoodsStage = new Stage();
            addGoodsStage.setTitle(props.getProperty("goods.add.window.title"));
            addGoodsStage.initOwner(stage);
            addGoodsStage.initModality(Modality.APPLICATION_MODAL);
            addGoodsStage.setResizable(false);

            addGoodsScene = new Scene(addGoodsRoot);
            addGoodsStage.setScene(addGoodsScene);

            addGoodsController = addGoodsLoader.getController();
            addGoodsController.setStage(addGoodsStage);
        } catch (IOException ex) {
            logger.error("Error reading AddGoods.fxml file.");
            logger.error(ex.getMessage());
        }
    }
    
    private ContextMenu createTableViewContextMenu() {
        MenuItem editMenu = new MenuItem(props.getProperty("goods.tableView.contextMenu.edit"));
        editMenu.setOnAction((event) -> editGoods());
        
        MenuItem deleteMenu = new MenuItem(props.getProperty("goods.tableView.contextMenu.delete"));
        deleteMenu.setOnAction((event) -> deleteRecords());
        
        return new ContextMenu(editMenu, deleteMenu);
    }
}
