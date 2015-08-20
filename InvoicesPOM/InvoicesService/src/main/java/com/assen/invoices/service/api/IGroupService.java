package com.assen.invoices.service.api;

import com.assen.invoices.entities.Group;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IGroupService {

    List<Group> findAllGoods();
}
