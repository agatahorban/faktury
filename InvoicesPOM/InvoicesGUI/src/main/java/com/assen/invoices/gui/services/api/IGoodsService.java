package com.assen.invoices.gui.services.api;

import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.validators.GoodsValidator;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Arek
 */
public interface IGoodsService {

    ObservableList<String> getObservableData(DataType type);

    void populateAddGoodsDataFromServer();
    
    String validData(GoodsWrapper goods, GoodsValidator.GoodsValidationData validationData);
    
    boolean deleteData(List<GoodsWrapper> goodsToDelete);
    
    ObservableList<GoodsWrapper> populateAllGoods();
    
    public enum DataType {

        COLLECTIVE_PACKAGES,
        GROUPS,
        VAT_RATES,
        CONTRACTORS,
        UNITS_OF_MEASURE
    }
}
