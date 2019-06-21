package ru.job4j.srp.actions;

import ru.job4j.srp.input.Input;
import ru.job4j.Calculator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

/**
 * Arithmetic operation Division.
 */
public class Division extends SimpleAction {

    public Division(int key, String taskName, Calculator calculator, Input input) {
        super(key, taskName, calculator, input);
    }

    /**
     * Method performs arithmetic operation - division.
     * @param x
     * @param y
     * @return result
     */
    @Override
    public Double action(double x, double y) {
        calculator.div(x, y);
        return calculator.getResult();
    }
}
