package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.Magazyn;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class MagazynDao extends CrudDao<Magazyn>{

    public MagazynDao() {
        super(Magazyn.class);
    }
}
