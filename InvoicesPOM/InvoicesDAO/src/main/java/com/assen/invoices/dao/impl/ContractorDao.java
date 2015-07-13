package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Contractor;
import com.assen.invoices.dao.api.IContractorDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ContractorDao extends CrudDao<Contractor> implements IContractorDao {

}
