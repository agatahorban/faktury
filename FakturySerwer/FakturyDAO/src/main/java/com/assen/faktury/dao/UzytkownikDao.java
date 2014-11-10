package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IUzytkownikDao;
import com.assen.faktury.encje.Uzytkownik;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class UzytkownikDao extends CrudDao<Uzytkownik> implements IUzytkownikDao{

    public UzytkownikDao() {
        super(Uzytkownik.class);
    }
}
