package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Goods;
import com.assen.invoices.dao.api.IGoodsDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GoodsDao extends CrudDao<Goods> implements IGoodsDao {

}
