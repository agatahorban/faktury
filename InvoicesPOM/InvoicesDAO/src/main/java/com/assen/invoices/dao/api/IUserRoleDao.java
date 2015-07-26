/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assen.invoices.dao.api;

import com.assen.invoices.entities.Role;
import com.assen.invoices.entities.User;
import com.assen.invoices.entities.UserRole;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Agata
 */
@Local
public interface IUserRoleDao extends ICrudDao<UserRole>{
    List<String> findByUsername(User user);
}
