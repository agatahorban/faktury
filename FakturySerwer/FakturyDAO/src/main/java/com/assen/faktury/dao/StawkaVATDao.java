package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IStawkaVATDao;
import com.assen.faktury.encje.StawkaVAT;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class StawkaVATDao extends CrudDao<StawkaVAT> implements IStawkaVATDao{

    public StawkaVATDao() {
        super(StawkaVAT.class);
    }
}
