package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Contractor;
import com.assen.invoices.dao.api.IContractorDao;
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
public class ContractorDao extends CrudDao<Contractor> implements IContractorDao {

    @Override
    public List<Contractor> findByCutName(String cutName) {
         Query query = em.createQuery("SELECT c FROM Contractor c WHERE c.cutName = :cutName")
                .setParameter("cutName", cutName);
        return query.getResultList();
    }

}
