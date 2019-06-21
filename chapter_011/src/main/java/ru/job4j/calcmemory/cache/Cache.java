package ru.job4j.calcmemory.cache;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Cache {
    /**
     * Method searches SoftReference String in the HashMap by key.
     * @param key key
     * @return value
     */
    String getFromCache(String key);
}
