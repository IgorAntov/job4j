package ru.job4j.servlets.http.validate;

import ru.job4j.servlets.http.servlet.models.User;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Validate {
    /**
     * Add User to Store
     * @param user param user
     * @return
     */
    boolean add(User user);

    /**
     * Update user in store
     * @param user param user
     * @return return operation status
     */
    boolean update(User user);

    /**
     * Delete user in store
     * @param id user id
     * @return
     */
    boolean delete(String id);

    /**
     * Find all users
     * @return user list
     */
    List<User> findAll();

    /**
     * Find user by id
     * @param id user id
     * @return user
     */
    User findById(String id);
}
