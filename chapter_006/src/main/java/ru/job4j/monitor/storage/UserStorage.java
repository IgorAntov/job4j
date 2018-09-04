package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final List<User> userStore = new ArrayList<>();

    synchronized public boolean add(User user) {
        this.userStore.add(user);
        return true;
    }

    synchronized public boolean update(User user) {
        boolean result = false;
        int i = findByUser(user);
        if (i != -1) {
            userStore.set(i, user);
            result = true;
        }
        return result;
    }

    synchronized public boolean delete(User user) {
        boolean result = false;
        int i = findByUser(user);
        if (i != -1) {
            userStore.remove(i);
            result = true;
        }
        return result;
    }

    synchronized public boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (fromId != toId) {
            int fid = findById(fromId);
            int tid = findById(toId);
            if (fid != -1 && tid != -1 && fid < userStore.size() && tid < userStore.size() && userStore.get(fid).getAmount() >= amount) {
                userStore.get(tid).setAmount(userStore.get(tid).getAmount() + amount);
                userStore.get(fid).setAmount(userStore.get(fid).getAmount() - amount);
                result = true;
            }
        }
        return result;
    }

    synchronized private int findByUser(User user) {
        int result = -1;
        for (int i = 0; i < userStore.size(); i++) {
            if (user.getId() == i) {
                result = i;
            }
        }
        return result;
    }

    synchronized private int findById(int id) {
        int result = -1;
        for (int i = 0; i < userStore.size(); i++) {
            if (userStore.get(i).getId() == id) {
                result = i;
                break;
            }
        }
        return result;
    }

    synchronized public User getUser(int i) {
        return userStore.get(findById(i));
    }
}
