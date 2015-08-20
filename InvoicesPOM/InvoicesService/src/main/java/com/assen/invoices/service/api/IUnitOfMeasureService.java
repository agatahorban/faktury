package com.assen.invoices.service.api;

import com.assen.invoices.entities.UnitOfMeasure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IUnitOfMeasureService {

    List<UnitOfMeasure> findAllUnitsOfMeasure();
}
