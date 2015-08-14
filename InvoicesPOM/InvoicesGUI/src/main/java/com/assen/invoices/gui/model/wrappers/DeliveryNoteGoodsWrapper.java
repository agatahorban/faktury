package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.DeliveryNoteGoods;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author Arek
 */
public class DeliveryNoteGoodsWrapper {

    private DeliveryNoteGoods deliveryNoteGoods;
    
    private DeliveryNoteWrapper deliveryNoteWrapper;
    private GoodsWrapper goodsWrapper;
    private IntegerProperty quantity;

    public DeliveryNoteGoodsWrapper(DeliveryNoteGoods deliveryNoteGoods) {
        this.deliveryNoteGoods = deliveryNoteGoods;
        setWrapperValues();
    }

    public DeliveryNoteGoods getDeliveryNoteGoods() {
        getWrapperValues();
        return deliveryNoteGoods;
    }

    public void setDeliveryNoteGoods(DeliveryNoteGoods deliveryNoteGoods) {
        this.deliveryNoteGoods = deliveryNoteGoods;
        setWrapperValues();
    }

    public DeliveryNoteWrapper getDeliveryNoteWrapper() {
        return deliveryNoteWrapper;
    }

    public void setDeliveryNoteWrapper(DeliveryNoteWrapper deliveryNoteWrapper) {
        this.deliveryNoteWrapper = deliveryNoteWrapper;
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
        deliveryNoteWrapper = new DeliveryNoteWrapper(deliveryNoteGoods.getDeliveryNote());
        goodsWrapper = new GoodsWrapper(deliveryNoteGoods.getGoods());
        this.quantity = new SimpleIntegerProperty(deliveryNoteGoods.getQuantity());
    }
    
    private void getWrapperValues() {
        deliveryNoteGoods.setDeliveryNote(getDeliveryNoteWrapper().getDeliveryNote());
        deliveryNoteGoods.setGoods(getGoodsWrapper().getGoods());
        deliveryNoteGoods.setQuantity(getQuantity());
    }
}
