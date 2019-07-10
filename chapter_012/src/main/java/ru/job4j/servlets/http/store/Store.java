package ru.job4j.servlets.http.store;

import ru.job4j.servlets.http.User;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Store {

    boolean add(User user);

    boolean update(User user);

    boolean delete(String s);

    List<User> findAll();

    User findById(String s);
}

