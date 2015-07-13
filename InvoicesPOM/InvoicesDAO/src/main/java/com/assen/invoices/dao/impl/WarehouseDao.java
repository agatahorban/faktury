package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Warehouse;
import com.assen.invoices.dao.api.IWarehouseDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class WarehouseDao extends CrudDao<Warehouse> implements IWarehouseDao {

}
