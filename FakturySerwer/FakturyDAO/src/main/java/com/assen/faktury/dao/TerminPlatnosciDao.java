package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.ITerminPlatnosciDao;
import com.assen.faktury.encje.TerminPlatnosci;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class TerminPlatnosciDao extends CrudDao<TerminPlatnosci> implements ITerminPlatnosciDao{

    public TerminPlatnosciDao() {
        super(TerminPlatnosci.class);
    }
}
