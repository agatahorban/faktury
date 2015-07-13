package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.WarehouseGoods;
import com.assen.invoices.dao.api.IWarehouseGoodsDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class WarehouseGoodsDao extends CrudDao<WarehouseGoods> implements IWarehouseGoodsDao {

}
