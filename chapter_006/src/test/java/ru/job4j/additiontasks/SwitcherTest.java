package ru.job4j.additiontasks;

import org.junit.Test;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SwitcherTest {
    @Test
    public void switcherTest() {
        Switcher switcher = new Switcher();
        try {
            switcher.startTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
