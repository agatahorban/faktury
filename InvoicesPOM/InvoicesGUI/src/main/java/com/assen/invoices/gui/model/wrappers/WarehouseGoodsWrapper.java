package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.WarehouseGoods;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Arek
 */
public class WarehouseGoodsWrapper {

    private WarehouseGoods warehouseGoods;

    private WarehouseWrapper warehouseWrapper;
    private GoodsWrapper goodsWrapper;
    private IntegerProperty quantity;

    public WarehouseGoodsWrapper(WarehouseGoods warehouseGoods) {
        this.warehouseGoods = warehouseGoods;
        setWrapperValues();
    }

    public WarehouseGoods getWarehouseGoods() {
        getWrapperValues();
        return warehouseGoods;
    }

    public void setWarehouseGoods(WarehouseGoods warehouseGoods) {
        this.warehouseGoods = warehouseGoods;
        setWrapperValues();
    }

    public WarehouseWrapper getWarehouseWrapper() {
        return warehouseWrapper;
    }

    public void setWarehouseWrapper(WarehouseWrapper warehouseWrapper) {
        this.warehouseWrapper = warehouseWrapper;
    }

    public GoodsWrapper getGoodsWrapper() {
        return goodsWrapper;
    }

    public void setGoodsWrapper(GoodsWrapper goodsWrapper) {
        this.goodsWrapper = goodsWrapper;
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    private void setWrapperValues() {
        this.goodsWrapper = new GoodsWrapper(warehouseGoods.getGoods());
        this.quantity = new SimpleIntegerProperty(warehouseGoods.getQuantity());
        this.warehouseWrapper = new WarehouseWrapper(warehouseGoods.getWarehouse());
    }

    private void getWrapperValues() {
        warehouseGoods.setGoods(getGoodsWrapper().getGoods());
        warehouseGoods.setQuantity(getQuantity());
        warehouseGoods.setWarehouse(getWarehouseWrapper().getWarehouse());
    }
}
