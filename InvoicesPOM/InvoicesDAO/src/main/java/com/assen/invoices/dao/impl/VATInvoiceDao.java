package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.VATInvoice;
import com.assen.invoices.dao.api.IVATInvoiceDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VATInvoiceDao extends CrudDao<VATInvoice> implements IVATInvoiceDao {

}
