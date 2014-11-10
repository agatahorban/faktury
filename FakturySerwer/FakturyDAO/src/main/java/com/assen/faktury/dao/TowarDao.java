package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.ITowarDao;
import com.assen.faktury.encje.Towar;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class TowarDao extends CrudDao<Towar> implements ITowarDao{

    public TowarDao() {
        super(Towar.class);
    }
}
