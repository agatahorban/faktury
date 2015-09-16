package com.assen.invoices.gui.controllers.add;

import com.assen.invoices.entities.Contractor;
import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.services.api.IContractorService;
import com.assen.invoices.gui.services.api.IContractorService.DataType;
import com.assen.invoices.gui.utils.RestUtil;
import com.assen.invoices.gui.validators.ContractorValidator;
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
 * @author Agata
 */
public class AddContractorController implements Initializable {
    private static final Logger logger = LoggerFactory.getLogger(AddContractorController.class);

    @FXML
    private TextField fullnameTF;

    @FXML
    private TextField cutnameTF;

    @FXML
    private TextField nipTF;

    @FXML
    private CheckBox vatPayerCB;

    @FXML
    private TextField streetTF;

    @FXML
    private TextField houseNumberTF;

    @FXML
    private TextField apartmentNameTF;

    @FXML
    private TextField postalCodeTF;

    @FXML
    private TextField townTF;

    @FXML
    private ChoiceBox provinceCB;

    @FXML
    private TextField countyTF;

    @FXML
    private TextField boroughTF;

    @FXML
    private CheckBox addresseeCB;

    @FXML
    private CheckBox supplierCB;

    @FXML
    private ChoiceBox bankChB;

    @FXML
    private ChoiceBox paymentDateChB;

    @FXML
    private Button clearButton;

    @FXML
    private Button addEditButton;

    @FXML
    private TextArea errorsTA;

    private Stage stage;
    private boolean isEdit;
    private ObservableList<ContractorWrapper> obsContractor;
    private ContractorWrapper contractor;
    private Contractor originalContractor;

    @Inject
    private RestUtil restUtil;

    @Inject
    private IContractorService contractorService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isEdit = false;
        errorsTA.setText("");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public boolean isIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

    public ObservableList<ContractorWrapper> getObsContractor() {
        return obsContractor;
    }

    public void setObsContractor(ObservableList<ContractorWrapper> obsContractor) {
        this.obsContractor = obsContractor;
    }

    public void populateReferencedData() {
        if (!isEdit) {
            setContractor(new ContractorWrapper(new Contractor()));
            addEditButton.setText("Dodaj");
            clearButton.setVisible(true);
        } else {
            addEditButton.setText("Edytuj");
            clearButton.setVisible(false);
        }

        contractorService.populateAddContractorDataFromServer();
        setChoiceBoxesValues();
    }

    public void setContractor(ContractorWrapper contractor) {
        this.contractor = contractor;
        originalContractor = Contractor.copyOf(contractor.getContractor());
        setBindings();
    }

    private void setBindings() {
        fullnameTF.textProperty().bindBidirectional(contractor.fullNameProperty());
        cutnameTF.textProperty().bindBidirectional(contractor.cutNameProperty());
        vatPayerCB.selectedProperty().bindBidirectional(contractor.VATPayerProperty());
        streetTF.textProperty().bindBidirectional(contractor.getAddressWrapper().streetProperty());
        houseNumberTF.textProperty().bindBidirectional(contractor.getAddressWrapper().houseNumberProperty(), new NumberStringConverter());
        postalCodeTF.textProperty().bindBidirectional(contractor.getAddressWrapper().postalCodeProperty());
        townTF.textProperty().bindBidirectional(contractor.getAddressWrapper().townProperty());
        countyTF.textProperty().bindBidirectional(contractor.getAddressWrapper().countyProperty());
        boroughTF.textProperty().bindBidirectional(contractor.getAddressWrapper().boroughProperty());
        addresseeCB.selectedProperty().bindBidirectional(contractor.addresseeProperty());
        supplierCB.selectedProperty().bindBidirectional(contractor.supplierProperty());
        nipTF.textProperty().bindBidirectional(contractor.NIPProperty());
    }

    @FXML
    private void addOrEdit() {
        errorsTA.setText("");
        Client client = restUtil.getAuthorizedClient();
        if (validData()) {
            if (isEdit) {
                ClientResponse response = RestUtil.generateRestPost(client, "contractors/update", contractor.getContractor());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error updating contractors to database.");
                    errorsTA.appendText("Wystąpił błąd podczas aktualizowania nowego obiektu do bazy.\n");
                } else {
                    contractor.setContractor(response.getEntity(Contractor.class));
                }
            } else {
                ClientResponse response = RestUtil.generateRestPost(client, "contractors/add", contractor.getContractor());
                if (RestUtil.responseHasErrors(response)) {
                    logger.error("Error adding contractors to database.");
                    errorsTA.appendText("Wystąpił błąd podczas dodawania nowego obiektu do bazy.\n");
                } else {
                    contractor.setContractor(response.getEntity(Contractor.class));
                    obsContractor.add(new ContractorWrapper(contractor.getContractor()));
                }
            }
        }

        if (errorsTA.getText().equals("")) {
            stage.close();
        }
    }

    private boolean validData() {
        ContractorValidator.ContractorValidationData validationData
                = new ContractorValidator.ContractorValidationData(
                        (String) provinceCB.getSelectionModel().getSelectedItem(),
                        (String) bankChB.getSelectionModel().getSelectedItem(),
                        (String) paymentDateChB.getSelectionModel().getSelectedItem()
                );
        String errors = contractorService.validData(contractor, validationData);

        if (!errors.equals("")) {
            logger.info("Invalid Contractor data: " + errors);
            errorsTA.appendText(errors);
            return false;
        }
        return true;
    }
    
    @FXML
    private void clear() {
        contractor.setContractor(new Contractor());
        setBindings();
        setChoiceBoxesValues();
    }

    private void setChoiceBoxesValues() {
        bankChB.setItems(contractorService.getObservableData(DataType.BANKS));
        paymentDateChB.setItems(contractorService.getObservableData(DataType.PAYMENT_DATES));

        if (isEdit) {
            bankChB.getSelectionModel()
                    .select(contractor.getBankWrapper().getBankName());
            paymentDateChB.getSelectionModel()
                    .select(contractor.getPaymentDateWrapper().getDescription());
          
        }
    }
    
     @FXML
    private void cancel() {
        errorsTA.setText("");
        if (isEdit) {
            contractor.setContractor(originalContractor);
        }
        stage.close();
    }
}
