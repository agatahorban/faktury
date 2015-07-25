/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dao.api;

import com.assen.invoices.entities.Role;
import javax.ejb.Local;

/**
 *
 * @author Agata
 */
@Local
public interface IRoleDao extends ICrudDao<Role> {
    Role findByName(String name);
}
