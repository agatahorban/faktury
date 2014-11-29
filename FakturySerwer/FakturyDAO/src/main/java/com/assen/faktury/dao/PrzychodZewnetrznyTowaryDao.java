package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.PrzychodZewnetrznyTowary;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class PrzychodZewnetrznyTowaryDao extends CrudDao<PrzychodZewnetrznyTowary>{

    public PrzychodZewnetrznyTowaryDao() {
        super(PrzychodZewnetrznyTowary.class);
    }
}
