package ru.job4j.lambda.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CalcFunc {

    List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            result.add(func.apply((double) i));
        }
        return result;
    }
}
