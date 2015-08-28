package com.assen.invoices.gui.services.api;

import com.assen.invoices.entities.Goods;
import com.assen.invoices.gui.model.wrappers.GoodsWrapper;
import com.assen.invoices.gui.validators.GoodsValidator;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;

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
    
    ObservableList<GoodsWrapper> filterByIndex1(String index1);
    
    TreeItem<String> generateRootView();
    
    ObservableList<GoodsWrapper> filterByGroupOrContractor(Object filterOption);
    
    public enum DataType {

        COLLECTIVE_PACKAGES,
        GROUPS,
        VAT_RATES,
        CONTRACTORS,
        UNITS_OF_MEASURE
    }
}
