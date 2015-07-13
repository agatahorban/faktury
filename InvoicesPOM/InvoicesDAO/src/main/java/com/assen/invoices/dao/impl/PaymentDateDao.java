package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.PaymentDate;
import com.assen.invoices.dao.api.IPaymentDateDao;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PaymentDateDao extends CrudDao<PaymentDate> implements IPaymentDateDao {

}
