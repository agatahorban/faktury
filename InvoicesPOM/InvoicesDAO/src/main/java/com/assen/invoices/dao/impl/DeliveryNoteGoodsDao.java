package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.DeliveryNoteGoods;
import com.assen.invoices.dao.api.IDeliveryNoteGoodsDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DeliveryNoteGoodsDao extends CrudDao<DeliveryNoteGoods> implements IDeliveryNoteGoodsDao {

}
