package ru.job4j.nba;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class NbaHashMap {
    ConcurrentHashMap<Integer, Base> hashMap = new ConcurrentHashMap<>();

    public void add(Integer key, Base model) {
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

    public void delete(Integer key) {
        hashMap.remove(key);
    }

    public Base get(int key) {
        return hashMap.get(key);
    }
}
