package ru.job4j.generic;
/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 * Class User extends Class Base and includes field "role"
 */
public class Role extends Base {

    private String role;

    protected Role(String id, String role) {
        super(id);
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
