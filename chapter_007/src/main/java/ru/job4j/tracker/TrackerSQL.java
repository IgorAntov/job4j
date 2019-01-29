package ru.job4j.tracker;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storage.ITracker;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class TrackerSQL  implements ITracker, Cloneable, AutoCloseable {
    private Connection connection;

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    @Override
    public Item add(Item item) {
        String query = "INSERT INTO items(name, description, date) VALUES(?, ?, now())";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }

    /**
     * Метод редактирование заявок
     * @param id уникальный ключ
     * @param item - класс заявка
     */
    @Override
    public boolean replace(String id, Item item) {
        String query = "UPDATE items SET name = ?, description = ? where id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.setInt(3, Integer.parseInt(id));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * Метод реализаущий удаление заявки из хранилища
     * @param id уникальный ключ
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        String query = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @Override
    public List<Item> findAll() {
        List<Item>  items = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM items");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Item item = getItem(rs);
                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    /**
     * Получение списка всех заявок
     * @return лист заявок
     */
    @Override
    public List<Item> findByName(String key) {
        List<Item>  items = new ArrayList<>();
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM items WHERE name LIKE ?")) {
            pst.setString(1, "%" + key + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Item item = getItem(rs);
                    items.add(item);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    private Item getItem(ResultSet rs) throws SQLException {
        Item item = new Item(rs.getString(2), rs.getString(3));
        item.setId(String.valueOf(rs.getInt(1)));
        item.setCreateDate(rs.getDate(4));
        return item;
    }

    /**
     * Метод реализаущий поиск заявок по имени
     * @param key ключ поиска (по имени)
     * @return все заявки с одинаковым именем (key)
     */
    @Override
    public Item findById(String id) {
        Item item = null;
        try (PreparedStatement pst = connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            pst.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    item = getItem(rs);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }

    /**
     * Проверка, что список не пустой
     */
    @Override
    public boolean listNotEmpty() {
        boolean result = false;
        try (PreparedStatement pst = connection.prepareStatement("SELECT count(id) FROM items");
             ResultSet rs = pst.executeQuery()) {
            rs.next();
            result = rs.getInt(1) > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
