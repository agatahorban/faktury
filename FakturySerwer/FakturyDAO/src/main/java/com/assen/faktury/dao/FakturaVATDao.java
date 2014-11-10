package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IFakturaVATDao;
import com.assen.faktury.encje.FakturaVAT;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class FakturaVATDao extends CrudDao<FakturaVAT> implements IFakturaVATDao{

    public FakturaVATDao() {
        super(FakturaVAT.class);
    }
}
