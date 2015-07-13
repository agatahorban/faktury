package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Bank;
import com.assen.invoices.dao.api.IBankDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class BankDao extends CrudDao<Bank> implements IBankDao {

}
