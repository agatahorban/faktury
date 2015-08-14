package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.PurchaseInvoiceGoods;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Arek
 */
public class PurchaseInvoiceGoodsWrapper {

    private PurchaseInvoiceGoods purchaseInvoiceGoods;
    
    private PurchaseInvoiceWrapper purchaseInvoiceWrapper;
    private GoodsWrapper goodsWrapper;
    private IntegerProperty quantity;

    public PurchaseInvoiceGoodsWrapper(PurchaseInvoiceGoods purchaseInvoiceGoods) {
        this.purchaseInvoiceGoods = purchaseInvoiceGoods;
        setWrapperValues();
    }

    public PurchaseInvoiceGoods getPurchaseInvoiceGoods() {
        getWrapperValues();
        return purchaseInvoiceGoods;
    }

    public void setPurchaseInvoiceGoods(PurchaseInvoiceGoods purchaseInvoiceGoods) {
        this.purchaseInvoiceGoods = purchaseInvoiceGoods;
        setWrapperValues();
    }

    public PurchaseInvoiceWrapper getPurchaseInvoiceWrapper() {
        return purchaseInvoiceWrapper;
    }

    public void setPurchaseInvoiceWrapper(PurchaseInvoiceWrapper purchaseInvoiceWrapper) {
        this.purchaseInvoiceWrapper = purchaseInvoiceWrapper;
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
        this.goodsWrapper = new GoodsWrapper(purchaseInvoiceGoods.getGoods());
        this.purchaseInvoiceWrapper = new PurchaseInvoiceWrapper(purchaseInvoiceGoods.getPurchaseInvoice());
        this.quantity = new SimpleIntegerProperty(purchaseInvoiceGoods.getQuantity());
    }
    
    private void getWrapperValues() {
        purchaseInvoiceGoods.setGoods(getGoodsWrapper().getGoods());
        purchaseInvoiceGoods.setPurchaseInvoice(getPurchaseInvoiceWrapper().getPurchaseInvoice());
        purchaseInvoiceGoods.setQuantity(getQuantity());
    }
}
