package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.User;
import com.assen.invoices.dao.api.IUserDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserDao extends CrudDao<User> implements IUserDao {

}
