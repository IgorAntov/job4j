package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void beforeTest() {
        stack = new SimpleStack<>();

    }

    @Test
    public void whenPushPullSomeElementsToStack() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertThat(stack.poll(), is(3));
        stack.push(4);
        assertThat(stack.poll(), is(4));
        assertThat(stack.poll(), is(2));
    }
}
