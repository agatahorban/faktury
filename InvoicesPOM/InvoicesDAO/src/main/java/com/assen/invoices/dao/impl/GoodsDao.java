package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.Goods;
import com.assen.invoices.dao.api.IGoodsDao;
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
public class GoodsDao extends CrudDao<Goods> implements IGoodsDao {

    @Override
    public List<Goods> findByIndex1(String index1) {
        Query query = em.createQuery("SELECT g FROM Goods g WHERE g.index1 = :index")
                .setParameter("index", index1);
        return query.getResultList();
    }

    @Override
    public List<Goods> findByContractor(String cutName) {
        Query query = em.createQuery("SELECT g FROM Goods g WHERE g.supplier.cutName = :cutName")
                .setParameter("cutName", cutName);
        return query.getResultList();
    }

    @Override
    public List<Goods> findByGroup(String groupName) {
        Query query = em.createQuery("SELECT g FROM Goods g WHERE g.group.name = :groupName")
                .setParameter("groupName", groupName);
        return query.getResultList();
    }

}
