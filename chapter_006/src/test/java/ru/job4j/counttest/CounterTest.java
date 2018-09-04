package ru.job4j.counttest;

import org.junit.Test;
import ru.job4j.monitor.storage.User;
import ru.job4j.monitor.storage.UserStorage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CounterTest {

    @Test
    public void guardianIsOn() throws InterruptedException {
        UserStorage userStorage = new UserStorage();
        userStorage.add(new User(1, 10000));
        userStorage.add(new User(2, 0));
        class Run implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 3000; i++) {
                    userStorage.transfer(1, 2, 1);
                }
            }
        }
        Thread t1 = new Thread(new Run());
        t1.start();
        Thread t2 = new Thread(new Run());
        t2.start();
        t1.join();
        t2.join();
        assertThat(userStorage.getUser(2).getAmount(), is(6000));
    }
}
