package com.assen.invoices.dao.api;

import com.assen.invoices.entities.Goods;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IGoodsDao extends ICrudDao<Goods> {

    List<Goods> findByIndex1(String index1);
}
