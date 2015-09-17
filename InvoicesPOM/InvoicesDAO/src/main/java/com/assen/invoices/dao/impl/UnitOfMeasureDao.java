package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.UnitOfMeasure;
import com.assen.invoices.dao.api.IUnitOfMeasureDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UnitOfMeasureDao extends CrudDao<UnitOfMeasure> implements IUnitOfMeasureDao {

    @Override
    public List<UnitOfMeasure> findByShortcut(String shortcut) {
        Query query = em.createQuery("SELECT u FROM UnitOfMeasure u WHERE u.shortcut = :shortcut")
                .setParameter("shortcut", shortcut);
        return query.getResultList();
    }

}
