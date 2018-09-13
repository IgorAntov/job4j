package ru.job4j.monitor.storage;

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

    /**
     * Method updates user from Array
     * @param user
     * @return
     */
    synchronized public boolean update(User user) {
        boolean result = false;
        int i = findByUser(user);
        if (i != -1) {
            userStore.set(i, user);
            result = true;
        }
        return result;
    }

    /**
     * Method deletes user from Array
     * @param user
     * @return
     */
    synchronized public boolean delete(User user) {
        boolean result = false;
        int i = findByUser(user);
        if (i != -1) {
            userStore.remove(i);
            result = true;
        }
        return result;
    }

    /**
     * Method transfers amount from one user to other
     * @param fromId
     * @param toId
     * @param amount
     * @return
     */
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

    /**
     * Method finds user, that is based on ID
     * @param user
     * @return
     */
    synchronized private int findByUser(User user) {
        int result = -1;
        for (int i = 0; i < userStore.size(); i++) {
            if (user.getId() == i) {
                result = i;
            }
        }
        return result;
    }

    /**
     * Method finds user
     * @param id
     * @return
     */
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

    /**
     * Method gets user from Array
     * @param i
     * @return
     */
    synchronized public User getUser(int i) {
        return userStore.get(findById(i));
    }
}
