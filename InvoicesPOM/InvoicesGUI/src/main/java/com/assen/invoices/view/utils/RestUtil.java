package com.assen.invoices.view.utils;

import com.sun.jersey.api.client.Client;

/**
 *
 * @author Arek
 */
public class RestUtil {

    public static final String URL = "http://localhost:8080/InvoicesREST/";

    public static Client getClient() {
        return new Client();
    }
}
