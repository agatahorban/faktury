package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.Kontrahent;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class KontrahentDao extends CrudDao<Kontrahent> {

    public KontrahentDao() {
        super(Kontrahent.class);
    }
}
