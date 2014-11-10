package com.assen.faktury.dao.base;

import com.assen.faktury.encje.base.BaseEntity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
public abstract class CrudDao<T extends BaseEntity> implements ICrudDao<T>, Serializable {

    @PersistenceContext
    protected EntityManager em;

//    protected Logger logger;
    private Class<T> entityClass;

    protected CrudDao() {
        entityClass = (Class) ((java.lang.reflect.ParameterizedType) this
                .getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public CrudDao(Class<T> entityClass) {
        this.entityClass = entityClass;
//        logger = LoggerFactory.getLogger(this.entityClass.getName());
    }

    @Override
    public void insert(T entity) {
        em.persist(entity);
    }

    @Override
    public void remove(T entity) {
        em.remove(em.merge(entity));
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public T select(Serializable id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<T> selectAll() {
        String query = String.format("select e from %s e", entityClass.getCanonicalName());
        return em.createQuery(query, entityClass).getResultList();
    }
}
