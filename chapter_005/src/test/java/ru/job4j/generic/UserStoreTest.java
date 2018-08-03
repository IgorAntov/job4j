package ru.job4j.generic;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {

    @Test
    public void addNewStoreUserAndFindById() {
        UserStor users = new UserStor(5);
        users.add(new User("1", "Ivan"));
        users.add(new User("2", "Igor"));
        users.add(new User("3", "Oleg"));
        User user1 = new User("4", "Vasiliy");
        users.add(user1);
        assertThat(users.findById("2").toString(), is("Igor"));
    }

    @Test
    public void addNewStoreRoleAndFindById() {
        RoleStore roles = new RoleStore(5);
        roles.add(new Role("1", "Role1"));
        roles.add(new Role("2", "Role2"));
        roles.add(new Role("3", "Role3"));
        Role role1 = new Role("4", "Role4");
        roles.add(role1);
        assertThat(roles.findById("2").toString(), is("Role2"));
    }

    @Test
    public void deleteUserFromStoreArray() {
        UserStor users = new UserStor(5);
        users.add(new User("1", "Ivan"));
        users.add(new User("2", "Igor"));
        users.add(new User("3", "Oleg"));
        users.delete("2");
        User base = null;
        users.findById("2");
        assertThat(users.findById("2"), is(base));
    }



}
