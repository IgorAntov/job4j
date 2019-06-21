package ru.job4j.xml;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConfigApp {

    private final Properties values = new Properties();

    public ConfigApp() {
        this.init();
    }

    /**
     * Method initiates connection properties to DB
     */
    public void init() {
        try (InputStream in = ConfigApp.class.getClassLoader().getResourceAsStream("app.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Method gets specific key from app.properties file
     * @param key
     * @return
     */
    public String get(String key) {
        return this.values.getProperty(key);
    }
}
