package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.TerminPlatnosci;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class TerminPlatnosciDao extends CrudDao<TerminPlatnosci>{

    public TerminPlatnosciDao() {
        super(TerminPlatnosci.class);
    }
}
