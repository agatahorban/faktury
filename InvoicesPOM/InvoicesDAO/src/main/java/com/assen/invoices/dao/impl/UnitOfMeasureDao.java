package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.dao.api.IUnitOfMeasureDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UnitOfMeasureDao extends CrudDao<UnitOfMeasure> implements IUnitOfMeasureDao {

}
