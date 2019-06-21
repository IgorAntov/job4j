package ru.job4j.parsing;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Config {

    private final Properties values = new Properties();

    public Config(String profile) {
        this.init(profile);
    }

    /**
     * Method initiates connection properties to DB
     */
    public void init(String profile) {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream(profile)) {
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
