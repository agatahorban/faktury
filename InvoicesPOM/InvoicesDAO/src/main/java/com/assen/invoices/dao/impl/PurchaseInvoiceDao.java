package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.PurchaseInvoice;
import com.assen.invoices.dao.api.IPurchaseInvoiceDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PurchaseInvoiceDao extends CrudDao<PurchaseInvoice> implements IPurchaseInvoiceDao {

}
