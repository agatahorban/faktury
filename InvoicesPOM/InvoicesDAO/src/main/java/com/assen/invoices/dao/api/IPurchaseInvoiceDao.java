package com.assen.invoices.dao.api;

import com.assen.invoices.entities.PurchaseInvoice;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IPurchaseInvoiceDao extends ICrudDao<PurchaseInvoice> {

}
