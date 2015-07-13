package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.VATInvoiceGoods;
import com.assen.invoices.dao.api.IVATInvoiceGoodsDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VATInvoiceGoodsDao extends CrudDao<VATInvoiceGoods> implements IVATInvoiceGoodsDao {

}
