package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IGrupaDao;
import com.assen.faktury.encje.Grupa;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class GrupaDao extends CrudDao<Grupa> implements IGrupaDao{

    public GrupaDao() {
        super(Grupa.class);
    }
}
