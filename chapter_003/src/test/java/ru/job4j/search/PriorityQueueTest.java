package ru.job4j.search;
import org.junit.Test;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }
}
