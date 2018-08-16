package ru.job4j.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetTest {

    @Test
    public void addObjectsStringInSet() {
        SimpleSet<String> simpleSet = new SimpleSet<>(5);
        Iterator it;

        simpleSet.add("test1");
        simpleSet.add("test2");
        simpleSet.add("test3");
        simpleSet.add("test2");

        it = simpleSet.iterator();
        assertThat(it.next(), is("test1"));
        assertThat(it.next(), is("test2"));
        assertThat(it.next(), is("test3"));
        assertThat(it.hasNext(), is(false));
    }
}
