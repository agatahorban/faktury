package com.assen.invoices.service.api;

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
}
