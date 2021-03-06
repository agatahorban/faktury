package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Address;
import com.assen.invoices.dao.api.IAddressDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AddressDao extends CrudDao<Address> implements IAddressDao {

}
