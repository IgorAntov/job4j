package ru.job4j.servlets.http.validate;

import ru.job4j.servlets.http.servlet.models.User;
import ru.job4j.servlets.http.store.DbStore;
import ru.job4j.servlets.http.store.Store;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {
    private final static Validate VALIDATE_SERVICE = new ValidateService();
    private final Store store = DbStore.getInstance();

    private ValidateService() {

    }

    public static Validate getInstance() {
        return VALIDATE_SERVICE;
    }

    public boolean add(User user) {
        return this.store.add(user);
    }

    public boolean update(User user) {
        return this.store.update(user);
    }

    public boolean delete(String id) {
        return this.store.delete(id);
    }

    public List<User> findAll() {
        return this.store.findAll();
    }

    public User findById(String id) {
        return this.store.findById(id);
    }
}
