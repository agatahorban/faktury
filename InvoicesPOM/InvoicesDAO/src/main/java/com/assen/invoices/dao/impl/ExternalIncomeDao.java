package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.ExternalIncome;
import com.assen.invoices.dao.api.IExternalIncomeDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ExternalIncomeDao extends CrudDao<ExternalIncome> implements IExternalIncomeDao {

}
