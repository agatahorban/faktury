package com.assen.invoices.gui.controllers;

import com.assen.invoices.entities.Contractor;
import com.assen.invoices.gui.controllers.add.AddContractorController;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.services.api.IContractorService;
import com.assen.invoices.gui.utils.AlertUtil;
import com.assen.invoices.gui.utils.RestUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
 * @author Agata
 */
public class ContractorController implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(ContractorController.class);

    @Inject
    private RestUtil restUtil;

    @FXML
    private TableView<ContractorWrapper> contractorsTV;
    @FXML
    private TableColumn<ContractorWrapper, String> cutnameTC;
    @FXML
    private TableColumn<ContractorWrapper, String> nipTC;
    @FXML
    private TableColumn<ContractorWrapper, String> townTC;
    @FXML
    private TableColumn<ContractorWrapper, String> postalCodeTC;
    @FXML
    private TableColumn<ContractorWrapper, String> streetTC;
    @FXML
    private TableColumn<ContractorWrapper, Number> houseNumberTC;
    
    @FXML
    private TextField searchTF;

    private ObservableList<ContractorWrapper> obsContractors;

    private Stage stage;

    @Inject
    IContractorService contractorService;

    private Parent addContractorRoot;
    private Stage addContractorStage;
    private Scene addContractorScene;
    private AddContractorController addContractorController;

    @Inject
    private FXMLLoader addContractorLoader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBindings();
        initAddContractorWindow();
    }

    private void setBindings() {
        cutnameTC.setCellValueFactory(cellData -> cellData.getValue().cutNameProperty());
        nipTC.setCellValueFactory(cellData -> cellData.getValue().NIPProperty());
        townTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().townProperty());
        postalCodeTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().postalCodeProperty());
        streetTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().streetProperty());
        houseNumberTC.setCellValueFactory(cellData -> cellData.getValue().getAddressWrapper().houseNumberProperty());
    }

    public void generateData() {
        Client client = restUtil.getAuthorizedClient();
        ClientResponse response = RestUtil.generateRestGet(client, "contractors/all");
        List<Contractor> contractors = response.getEntity(new GenericType<List<Contractor>>() {
        });
        obsContractors = FXCollections.observableArrayList();
        for (Contractor contractor : contractors) {
            ContractorWrapper contractorWrapper = new ContractorWrapper(contractor);
            obsContractors.add(contractorWrapper);
        }
        contractorsTV.setItems(obsContractors);

    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void initAddContractorWindow() {
        try (InputStream addContractorFXML = getClass().getResourceAsStream("/fxml/AddContractor.fxml")) {
            addContractorRoot = addContractorLoader.load(addContractorFXML);
            addContractorStage = new Stage();
            addContractorStage.setTitle("Faktury");
            addContractorStage.initOwner(stage);
            addContractorStage.initModality(Modality.APPLICATION_MODAL);
            addContractorStage.setResizable(false);

            addContractorScene = new Scene(addContractorRoot);
            addContractorStage.setScene(addContractorScene);
            addContractorController = addContractorLoader.getController();
            addContractorController.setStage(addContractorStage);
        } catch (IOException ex) {
            logger.error("Error reading AddContractor.fxml file.");
            logger.error(ex.getMessage());
        }
    }

    @FXML
    private void addNewRecord() {
        addContractorController.setIsEdit(false);
        addContractorController.populateReferencedData();
        addContractorController.setObsContractor(obsContractors);
        addContractorStage.show();
    }
    
     @FXML
    private void editContractor() {
        List<ContractorWrapper> contractorsToEdit = contractorsTV.getSelectionModel().getSelectedItems();
        if (!contractorsToEdit.isEmpty()) {
            addContractorController.setIsEdit(true);
            addContractorController.setContractor(contractorsToEdit.get(0));
            addContractorController.populateReferencedData();

            addContractorStage.showAndWait();

            contractorsTV.getColumns().get(0).setVisible(false);
            contractorsTV.getColumns().get(0).setVisible(true);
        } else {
            Alert warning = AlertUtil.createWarningAlert("Brak zaznaczonego rekordu",
                    "Proszę wybrać kontrahenta do edycji.");
            warning.showAndWait();
        }
    }
    
    @FXML
    private void deleteRecords() {
        List<ContractorWrapper> contractorsToDelete = contractorsTV.getSelectionModel().getSelectedItems();
        Alert deleteDialog = AlertUtil
                .createConfirmationAlert("Potwierdzenie usunięcia rekordów",
                        "Czy napewno chcesz usunnąć następującą liczbę rekordów: "
                        + contractorsToDelete.size());

        Optional<ButtonType> result = deleteDialog.showAndWait();
        if (result.get().equals(ButtonType.OK)) {
            boolean deleteSuccess = contractorService.deleteData(contractorsToDelete);
            
            if (!deleteSuccess) {
                Alert error = AlertUtil.createErrorAlert("Niepowodzenie usuwania",
                        "Wystąpił błąd podczas próby usunięcia rekordów z bazy.");
  
                error.showAndWait();
            } else {
                obsContractors.removeAll(contractorsToDelete);
            }
        }
    }

    @FXML
    private void filterContractor() {
        obsContractors.clear();
        obsContractors.addAll(contractorService
                .filterByCutName(searchTF.getText()));
        if(obsContractors.isEmpty()) {
            Alert warning = AlertUtil.createWarningAlert("Rekord nie został znaleziony", 
                    "Rekord o podanej nazwie skróconej nie istnieje w bazie danych.");
            warning.showAndWait();
        }
    }
}