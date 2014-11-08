package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.OpakowanieZbiorcze;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class OpakowanieZbiorczeDao extends CrudDao<OpakowanieZbiorcze> {

    public OpakowanieZbiorczeDao() {
        super(OpakowanieZbiorcze.class);
    }
}