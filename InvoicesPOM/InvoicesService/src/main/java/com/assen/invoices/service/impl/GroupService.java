package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IGroupDao;
import com.assen.invoices.entities.Group;
import com.assen.invoices.service.api.IGroupService;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GroupService implements IGroupService {

    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);
    
    @EJB
    private IGroupDao groupDao;

    @Override
    public List<Group> findAllGoods() {
        return groupDao.selectAll();
    }
}
