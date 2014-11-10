package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IPrzychodZewnetrznyDao;
import com.assen.faktury.encje.PrzychodZewnetrzny;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class PrzychodZewnetrznyDao extends CrudDao<PrzychodZewnetrzny> implements IPrzychodZewnetrznyDao{

    public PrzychodZewnetrznyDao() {
        super(PrzychodZewnetrzny.class);
    }
}
