package ru.job4j.servlets.http.store;

import ru.job4j.servlets.http.User;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MemoryStore implements Store {
    private final static MemoryStore MEMORY_STORE = new MemoryStore();
    private final List<User> users = new CopyOnWriteArrayList();

    private MemoryStore() {

    }

    public static MemoryStore getInstance() {
        return MEMORY_STORE;
    }

    public boolean add(User user) {
        this.users.add(user);
        return true;
    }

    public boolean update(User user) {
        boolean result = false;
        Iterator iterator = this.users.iterator();
        while (iterator.hasNext()) {
            User u = (User) iterator.next();
            if (user.getId().equals(u.getId())) {
                u.setName(user.getName());
                u.setLogin(user.getLogin());
                u.setEmail(user.getEmail());
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        User u = this.findById(id);
        if (u != null) {
            this.users.remove(u);
            result = true;
        }
        return result;
    }

    public List<User> findAll() {
        return this.users;
    }

    public User findById(String id) {
        User result = null;
        Iterator iterator = this.users.iterator();
        while (iterator.hasNext()) {
            User u = (User) iterator.next();
            if (u.getId().equals(id)) {
                result = u;
                break;
            }
        }
        return result;
    }
}

