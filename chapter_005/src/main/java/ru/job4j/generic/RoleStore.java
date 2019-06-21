package ru.job4j.generic;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class RoleStore extends AbstractStore<Role> {

    public RoleStore(int size) {
        super(new SimpleArray<>(size));
    }
}
