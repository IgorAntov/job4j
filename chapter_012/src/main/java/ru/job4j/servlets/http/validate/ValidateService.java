package ru.job4j.servlets.http.validate;

import ru.job4j.servlets.http.User;
import ru.job4j.servlets.http.store.MemoryStore;
import ru.job4j.servlets.http.store.Store;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ValidateService implements Validate {
    private final static ValidateService VALIDATE_SERVICE = new ValidateService();
    private final Store store = MemoryStore.getInstance();
    private static final Random RN = new Random();

    public static ValidateService getInstance() {
        return VALIDATE_SERVICE;
    }

    public boolean add(String name, String login, String email) {
        return this.store.add(new User(this.generateId(), name, login, email, new Date(System.currentTimeMillis())));
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

    /**
     * Method generates user id
     * @return
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + (long) RN.nextInt());
    }
}
