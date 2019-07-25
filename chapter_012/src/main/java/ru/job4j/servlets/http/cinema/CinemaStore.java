package ru.job4j.servlets.http.cinema;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CinemaStore implements Hall {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final CinemaStore INSTANCE = new CinemaStore();

    private CinemaStore() {
        SOURCE.setDriverClassName("org.postgresql.Driver");
        SOURCE.setUrl("jdbc:postgresql://127.0.0.1:5432/dbstore");
        SOURCE.setUsername("pcontrol");
        SOURCE.setPassword("pcontrol");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        createTable();
    }

    public static CinemaStore getInstance() {
        return INSTANCE;
    }


    /**
     * Create Hall DataBase
     */
    private  void createTable() {
        try (Connection connection = SOURCE.getConnection()) {
            final PreparedStatement ps = connection.prepareStatement(
                    "create table if not exists account(id serial primary key, name character(20), phone character(20))"
            );
            final PreparedStatement ps1 = connection.prepareStatement(
                    "create table if not exists hall(id serial primary key, place character(20), status character(20), descp character(20), account_id integer references account(id))"
            );
            ps.execute();
            ps1.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add account to DB atomic operation
     */
    public boolean addPlace(Account account, Place place) {
        boolean result = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement("INSERT INTO account(name, phone) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
             PreparedStatement st2 = connection.prepareStatement("INSERT INTO hall(place, status, descp, account_id) VALUES(?, ?, ?, ?)")
        ) {
            connection.setAutoCommit(false);
            st.setString(1, account.getName());
            st.setString(2, account.getPhone());
                st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            int auto_id = rs.getInt(1);
            st2.setString(1, place.getPlace());
            st2.setString(2, place.getStatus());
            st2.setString(3, place.getDesc());
            st2.setInt(4, auto_id);
            st2.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        result = true;
        return result;
    }

    /**
     * Get all Places
     * @return Places list of hall
     */
    public List<Place> getHallState() {
        List<Place> hallState= new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement pst = connection.prepareStatement("SELECT * FROM hall");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Place place = new Place();
                place.setPlace(rs.getString(2).trim());
                place.setStatus(rs.getString(3).trim());
                place.setDesc(rs.getString(4).trim());
                hallState.add(place);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return hallState;
    }
}
