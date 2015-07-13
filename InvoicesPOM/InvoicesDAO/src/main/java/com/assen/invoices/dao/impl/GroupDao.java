package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Group;
import com.assen.invoices.dao.api.IGroupDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GroupDao extends CrudDao<Group> implements IGroupDao {

}
