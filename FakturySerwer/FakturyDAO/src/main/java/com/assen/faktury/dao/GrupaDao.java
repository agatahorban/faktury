package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.Grupa;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class GrupaDao extends CrudDao<Grupa>{

    public GrupaDao() {
        super(Grupa.class);
    }
}
