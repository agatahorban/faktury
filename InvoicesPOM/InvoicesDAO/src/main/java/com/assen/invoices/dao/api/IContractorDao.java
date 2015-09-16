package com.assen.invoices.dao.api;

import com.assen.invoices.entities.Contractor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IContractorDao extends ICrudDao<Contractor> {
    List<Contractor> findByCutName(String cutName);
}
