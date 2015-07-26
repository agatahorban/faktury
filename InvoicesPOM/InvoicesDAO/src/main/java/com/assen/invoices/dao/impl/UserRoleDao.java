/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dao.impl;

import com.assen.invoices.dao.api.IUserRoleDao;
import com.assen.invoices.entities.User;
import com.assen.invoices.entities.UserRole;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.Query;

/**
 *
 * @author Agata
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserRoleDao extends CrudDao<UserRole> implements IUserRoleDao{

    @Override
    public List<String> findByUsername(User user) {
              Query query = em.createQuery("SELECT r.role FROM UserRole r WHERE r.username = :user")
                .setParameter("user", user.getLogin());
        List<String> results = query.getResultList();
        return results;
    }
    
}
