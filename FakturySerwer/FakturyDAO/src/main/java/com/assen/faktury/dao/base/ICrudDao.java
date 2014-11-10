package com.assen.faktury.dao.base;

import com.assen.faktury.encje.base.BaseEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Arek
 */
public interface ICrudDao<T extends BaseEntity> {

    void insert(T entity);
    
    void remove(T entity);
    
    void update(T entity);
    
    T select(Serializable id);
    
    List<T> selectAll();
}
