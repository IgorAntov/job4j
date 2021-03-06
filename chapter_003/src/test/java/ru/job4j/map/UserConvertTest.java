package ru.job4j.map;

import org.junit.Test;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserConvertTest {

    @Test
    public void when2UsersToHashMap() {
        UserConvert userConvert = new UserConvert();
        User user1 = new User(1, "Ivan", "Chelyabinsk");
        User user2 = new User(2, "Igor", "Moscow");
        List<User> users = List.of(user1, user2);
        Map<Integer, User> expect = Map.of(user1.getId(), user1, user2.getId(), user2);
        HashMap<Integer, User> result = userConvert.process(users);
        assertThat(expect, is(result));
    }
}
