package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.FakturaVATTowary;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class FakturaVATTowaryDao extends CrudDao<FakturaVATTowary> {

    public FakturaVATTowaryDao() {
        super(FakturaVATTowary.class);
    }
}
