package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.VATInvoiceGoods;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Arek
 */
public class VATInvoiceGoodsWrapper {

    private VATInvoiceGoods vatInvoiceGoods;
    
    private VATInvoiceWrapper vatInvoiceWrapper;
    private GoodsWrapper goodsWrapper;
    private IntegerProperty quantity;

    public VATInvoiceGoodsWrapper(VATInvoiceGoods vatInvoiceGoods) {
        this.vatInvoiceGoods = vatInvoiceGoods;
        setWrapperValues();
    }

    public VATInvoiceGoods getVatInvoiceGoods() {
        getWrapperValues();
        return vatInvoiceGoods;
    }

    public void setVatInvoiceGoods(VATInvoiceGoods vatInvoiceGoods) {
        this.vatInvoiceGoods = vatInvoiceGoods;
        setWrapperValues();
    }

    public VATInvoiceWrapper getVatInvoiceWrapper() {
        return vatInvoiceWrapper;
    }

    public void setVatInvoiceWrapper(VATInvoiceWrapper vatInvoiceWrapper) {
        this.vatInvoiceWrapper = vatInvoiceWrapper;
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
        this.goodsWrapper = new GoodsWrapper(vatInvoiceGoods.getGoods());
        this.quantity = new SimpleIntegerProperty(vatInvoiceGoods.getQuantity());
        this.vatInvoiceWrapper = new VATInvoiceWrapper(vatInvoiceGoods.getVATInvoice());
    }
    
    private void getWrapperValues() {
        vatInvoiceGoods.setGoods(getGoodsWrapper().getGoods());
        vatInvoiceGoods.setQuantity(getQuantity());
        vatInvoiceGoods.setVATInvoice(getVatInvoiceWrapper().getVatInvoice());
    }
}
