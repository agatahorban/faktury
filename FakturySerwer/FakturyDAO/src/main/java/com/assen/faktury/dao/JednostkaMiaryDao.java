package com.assen.faktury.dao;

import com.assen.faktury.dao.base.CrudDao;
import com.assen.faktury.encje.JednostkaMiary;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Arek
 */
@Stateless
@LocalBean
public class JednostkaMiaryDao extends CrudDao<JednostkaMiary>{

    public JednostkaMiaryDao() {
        super(JednostkaMiary.class);
    }
}
