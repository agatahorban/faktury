package com.assen.invoices.dao.api;

import com.assen.invoices.entities.User;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface IUserDao extends ICrudDao<User> {

}
