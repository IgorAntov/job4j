package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConnectionDB {

    /**
     * DataBase connection initialization
     */
    public static Connection create() {
        Connection connection;
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
            connection.setAutoCommit(true);
            if (connection == null) {
                System.out.println("Connection issue.");
            } else {
                String query = "create table if not exists items (\n"
                        + "  id serial primary key,\n"
                        + "  name varchar(100),\n"
                        + "  description varchar(100),\n"
                        + "  date timestamp\n"
                        + ")";
                try (Statement st = connection.createStatement()) {
                    st.executeUpdate(query);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return connection;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
