package com.assen.invoices.dao.api;

import com.assen.invoices.entities.UnitOfMeasure;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IUnitOfMeasureDao extends ICrudDao<UnitOfMeasure> {
    List<UnitOfMeasure> findByShortcut(String shortcut);
}
