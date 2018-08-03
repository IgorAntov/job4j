package ru.job4j.generic;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 * Interface that define general methods to operate the SimpleArray
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
