package com.assen.invoices.gui.model.wrappers;

import com.assen.invoices.entities.DeliveryNoteGoods;

/**
 *
 * @author Arek
 */
public class DeliveryNoteGoodsWrapper {

    private DeliveryNoteGoods deliveryNoteGoods;

    public DeliveryNoteGoodsWrapper(DeliveryNoteGoods deliveryNoteGoods) {
        this.deliveryNoteGoods = deliveryNoteGoods;
    }

    public DeliveryNoteGoods getDeliveryNoteGoods() {
        return deliveryNoteGoods;
    }

    public void setDeliveryNoteGoods(DeliveryNoteGoods deliveryNoteGoods) {
        this.deliveryNoteGoods = deliveryNoteGoods;
    }
}
