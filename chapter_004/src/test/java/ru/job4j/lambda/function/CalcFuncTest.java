package ru.job4j.lambda.function;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CalcFuncTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        CalcFunc calcFunc = new CalcFunc();
        List<Double> result = new ArrayList<>(Arrays.asList(11D, 13D, 15D));
        Assert.assertThat(calcFunc.diapason(5, 7, x -> x * 2 + 1), is(result));
    }

    @Test
    public void whenSqrFunctionThenSqrResults() {
        CalcFunc calcFunc = new CalcFunc();
        List<Double> result = new ArrayList<>(Arrays.asList(4D, 9D, 16D));
        Assert.assertThat(calcFunc.diapason(2, 4, x -> x * x), is(result));
    }

    @Test
    public void whenLogFunctionThenLogResults() {
        CalcFunc calcFunc = new CalcFunc();
        List<Double> result = new ArrayList<>(Arrays.asList(Math.log(1), Math.log(2), Math.log(3)));
        Assert.assertThat(calcFunc.diapason(1, 3, Math::log), is(result));
    }
}