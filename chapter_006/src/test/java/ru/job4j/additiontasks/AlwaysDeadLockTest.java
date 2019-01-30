package ru.job4j.additiontasks;

import org.junit.Test;

/** Test
 *  Temporary exclude from project to allow build project with Travis CI
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class AlwaysDeadLockTest {
    @Test
    public void alwaysDeadLockTest() {
        AlwaysDeadLock alwaysDeadLock = new AlwaysDeadLock();
    /*
        try {
            alwaysDeadLock.startTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
     */
    }
}