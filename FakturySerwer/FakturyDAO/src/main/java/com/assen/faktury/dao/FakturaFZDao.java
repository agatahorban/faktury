package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IFakturaFZDao;
import com.assen.faktury.encje.FakturaFZ;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class FakturaFZDao extends CrudDao<FakturaFZ> implements IFakturaFZDao{

    public FakturaFZDao() {
        super(FakturaFZ.class);
    }
}
