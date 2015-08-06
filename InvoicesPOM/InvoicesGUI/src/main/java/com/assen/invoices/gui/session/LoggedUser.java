package com.assen.invoices.gui.session;

import com.assen.invoices.entities.User;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Arek
 */
@ApplicationScoped
public class LoggedUser {

    private User dbUser;
    private String password;

    public User getDbUser() {
        return dbUser;
    }

    public void setDbUser(User dbUser) {
        this.dbUser = dbUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
