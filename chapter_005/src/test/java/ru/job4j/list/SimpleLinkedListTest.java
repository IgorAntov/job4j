package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> list;
    private Iterator<Integer> it;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
    }

    @Test
    public void whenGetSomeElements() {
        assertThat(list.get(0), is(7));
        assertThat(list.get(6), is(1));
    }

    @Test
    public void whenCreateIterator() {
        it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(6));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenListModifiedOnIteratorHasNextThrowException() {
        it = list.iterator();
        list.add(8);
        it.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenListModifiedOnIteratorNextThrowException() {
        it = list.iterator();
        list.add(8);
        it.next();
    }
}
