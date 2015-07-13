package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.PurchaseInvoiceGoods;
import com.assen.invoices.dao.api.IPurchaseInvoiceGoodsDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PurchaseInvoiceGoodsDao extends CrudDao<PurchaseInvoiceGoods> implements IPurchaseInvoiceGoodsDao {

}
