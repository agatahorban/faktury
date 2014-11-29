package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.MagazynTowary;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class MagazynTowaryDao extends CrudDao<MagazynTowary>{

    public MagazynTowaryDao() {
        super(MagazynTowary.class);
    }
}
