package ru.job4j.map;

import org.junit.Test;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMapTest {

    @Test
    public void simpleHashMapTest() {

        class Key {
            private int i;
            public Key(int i) {
                this.i = i;
            }
            @Override
            public int hashCode() {
                return i;
            }
        }

        SimpleHashMap<Key, Integer> simpleHashMap = new SimpleHashMap<>();
        Key first = new Key(1);
        assertThat(simpleHashMap.insert(first, 1), is(true));
        Key second = new Key(2);
        assertThat(simpleHashMap.insert(second, 2), is(true));
        simpleHashMap.insert(new Key(3), 3);
        simpleHashMap.insert(new Key(4), 4);
        simpleHashMap.insert(new Key(5), 5);
        simpleHashMap.insert(new Key(6), 6);
        simpleHashMap.insert(new Key(7), 7);
        simpleHashMap.insert(new Key(8), 8);
        simpleHashMap.insert(new Key(9), 9);
        assertThat(simpleHashMap.delete(first), is(true));
        assertThat(simpleHashMap.size(), is(8));
        assertThat(simpleHashMap.get(second), is(2));

        Iterator<Key> it = simpleHashMap.iterator();
        assertThat(it.next().i, is(2));
        assertThat(it.next().i, is(3));
        assertThat(it.next().i, is(4));
        assertThat(it.next().i, is(5));
        assertThat(it.next().i, is(6));
        assertThat(it.next().i, is(7));
        assertThat(it.next().i, is(8));
        assertThat(it.next().i, is(9));
        assertThat(it.hasNext(), is(false));
    }
}

