package ru.job4j.monitor;

import org.junit.Test;
import ru.job4j.monitor.arrays.MtArrayList;

import java.util.Iterator;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MtLinkedListTest {

    @Test
    public void mtLinkedListArrayTestMethods() {
        MtArrayList<Integer> mtLinkedList = new MtArrayList<>();
        mtLinkedList.add(4);
        mtLinkedList.add(5);
        mtLinkedList.add(6);
        assertThat(mtLinkedList.get(2), is(6));
        Iterator it = mtLinkedList.iterator();
        assertThat(it.next(), is(4));
    }
}