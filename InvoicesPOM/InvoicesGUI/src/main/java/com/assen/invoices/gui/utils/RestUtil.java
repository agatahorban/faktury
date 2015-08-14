package com.assen.invoices.gui.utils;

import com.assen.invoices.gui.session.LoggedUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
public class RestUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(RestUtil.class);

    @Inject
    private LoggedUser loggedUser;
    
    private static final ApplicationPropertiesUtil appProps = new ApplicationPropertiesUtil();

    public static final String URL = appProps.getProperty("rest.url");
    
    public static Client getUnauthorizedClient() {
        return Client.create();
    }

    public Client getAuthorizedClient() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(loggedUser.getDbUser().getLogin(),
                loggedUser.getPassword()));
        return client;
    }
}
