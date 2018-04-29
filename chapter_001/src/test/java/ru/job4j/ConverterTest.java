package ru.job4j;

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

public class ConverterTest {

    /**
     * Test converter Ruble to Dollar.
     */
    @Test
    public void when60RubleToDollarThenGetRouble() {
        Converter converter = new Converter();
          int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    /**
     * Test converter Ruble to Euro.
     */
     @Test
    public void when70RubleToEuroThenGetRouble() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    /**
     * Test converter Dollar to Ruble.
     */
    @Test
    public void when60RubleToDollarThenGetEuro() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        assertThat(result, is(60));
    }

    /**
     * Test converter Ruble to Euro.
     */
    @Test
    public void when70RubleToEuroThenGetDollar() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(1);
        assertThat(result, is(70));
    }

}
