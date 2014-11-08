package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.Adres;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class AdresDao extends CrudDao<Adres> {

    public AdresDao() {
        super(Adres.class);
    }
}
