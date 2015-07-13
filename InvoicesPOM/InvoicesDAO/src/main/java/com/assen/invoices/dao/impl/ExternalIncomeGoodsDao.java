package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.ExternalIncomeGoods;
import com.assen.invoices.dao.api.IExternalIncomeGoodsDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ExternalIncomeGoodsDao extends CrudDao<ExternalIncomeGoods> implements IExternalIncomeGoodsDao {

}
