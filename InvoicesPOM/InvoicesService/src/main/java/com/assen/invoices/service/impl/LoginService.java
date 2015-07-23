package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IUserDao;
import com.assen.invoices.dto.LoginCredentialsDto;
import com.assen.invoices.entities.User;
import com.assen.invoices.service.api.ILoginService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class LoginService implements ILoginService {

    @EJB
    private IUserDao userDao;
    
    @Override
    public User findUserByLoginAndPassword(LoginCredentialsDto userData) {
        return userDao.findByLoginAndPassword(userData);
    }

}
