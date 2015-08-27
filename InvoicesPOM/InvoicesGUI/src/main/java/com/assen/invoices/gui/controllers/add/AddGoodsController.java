package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.entities.Goods;
import com.assen.invoices.gui.controllers.GoodsController;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.services.api.IGoodsService;
import com.assen.invoices.gui.services.api.IGoodsService.DataType;
import com.assen.invoices.gui.utils.RestUtil;
import com.assen.invoices.gui.validators.GoodsValidator;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Arek
 */
public class AddGoodsController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @FXML
    private TextField index1TF;
    @FXML
    private TextField index2TF;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField quantityTF;
    @FXML
    private TextField priceTF;
    @FXML
    private CheckBox priceHigherChB;
    @FXML
    private ChoiceBox contractorsCB;
    @FXML
    private ChoiceBox groupCB;
    @FXML
    private ChoiceBox collectivePackageCB;
    @FXML
    private ChoiceBox unitOfMeasureCB;
    @FXML
    private ChoiceBox vatRateCB;
    @FXML
    private TextArea errorsTA;

    @FXML
    private Button addEditButton;
    @FXML
    private Button clearButton;

    private Stage stage;

    private ObservableList<GoodsWrapper> obsGoods;

    private boolean isEdit;
    private GoodsWrapper goods;
    private Goods originalGoods;

    @Inject
    private RestUtil restUtil;

    @Inject
    private IGoodsService goodsService;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public ObservableList<GoodsWrapper> getObsGoods() {
        return obsGoods;
    }

    public void setObsGoods(ObservableList<GoodsWrapper> obsGoods) {
        this.obsGoods = obsGoods;
    }

    public void setGoods(GoodsWrapper goods) {
        this.goods = goods;
        originalGoods = Goods.copyOf(goods.getGoods());
        //this.originalGoods = new GoodsWrapper(copiedGoods);
        setBindings();
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    @FXML
    private void addOrEdit() {
        errorsTA.setText("");
        Client client = restUtil.getAuthorizedClient();
        if (validData()) {
            if (isEdit) {
                ClientResponse response = RestUtil.generateRestPost(client, "goods/update", goods.getGoods());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error updating goods to database.");
                    errorsTA.appendText("Wystąpił błąd podczas aktualizowania nowego obiektu do bazy.\n");
                } else {
                    goods.setGoods(response.getEntity(Goods.class));
                }
            } else {
                ClientResponse response = RestUtil.generateRestPost(client, "goods/add", goods.getGoods());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error adding goods to database.");
                    errorsTA.appendText("Wystąpił błąd podczas dodawania nowego obiektu do bazy.\n");
                } else {
                    goods.setGoods(response.getEntity(Goods.class));
                    obsGoods.add(new GoodsWrapper(goods.getGoods()));
                }
            }
        }

        if (errorsTA.getText().equals("")) {
            stage.close();
        }
    }

    @FXML
    private void clear() {
        goods.setGoods(new Goods());
        setBindings();
        setChoiceBoxesValues();
    }

    @FXML
    private void cancel() {
        errorsTA.setText("");
        if (isEdit) {
            goods.setGoods(originalGoods);
        }
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isEdit = false;
    }

    public void populateReferencedData() {
        if (!isEdit) {
            setGoods(new GoodsWrapper(new Goods()));
            addEditButton.setText("Dodaj");
            clearButton.setVisible(true);
        } else {
            addEditButton.setText("Edytuj");
            clearButton.setVisible(false);
        }

        goodsService.populateDataFromServer();
        setChoiceBoxesValues();
    }

    private void setBindings() {
        index1TF.textProperty().bindBidirectional(goods.index1Property());
        index2TF.textProperty().bindBidirectional(goods.index2Property());
        nameTF.textProperty().bindBidirectional(goods.nameProperty());
        quantityTF.textProperty().bindBidirectional(goods.quantityProperty(), new NumberStringConverter());
        priceTF.textProperty().bindBidirectional(goods.priceProperty(), new NumberStringConverter());
        priceHigherChB.selectedProperty().bindBidirectional(goods.priceHigherProperty());
    }

    private boolean validData() {
        GoodsValidator.GoodsValidationData validationData
                = new GoodsValidator.GoodsValidationData(
                        (String) contractorsCB.getSelectionModel().getSelectedItem(),
                        (String) collectivePackageCB.getSelectionModel().getSelectedItem(),
                        (String) groupCB.getSelectionModel().getSelectedItem(),
                        (String) unitOfMeasureCB.getSelectionModel().getSelectedItem(),
                        (String) vatRateCB.getSelectionModel().getSelectedItem());
        String errors = goodsService.validData(goods, validationData);

        if (!errors.equals("")) {
            logger.info("Invalid Goods data: " + errors);
            errorsTA.appendText(errors);
            return false;
        }
        return true;
    }

    private void setChoiceBoxesValues() {
        collectivePackageCB.setItems(goodsService.getObservableData(DataType.COLLECTIVE_PACKAGES));
        groupCB.setItems(goodsService.getObservableData(DataType.GROUPS));
        vatRateCB.setItems(goodsService.getObservableData(DataType.VAT_RATES));
        contractorsCB.setItems(goodsService.getObservableData(DataType.CONTRACTORS));
        unitOfMeasureCB.setItems(goodsService.getObservableData(DataType.UNITS_OF_MEASURE));

        if (isEdit) {
            collectivePackageCB.getSelectionModel()
                    .select(goods.getCollectivePackageWrapper().getCutName());
            groupCB.getSelectionModel()
                    .select(goods.getGroupWrapper().getName());
            vatRateCB.getSelectionModel()
                    .select(goods.getVatRateWrapper().getName());
            contractorsCB.getSelectionModel()
                    .select(goods.getSupplierWrapper().getCutName());
            unitOfMeasureCB.getSelectionModel()
                    .select(goods.getUnitOfMeasureWrapper().getName());
        }
    }
}
