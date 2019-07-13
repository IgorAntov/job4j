package ru.job4j.servlets.http.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.http.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/dbstore");
        SOURCE.setUsername("pcontrol");
        SOURCE.setPassword("pcontrol");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable();
    }
    public static DbStore getInstance() {
        return INSTANCE;
    }

    private  void createTable() {
        try (Connection connection = SOURCE.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    "create table if not exists dbusers(id serial primary key, name character(10), login character(10), email character(20), createDate timestamp)"
            );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO dbusers(name, login, email, createDate) VALUES(?, ?, ?, now())")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        String query = "UPDATE dbusers SET name = ?, login = ?, email = ? where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setInt(4, Integer.parseInt(user.getId()));
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        String query = "DELETE FROM dbusers WHERE id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setInt(1, Integer.parseInt(id));
            pst.executeUpdate();
            result = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;

    }

    @Override
    public List<User> findAll() {
        List<User>  users = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM dbusers");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                User user = new User(String.valueOf(rs.getInt(1)), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim());
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM dbusers WHERE id = ?")) {
            pst.setInt(1, Integer.parseInt(id));
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    user = new User(String.valueOf(rs.getInt(1)), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
