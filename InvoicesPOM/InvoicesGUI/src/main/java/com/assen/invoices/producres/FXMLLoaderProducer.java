package com.assen.invoices.producres;

import java.nio.charset.StandardCharsets;
import javafx.fxml.FXMLLoader;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Arek
 */
public class FXMLLoaderProducer {

    @Inject
    Instance<Object> instance;

    @Produces
    public FXMLLoader createLodaer() {
        return new FXMLLoader(null, null, null,
                (Class<?> param) -> instance.select(param).get(),
                StandardCharsets.UTF_8);
    }
}
