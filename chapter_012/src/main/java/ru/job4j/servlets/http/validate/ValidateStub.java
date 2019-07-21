package ru.job4j.servlets.http.validate;

import ru.job4j.servlets.http.servlet.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ValidateStub implements Validate {
    private final static ValidateStub VALIDATE_SERVICE = new ValidateStub();

    public static ValidateStub getInstance() {
        return VALIDATE_SERVICE;
    }

    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;

    @Override
    public boolean add(User user) {
        user.setId(String.valueOf(++ids));
        this.store.put(Integer.valueOf(user.getId()), user);
        return true;
    }

    @Override
    public boolean update(User user) {
        store.put(Integer.valueOf(user.getId()), user);
        return true;
    }

    @Override
    public boolean delete(String id) {
        store.remove(Integer.valueOf(id));
        return true;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(this.store.values());
    }

    @Override
    public User findById(String id) {
        return store.get(Integer.valueOf(id));
    }
}
