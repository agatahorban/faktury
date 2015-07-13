package com.assen.invoices.dao.api;

import com.assen.invoices.entities.VATRate;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IVATRateDao extends ICrudDao<VATRate> {

}
