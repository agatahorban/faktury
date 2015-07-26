/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dao.impl;

import com.assen.invoices.dao.api.IRoleDao;
import com.assen.invoices.entities.Role;
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
public class RoleDao extends CrudDao<Role> implements IRoleDao{

    @Override
    public Role findByName(String name) {
           Query query = em.createQuery("SELECT r FROM Role r WHERE r.name = :name")
                .setParameter("name", name);
        Role result = (Role) query.getSingleResult();
        return result;
    }
    
}
