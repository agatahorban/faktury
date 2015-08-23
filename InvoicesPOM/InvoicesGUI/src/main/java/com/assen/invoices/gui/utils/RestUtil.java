package com.assen.invoices.gui.utils;

import com.assen.invoices.gui.session.LoggedUser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
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
    
    public static boolean responseHasErrors(ClientResponse response) {
        return response.getClientResponseStatus().equals(ClientResponse.Status.INTERNAL_SERVER_ERROR)
                || response.getClientResponseStatus().equals(ClientResponse.Status.UNAUTHORIZED);
    }
    
    public static ClientResponse generateRestGet(Client client, String restUrl) {
        WebResource webResource = client.resource(URL + restUrl);
        return webResource.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
    }
    
    public static ClientResponse generateRestPost(Client client, String restUrl, Object data) {
        WebResource webResource = client.resource(URL + restUrl);
        return webResource.accept(MediaType.APPLICATION_XML).post(ClientResponse.class, data);
    }
}
