package ru.job4j.map;

//import jdk.nashorn.internal.ir.WhileNode;
import org.junit.Test;
import java.util.Iterator;
import java.util.Objects;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleHashMapTest {

    class Key {
        private int i;
        public Key(int i) {
            this.i = i;
        }

        @Override
        public int hashCode() {
            return i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Key key = (Key) o;
            return i == key.i;
        }

        public int getI() {
            return i;
        }
    }

    @Test
    public void simpleHashMapTest() {

               SimpleHashMap<Key, Integer> simpleHashMap = new SimpleHashMap<>();
        Key first = new Key(1);
        assertThat(simpleHashMap.insert(first, 1), is(true));
        for (int i = 2; i <= 20; i++) {
            simpleHashMap.insert(new Key(i), i);
        }
        assertThat(simpleHashMap.delete(first), is(true));
        assertThat(simpleHashMap.size(), is(19));
        Iterator<Key> it = simpleHashMap.iterator();
        int j = 2;
        while (it.hasNext()) {
            assertThat(it.next().i, is(j));
            j++;
        }
        SimpleHashMap<Object, Integer> simpleHashMap2 = new SimpleHashMap<>();
        simpleHashMap2.insert(new Object(), 1);
    }
}

