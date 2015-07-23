package com.assen.invoices.dao.impl;

import com.assen.invoices.entities.User;
import com.assen.invoices.dao.api.IUserDao;
import com.assen.invoices.dto.LoginCredentialsDto;
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
public class UserDao extends CrudDao<User> implements IUserDao {

    @Override
    public User findByLoginAndPassword(LoginCredentialsDto userData) {
        Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
                .setParameter("login", userData.getLogin())
                .setParameter("password", userData.getPassword());
        List<User> result = query.getResultList();
        if (result.isEmpty()) {
            return null;
        } else {
            return result.get(0);
        }
    }

}
