package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.ExternalIncomeGoods;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Arek
 */
public class ExternalIncomeGoodsWrapper {

    private ExternalIncomeGoods externalIncomeGoods;
    
    private ExternalIncomeWrapper externalIncomeWrapper;
    private GoodsWrapper goodsWrapper;
    private IntegerProperty quantity;

    public ExternalIncomeGoodsWrapper(ExternalIncomeGoods externalIncomeGoods) {
        this.externalIncomeGoods = externalIncomeGoods;
        setWrapperValues();
    }

    public ExternalIncomeGoods getExternalIncomeGoods() {
        getWrapperValues();
        return externalIncomeGoods;
    }

    public void setExternalIncomeGoods(ExternalIncomeGoods externalIncomeGoods) {
        this.externalIncomeGoods = externalIncomeGoods;
        setWrapperValues();
    }

    public ExternalIncomeWrapper getExternalIncomeWrapper() {
        return externalIncomeWrapper;
    }

    public void setExternalIncomeWrapper(ExternalIncomeWrapper externalIncomeWrapper) {
        this.externalIncomeWrapper = externalIncomeWrapper;
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
        this.externalIncomeWrapper = new ExternalIncomeWrapper(externalIncomeGoods.getExternalIncome());
        this.goodsWrapper = new GoodsWrapper(externalIncomeGoods.getGoods());
        this.quantity = new SimpleIntegerProperty(externalIncomeGoods.getQuantity());
    }
    
    private void getWrapperValues() {
        externalIncomeGoods.setExternalIncome(getExternalIncomeWrapper().getExternalIncome());
        externalIncomeGoods.setGoods(getGoodsWrapper().getGoods());
        externalIncomeGoods.setQuantity(getQuantity());
    }
}
