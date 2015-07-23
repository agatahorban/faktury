package com.assen.invoices.service.api;

import com.assen.invoices.dto.LoginCredentialsDto;
import com.assen.invoices.entities.User;
import javax.ejb.Local;

/**
 *
 * @author Arek
 */
@Local
public interface ILoginService {

    User findUserByLoginAndPassword(LoginCredentialsDto userData);
}
