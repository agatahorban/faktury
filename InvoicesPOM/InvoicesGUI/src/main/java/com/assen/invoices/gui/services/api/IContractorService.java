package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.ContractorWrapper;
import com.assen.invoices.gui.validators.ContractorValidator;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Agata
 */
public interface IContractorService {

    boolean deleteData(List<ContractorWrapper> contractorsToDelete);

    public String validData(ContractorWrapper contractor, ContractorValidator.ContractorValidationData validationData);

    public void populateAddContractorDataFromServer();

    public ObservableList<String> getObservableData(DataType type);
    
    public ObservableList<ContractorWrapper> populateAllContractors();

    ObservableList<ContractorWrapper> filterByCutName(String cutName);
    public enum DataType {
        BANKS,
        PAYMENT_DATES,
        PROVINCES;
    }
    
}
