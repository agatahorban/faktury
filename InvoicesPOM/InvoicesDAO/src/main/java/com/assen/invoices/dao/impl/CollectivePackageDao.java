package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.CollectivePackage;
import com.assen.invoices.dao.api.ICollectivePackageDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CollectivePackageDao extends CrudDao<CollectivePackage> implements ICollectivePackageDao{

}
