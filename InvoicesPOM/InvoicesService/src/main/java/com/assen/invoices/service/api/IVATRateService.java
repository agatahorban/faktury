package com.assen.invoices.service.api;

import com.assen.invoices.entities.VATRate;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IVATRateService {

    List<VATRate> findAllVATRates();
}
