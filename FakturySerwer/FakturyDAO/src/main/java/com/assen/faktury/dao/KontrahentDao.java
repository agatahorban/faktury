package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IKontrahentDao;
import com.assen.faktury.encje.Kontrahent;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class KontrahentDao extends CrudDao<Kontrahent> implements IKontrahentDao{

    public KontrahentDao() {
        super(Kontrahent.class);
    }
}
