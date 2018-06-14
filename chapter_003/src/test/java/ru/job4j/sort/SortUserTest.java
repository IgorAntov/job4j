package ru.job4j.sort;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SortUserTest {

    @Test
    public void whenSortByAge() {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("vadim", 25),
                new User("igor", 30),
                new User("vasiliy", 5),
                new User("andrey", 10)));

        List<User> expect = new ArrayList<>(Arrays.asList(
                new User("vasiliy", 5),
                new User("andrey", 10),
                new User("vadim", 25),
                new User("igor", 30)));

        SortUser sortUser = new SortUser();
        Set<User> result = sortUser.sort(users);
        assertThat(result.toString(), is(expect.toString()));
    }

    @Test
    public void whenSortByAllFields() {
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("Сергей", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Иван", 25)));

        List<User> expect = new ArrayList<>(Arrays.asList(
                new User("Иван", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Сергей", 25)));

        SortUser sortUser = new SortUser();
        List<User> result = sortUser.sortByAllFields(users);
        assertThat(result.toString(), is(expect.toString()));
    }

}
