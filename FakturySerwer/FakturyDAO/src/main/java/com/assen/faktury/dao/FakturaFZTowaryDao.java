package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.FakturaFZTowary;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class FakturaFZTowaryDao extends CrudDao<FakturaFZTowary> {

    public FakturaFZTowaryDao() {
        super(FakturaFZTowary.class);
    }

}
