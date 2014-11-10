package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IWydanieZewnetrzneDao;
import com.assen.faktury.encje.WydanieZewnetrzne;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class WydanieZewnetrzneDao extends CrudDao<WydanieZewnetrzne> implements IWydanieZewnetrzneDao{

    public WydanieZewnetrzneDao() {
        super(WydanieZewnetrzne.class);
    }
}
