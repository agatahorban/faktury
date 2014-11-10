package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IBankDao;
import com.assen.faktury.encje.Bank;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class BankDao extends CrudDao<Bank> implements IBankDao{

    public BankDao() {
        super(Bank.class);
    }
}
