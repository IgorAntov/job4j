package ru.job4j;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class CalculatorTest {

    /**
     * Test echo.
     */

    @Test
    public void  whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expect = 2D;
        assertThat(result, is(expect));
    }

    @Test
    public void  whenSubtractOnesubOneThenTwo() {
        Calculator calc = new Calculator();
        calc.subtract(10D, 5D);
        double result = calc.getResult();
        double expect = 5D;
        assertThat(result, is(expect));
    }

    @Test
    public void  whenDivOneDivOneThenTwo() {
        Calculator calc = new Calculator();
        calc.div(10D, 5D);
        double result = calc.getResult();
        double expect = 2D;
        assertThat(result, is(expect));
    }

    @Test
    public void  whenMultipleOneMultipleOneThenTwo() {
        Calculator calc = new Calculator();
        calc.multiple(5D, 2D);
        double result = calc.getResult();
        double expect = 10D;
        assertThat(result, is(expect));
    }
}
