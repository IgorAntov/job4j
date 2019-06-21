package ru.job4j.ocp.engineeractions;

import ru.job4j.Calculator;
import ru.job4j.srp.actions.SimpleAction;
import ru.job4j.srp.input.Input;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */


public class Sin extends SimpleAction {

    private Calculator calculator;

    public Sin(int key, String taskName, Calculator calculator, Input input) {
        super(key, taskName, calculator, input);
        this.calculator = calculator;
    }

    /**
     * Method performs trigonometric operation - Cos.
     * @param x empty, zero
     * @param y argument
     * @return result
     */
    @Override
    public Double action(double x, double y) {
        calculator.add(0, Math.cos(y));
        return calculator.getResult();
    }

}
