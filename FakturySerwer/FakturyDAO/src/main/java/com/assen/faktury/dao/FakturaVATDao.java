package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.FakturaVAT;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class FakturaVATDao extends CrudDao<FakturaVAT>{

    public FakturaVATDao() {
        super(FakturaVAT.class);
    }
}
