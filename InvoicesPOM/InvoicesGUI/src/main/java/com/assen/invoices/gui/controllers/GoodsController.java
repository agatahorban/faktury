package com.assen.invoices.gui.controllers;

import com.assen.invoices.dto.GoodsListDto;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.gui.controllers.add.AddGoodsController;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    private ObservableList<GoodsWrapper> obsGoods;

    @Inject
    private RestUtil restUtil;

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
        setBingings();
        initAddGoodsWindow();
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
            Alert warning = new Alert(Alert.AlertType.WARNING);
            warning.setTitle("Brak zaznaczonego rekordu");
            warning.setHeaderText(null);
            warning.setContentText("Proszę wybrać towar do edycji.");

            warning.showAndWait();
        }
    }

    @FXML
    private void deleteRecords() {
        List<GoodsWrapper> goodsToDelete = goodsTV.getSelectionModel().getSelectedItems();
        Alert deleteDialog = new Alert(Alert.AlertType.CONFIRMATION);
        deleteDialog.setTitle("Potwierdzenie usunięcia rekordów");
        deleteDialog.setHeaderText(null);
        deleteDialog.setContentText("Czy napewno chcesz usunąć następującą liczbę rekordów: "
                + goodsToDelete.size());

        Optional<ButtonType> result = deleteDialog.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            logger.info("Deleting " + goodsToDelete.size() + " goods records.");
            List<Goods> goodsDelete = new ArrayList<>();
            goodsToDelete.stream().parallel().forEach((goodsToDelete1) -> {
                goodsDelete.add(goodsToDelete1.getGoods());
            });
            
            Client client = restUtil.getAuthorizedClient();
            
            GoodsListDto entitiesToDelete = new GoodsListDto(goodsDelete);
            ClientResponse response = RestUtil.generateRestPost(client, 
                    "goods/delete", entitiesToDelete);
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error deleting goods from database.");
                
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Niepowodzenie usuwania");
                error.setHeaderText(null);
                error.setContentText("Wystąpił błąd podczas próby usunięcia rekordów z bazy.");
            } else {
                obsGoods.removeAll(goodsToDelete);
            }
        }
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

        ClientResponse response = RestUtil.generateRestGet(client, "goods/all");

        if (RestUtil.responseHasErrors(response)) {
            logger.error("Error getting all goods from database. Error status: " + response.getClientResponseStatus().getStatusCode());
        } else {
            logger.info("Populating all goods from database.");

            goodsListDto = response.getEntity(GoodsListDto.class);
        }

        obsGoods = FXCollections.observableArrayList();
        for (Goods goods : goodsListDto.getList()) {
            GoodsWrapper goodsWrapper = new GoodsWrapper(goods);
            obsGoods.add(goodsWrapper);
        }

        goodsTV.setItems(obsGoods);
    }

    private void initAddGoodsWindow() {
        try (InputStream addGoodsFXML = getClass().getResourceAsStream("/fxml/AddGoods.fxml")) {
            addGoodsRoot = addGoodsLoader.load(addGoodsFXML);

            addGoodsStage = new Stage();
            addGoodsStage.setTitle("Faktury");
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
}
