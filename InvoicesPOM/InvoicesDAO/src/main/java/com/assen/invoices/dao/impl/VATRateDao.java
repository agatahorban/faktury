package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.VATRate;
import com.assen.invoices.dao.api.IVATRateDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class VATRateDao extends CrudDao<VATRate> implements IVATRateDao {

}
