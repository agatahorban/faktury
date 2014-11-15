package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.Uzytkownik;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class UzytkownikDao extends CrudDao<Uzytkownik>{

    public UzytkownikDao() {
        super(Uzytkownik.class);
    }
}
