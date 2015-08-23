package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IGoodsDao;
import com.assen.invoices.entities.Goods;
import com.assen.invoices.service.api.IGoodsService;
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
public class GoodsService implements IGoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsService.class);

    @EJB
    private IGoodsDao goodsDao;

    @Override
    public List<Goods> findAllGoods() {
        return goodsDao.selectAll();
    }

    @Override
    public boolean insertNewGoods(Goods goods) {
        try {
            goodsDao.insert(goods);
        } catch (Exception ex) {
            logger.error("Error adding new goods to database. Error message: " + ex.getMessage());
            return false;
        }
        return true;
    }
}
