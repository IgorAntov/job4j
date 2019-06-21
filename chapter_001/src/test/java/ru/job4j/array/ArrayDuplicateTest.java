package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ArrayDuplicateTest {

    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
       String[] input = new String[] {"Привет", "Мир", "Привет", "Супер", "Мир"};
       String[] result = new String[] {"Привет", "Мир", "Супер"};
       ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
       assertThat(arrayDuplicate.remove(input), is(result));
    }
}
