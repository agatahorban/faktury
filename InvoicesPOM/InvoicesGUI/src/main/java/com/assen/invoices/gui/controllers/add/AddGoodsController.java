package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.dto.CollectivePackageListDto;
import com.assen.invoices.dto.ContractorListDto;
import com.assen.invoices.dto.GroupListDto;
import com.assen.invoices.dto.UnitOfMeasureListDto;
import com.assen.invoices.dto.VATRateListDto;
import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.entities.Contractor;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.entities.Group;
import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.entities.VATRate;
import com.assen.invoices.gui.controllers.GoodsController;
import com.assen.invoices.gui.model.wrappers.CollectivePackageWrapper;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.model.wrappers.GroupWrapper;
import com.assen.invoices.gui.model.wrappers.UnitOfMeasureWrapper;
import com.assen.invoices.gui.model.wrappers.VATRateWrapper;
import com.assen.invoices.gui.utils.ResettableCountDownLatch;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
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

    private ResettableCountDownLatch latch;
    private static final int LATCH_COUNT = 6;

    private boolean isEdit;
    private GoodsWrapper goods;

    private Map<String, CollectivePackage> collectivePackages;
    private Map<String, Group> groups;
    private Map<String, VATRate> vatRates;
    private Map<String, Contractor> contractors;
    private Map<String, UnitOfMeasure> unitsOfMeasure;

    @Inject
    private RestUtil restUtil;

    public AddGoodsController() {
        latch = new ResettableCountDownLatch(LATCH_COUNT);
    }

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
        setBindings();
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    @FXML
    private void addOrEdit() {
        Client client = restUtil.getAuthorizedClient();
        //TODO validation
        if (validData()) {
            if (isEdit) {
                //TODO edit
            } else {
                ClientResponse response = RestUtil.generateRestPost(client, "goods/add", goods.getGoods());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error adding goods to database.");
                    errorsTA.appendText("Wystąpił błąd podczas dodawania nowego obiektu do bazy.\n");
                } else {
                    obsGoods.add(goods);
                }
            }
        }

        if (errorsTA.getText().equals("")) {
            stage.close();
        }
    }

    @FXML
    private void clear() {
        //TODO
    }

    @FXML
    private void cancel() {
        //TODO
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
        ExecutorService executor = Executors.newFixedThreadPool(5);

        collectivePackages = new HashMap<>();
        groups = new HashMap<>();
        vatRates = new HashMap<>();
        contractors = new HashMap<>();
        unitsOfMeasure = new HashMap<>();

        Client client = restUtil.getAuthorizedClient();

        executor.execute(populateCollectivePackages(client));
        executor.execute(populateGroups(client));
        executor.execute(populateVatRates(client));
        executor.execute(populateUnitsOfMeasure(client));
        executor.execute(populateContractors(client));

        latch.countDown();

        try {
            latch.await();
        } catch (InterruptedException ex) {
            logger.error("Thread interuppted while waiting for latch to count down.");
        }

        setChoiceBoxesValues();
        
        latch.reset();
        
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            logger.error("Termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                logger.error("Killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    private Runnable populateCollectivePackages(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGet(client, "collectivePackages/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all collectivePackages from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                CollectivePackageListDto packagesDto = response.getEntity(CollectivePackageListDto.class);

                packagesDto.getPackages().stream().parallel().forEach((aPackage) -> {
                    collectivePackages.put(aPackage.getCutName(), aPackage);
                });
            }
            latch.countDown();
        };
    }

    private Runnable populateGroups(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGet(client, "groups/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all groups from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                GroupListDto groupsDto = response.getEntity(GroupListDto.class);

                groupsDto.getGroups().stream().parallel().forEach((group) -> {
                    groups.put(group.getName(), group);
                });
            }
            latch.countDown();
        };
    }

    private Runnable populateVatRates(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGet(client, "vatRates/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all vatRates from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                VATRateListDto vatRatesDto = response.getEntity(VATRateListDto.class);

                vatRatesDto.getVatRates().stream().parallel().forEach((vatRate) -> {
                    vatRates.put(vatRate.getName(), vatRate);
                });
            }
            latch.countDown();
        };
    }

    private Runnable populateUnitsOfMeasure(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGet(client, "unitsOfMeasure/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all unitsOfMeasure groups from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                UnitOfMeasureListDto unitsDto = response.getEntity(UnitOfMeasureListDto.class);

                unitsDto.getUnits().stream().parallel().forEach((unit) -> {
                    unitsOfMeasure.put(unit.getName(), unit);
                });
            }
            latch.countDown();
        };
    }

    private Runnable populateContractors(Client client) {
        return () -> {
            ClientResponse response = RestUtil.generateRestGet(client, "contractors/all");
            if (RestUtil.responseHasErrors(response)) {
                logger.error("Error getting all contractors groups from database. Error status: "
                        + response.getClientResponseStatus().getStatusCode());
            } else {
                ContractorListDto contractorsDto = response.getEntity(ContractorListDto.class);

                contractorsDto.getList().stream().parallel().forEach((contractor) -> {
                    contractors.put(contractor.getCutName(), contractor);
                });
            }
            latch.countDown();
        };
    }

    private void setBindings() {
        index1TF.textProperty().bindBidirectional(goods.index1Property());
        index2TF.textProperty().bindBidirectional(goods.index2Property());
        nameTF.textProperty().bindBidirectional(goods.nameProperty());
        quantityTF.textProperty().bindBidirectional(goods.quantityProperty(), new NumberStringConverter());
        priceTF.textProperty().bindBidirectional(goods.priceProperty(), new NumberStringConverter());
    }

    private boolean validData() {
        if (nullOrEmptyValue(goods.getIndex1())) {
            errorsTA.appendText("Index1 nie może być pusty.\n");
        }
        
        if(nullOrEmptyValue(goods.getIndex2())) {
            goods.setIndex2("");
        }

        if (nullOrEmptyValue(goods.getName())) {
            errorsTA.appendText("Nazwa nie może być pusta.\n");
        }

        if (contractors.get(contractorsCB.getSelectionModel().getSelectedItem()) != null) {
            goods.setSupplierWrapper(new ContractorWrapper(
                    contractors.get(contractorsCB.getSelectionModel().getSelectedItem())));
        } else {
            errorsTA.appendText("Proszę wybrać dostawcę.\n");
        }

        if (collectivePackages.get(collectivePackageCB.getSelectionModel().getSelectedItem()) != null) {
            goods.setCollectivePackageWrapper(new CollectivePackageWrapper(
                    collectivePackages.get(collectivePackageCB.getSelectionModel().getSelectedItem())));
        } else {
            errorsTA.appendText("Proszę wybrać opakowanie.\n");
        }

        if (groups.get(groupCB.getSelectionModel().getSelectedItem()) != null) {
            goods.setGroupWrapper(new GroupWrapper(
                    groups.get(groupCB.getSelectionModel().getSelectedItem())));
        }

        if (unitsOfMeasure.get(unitOfMeasureCB.getSelectionModel().getSelectedItem()) != null) {
            goods.setUnitOfMeasureWrapper(new UnitOfMeasureWrapper(
                    unitsOfMeasure.get(unitOfMeasureCB.getSelectionModel().getSelectedItem())));
        } else {
            errorsTA.appendText("Proszę wybrać jednostkę miary.\n");
        }

        if (vatRates.get(vatRateCB.getSelectionModel().getSelectedItem()) != null) {
            goods.setVatRateWrapper(new VATRateWrapper(
                    vatRates.get(vatRateCB.getSelectionModel().getSelectedItem())));
        } else {
            errorsTA.appendText("Proszę wybrać stawkę vat.\n");
        }

        return errorsTA.getText().equals("");
    }
    
    private boolean nullOrEmptyValue(String value) {
        return value == null || value.equals("");
    }

    private void setChoiceBoxesValues() {
        ObservableList<String> obsContractors = FXCollections.observableArrayList();
        ObservableList<String> obsPackages = FXCollections.observableArrayList();
        ObservableList<String> obsGroups = FXCollections.observableArrayList();
        ObservableList<String> obsVatRates = FXCollections.observableArrayList();
        ObservableList<String> obsUnitsOfMeasure = FXCollections.observableArrayList();

        collectivePackages.entrySet().stream().parallel().forEach((entrySet) -> {
            obsPackages.add(entrySet.getKey());
        });
        groups.entrySet().stream().parallel().forEach((entrySet) -> {
            obsGroups.add(entrySet.getKey());
        });
        vatRates.entrySet().stream().parallel().forEach((entrySet) -> {
            obsVatRates.add(entrySet.getKey());
        });
        contractors.entrySet().stream().parallel().forEach((entrySet) -> {
            obsContractors.add(entrySet.getKey());
        });
        unitsOfMeasure.entrySet().stream().parallel().forEach((entrySet) -> {
            obsUnitsOfMeasure.add(entrySet.getKey());
        });

        collectivePackageCB.setItems(obsPackages);
        groupCB.setItems(obsGroups);
        vatRateCB.setItems(obsVatRates);
        contractorsCB.setItems(obsContractors);
        unitOfMeasureCB.setItems(obsUnitsOfMeasure);
    }
}
