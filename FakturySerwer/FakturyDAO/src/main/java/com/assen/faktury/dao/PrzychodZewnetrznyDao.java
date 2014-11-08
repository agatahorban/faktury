package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.PrzychodZewnetrzny;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class PrzychodZewnetrznyDao extends CrudDao<PrzychodZewnetrzny> {

    public PrzychodZewnetrznyDao() {
        super(PrzychodZewnetrzny.class);
    }
}