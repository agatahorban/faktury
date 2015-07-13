package com.assen.invoices.dao.api;

import com.assen.invoices.entities.PaymentDate;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IPaymentDateDao extends ICrudDao<PaymentDate> {

}
