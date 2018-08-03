package ru.job4j.generic;
/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 * Class User extends Class Base and includes field "name"
 */
public class User extends Base {

    private String name;

    protected User(String id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
