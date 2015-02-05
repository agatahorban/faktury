package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.WydanieZewnetrzneTowary;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class WydanieZewnetrzneTowaryDao extends CrudDao<WydanieZewnetrzneTowary>{

    public WydanieZewnetrzneTowaryDao() {
        super(WydanieZewnetrzneTowary.class);
    }
}