package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.entities.WarehouseGoods;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Arek
 */
public class WarehouseWrapper {

    private Warehouse warehouse;
    
    private StringProperty name;
    private ObservableList<WarehouseGoodsWrapper> warehouseGoods;

    public WarehouseWrapper(Warehouse warehouse) {
        this.warehouse = warehouse;
        setWrapperValues();
    }

    public Warehouse getWarehouse() {
        getWrapperValues();
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
        setWrapperValues();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public ObservableList<WarehouseGoodsWrapper> getWarehouseGoods() {
        return warehouseGoods;
    }

    public void setWarehouseGoods(ObservableList<WarehouseGoodsWrapper> warehouseGoods) {
        this.warehouseGoods = warehouseGoods;
    }
    
    public StringProperty nameProperty() {
        return name;
    }
    
    private void setWrapperValues() {
        this.name = new SimpleStringProperty(warehouse.getName());
        this.warehouseGoods = FXCollections.observableArrayList(convertToWrapperList());
    }
    
    private void getWrapperValues() {
        warehouse.setName(getName());
        warehouse.setWarehouseGoods(convertToList());
    }
    
    private List<WarehouseGoodsWrapper> convertToWrapperList() {
        List<WarehouseGoodsWrapper> listOfWrappers = new ArrayList<>();
        warehouse.getWarehouseGoods().stream().forEach((warehouseGood) -> {
            listOfWrappers.add(new WarehouseGoodsWrapper(warehouseGood));
        });
        return listOfWrappers;
    }
    
    private List<WarehouseGoods> convertToList() {
        List<WarehouseGoods> listOfUnwrappedGoods = new ArrayList<>();
        warehouseGoods.stream().forEach((listOfGood) -> {
            listOfUnwrappedGoods.add(listOfGood.getWarehouseGoods());
        });
        return listOfUnwrappedGoods;
    }
}
