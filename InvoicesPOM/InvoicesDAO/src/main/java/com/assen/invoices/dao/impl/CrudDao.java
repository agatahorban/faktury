package com.assen.invoices.dao.impl;

import com.assen.invoices.dao.api.ICrudDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Arek
 */
public abstract class CrudDao<T extends Serializable> implements ICrudDao<T> {

    @PersistenceContext(name = "invoicesDatabase")
    protected EntityManager em;

    private Class<T> domainClass;

    @SuppressWarnings("unchecked")
    private Class<T> getDomainClass() {
        domainClass = (Class<T>) ((ParameterizedType) 
                getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return domainClass;
    }

    private String getDomainClassName() {
        return getDomainClass().getName();
    }

    @Override
    public T insert(T entity) {
        em.persist(entity);
        return entity;
    }

    @Override
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public T select(Serializable id) {
        return em.find(getDomainClass(), id);
    }

    @Override
    public List<T> selectAll() {
        String query = String.format("select e from %s e", getDomainClassName());
        return em.createQuery(query, domainClass).getResultList();
    }
}
