package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {

    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }

    @Test
    public void whenSecondLessFirst() {
        Max maxim = new Max();
        int result = maxim.max(3, 2);
        assertThat(result, is(3));
    }

    @Test
    public void whenSecondLessFirstAndThirdButFirstLessThird() {
        Max maxim = new Max();
        int result = maxim.max(3, 2,5);
        assertThat(result, is(5));
    }

    @Test
    public void whenFirstLessSecondAndThirdButThirdLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(3, 6,5);
        assertThat(result, is(6));
    }

    @Test
    public void whenThirdLessFirstAndSecondButSecondLessThird() {
        Max maxim = new Max();
        int result = maxim.max(8, 2,4);
        assertThat(result, is(8));
    }
}
