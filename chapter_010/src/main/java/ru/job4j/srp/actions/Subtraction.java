package ru.job4j.srp.actions;

import ru.job4j.srp.input.Input;
import ru.job4j.Calculator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

/**
 * Arithmetic operation Subtraction.
 */
public class Subtraction extends SimpleAction {

    public Subtraction(int key, String taskName, Calculator calculator, Input input) {
        super(key, taskName, calculator, input);
    }

    /**
     *  Method performs arithmetic operation - Subtraction
     * @param x
     * @param y
     * @return result
     */
    @Override
    public Double action(double x, double y) {
        calculator.subtract(x, y);
        return calculator.getResult();
    }
}
