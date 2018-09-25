package ru.job4j.nba;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class NbaHashMap {
    @GuardedBy("this")
    ConcurrentHashMap<Integer, Base> hashMap = new ConcurrentHashMap<>();

    public synchronized void add(Integer key, Base model) {
        hashMap.put(key, model);
    }

    public void update(Integer key, Base model) {
        hashMap.computeIfPresent(key, (k, v) -> {
            if (v.getVersion() != model.getVersion()) {
                throw new OptimisticException("OptimisticException");
            }
                return model;
        });
    }

    public synchronized void delete(Integer key) {
        hashMap.remove(key);
    }

    public synchronized Base get(int key) {
        return hashMap.get(key);
    }
}
