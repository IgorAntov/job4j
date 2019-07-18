package ru.job4j.servlets.http.store;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.http.servlet.models.User;
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
    private static final String ADMIN_NAME = "admin";

    public DbStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/dbstore");
        SOURCE.setUsername("pcontrol");
        SOURCE.setPassword("pcontrol");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable();
        createAdmin();
    }
    public static DbStore getInstance() {
        return INSTANCE;
    }

    /**
     * Create User DataBase
     */
    private  void createTable() {
        try (Connection connection = SOURCE.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    "create table if not exists dbusers(id serial primary key, name character(10), login character(10), email character(20), role character(20), createDate timestamp)"
            );
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create Super User in DataBase
     */
    private void createAdmin() {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT COUNT(*) FROM dbusers WHERE name = ?")) {
            pst.setString(1, ADMIN_NAME);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    if (rs.getInt(1) == 0) {
                        User user = new User("admin", "admin", "AdEmail", "All");
                        this.add(user);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

     /**
     * Gets User Role by name and password
     * @param name
     * @param password
     * @return
     */
    public String getRole(String name, String password) {
        String role = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT role FROM dbusers WHERE name = ? and login = ?")) {
            pst.setString(1, name);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    role = rs.getString(1).trim();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return role;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO dbusers(name, login, email, role, createDate) VALUES(?, ?, ?, ?, now())")
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getRole());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = true;
        return result;
    }

    @Override
    public boolean update(User user) {
        String query = "UPDATE dbusers SET name = ?, login = ?, email = ?, role = ? where id = ?";
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getLogin());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getRole());
            pst.setInt(5, Integer.parseInt(user.getId()));
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
                User user = new User(String.valueOf(rs.getInt(1)), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(), rs.getString(5).trim());
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
                    user = new User(String.valueOf(rs.getInt(1)), rs.getString(2).trim(), rs.getString(3).trim(), rs.getString(4).trim(), rs.getString(5).trim());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
