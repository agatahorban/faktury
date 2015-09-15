package com.assen.invoices.service.api;

import com.assen.invoices.dto.GoodsListDto;
import com.assen.invoices.entities.Goods;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IGoodsService {

    List<Goods> findAllGoods();
    Goods insertNewGoods(Goods goods);
    Goods updateGoods(Goods goods);
    boolean removeGoods(GoodsListDto goodsList);
    Goods findGoodsByIndex1(String index1);
    List<Goods> findGoodsByContractor(String cutName);
    List<Goods> findGoodsByGroup(String group);
}
