package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.dao.interfaces.IOpakowanieZbiorczeDao;
import com.assen.faktury.encje.OpakowanieZbiorcze;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
public class OpakowanieZbiorczeDao extends CrudDao<OpakowanieZbiorcze> implements IOpakowanieZbiorczeDao{

    public OpakowanieZbiorczeDao() {
        super(OpakowanieZbiorcze.class);
    }
}
