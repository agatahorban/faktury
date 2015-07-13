package com.assen.invoices.dao.api;

import com.assen.invoices.entities.VATInvoice;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IVATInvoiceDao extends ICrudDao<VATInvoice> {

}
