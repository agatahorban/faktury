package com.assen.invoices.service.impl;

import com.assen.invoices.dao.api.IGoodsDao;
import com.assen.invoices.dto.GoodsListDto;
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
        logger.info("Returning all goods from database");
        return goodsDao.selectAll();
    }

    @Override
    public Goods insertNewGoods(Goods goods) {
        logger.info("Inserting new goods to database: " + goods.getIndex1());
        try {
            goods = goodsDao.insert(goods);
        } catch (Exception ex) {
            logger.error("Error adding new goods to database. Error message: " + ex.getMessage());
            return null;
        }
        return goods;
    }

    @Override
    public Goods updateGoods(Goods goods) {
        logger.info("Updating goods values: " + goods.getIndex1());
        try {
            goods = goodsDao.update(goods);
        } catch (Exception ex) {
            logger.error("Error updating goods data. Goods: " + goods.getId()
                    + ", error message: " + ex.getMessage());
            return null;
        }
        return goods;
    }

    @Override
    public boolean removeGoods(GoodsListDto goodsList) {
        logger.info("Deleting goods from database: " + goodsList.getList().size());
        try {
            goodsList.getList().stream().parallel().forEach((goodsToDelete) -> {
                goodsDao.remove(goodsToDelete);
            });
        } catch (Exception ex) {
            logger.error("Error deleting goods from database. Error message: "
                    + ex.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Goods findGoodsByIndex1(String index1) {
        logger.info("Filtering goods by index1: " + index1);
        List<Goods> filteredGoods = goodsDao.findByIndex1(index1);
        if(filteredGoods.isEmpty()) {
            logger.info("No goods found with index1: " + index1);
            return null;
        }
        return filteredGoods.get(0);
    }

    @Override
    public List<Goods> findGoodsByContractor(String cutName) {
        logger.info("Filtering goods by contractor: " + cutName);
        return goodsDao.findByContractor(cutName);
    }

    @Override
    public List<Goods> findGoodsByGroup(String group) {
        logger.info("Filtering goods by group: " + group);
        return goodsDao.findByGroup(group);
    }
}
