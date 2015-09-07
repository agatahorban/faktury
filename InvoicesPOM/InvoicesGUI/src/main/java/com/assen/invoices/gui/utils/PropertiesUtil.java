package com.assen.invoices.gui.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Arek
 */
public class PropertiesUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private Properties props;

    public PropertiesUtil(String propertiesFilename) {
        props = new Properties();
        try (InputStreamReader isr = new InputStreamReader(getClass().getClassLoader()
                .getResourceAsStream(propertiesFilename), "UTF-8")) {
            props.load(isr);
        } catch (IOException ex) {
            logger.error("Error reading properties file: " + propertiesFilename);
        }
    }

    public String getProperty(String key) {
        return props.getProperty(key);
    }
}
