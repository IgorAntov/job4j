package ru.job4j.srp.actions;

import ru.job4j.srp.input.Input;
import ru.job4j.Calculator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

/**
 * This class is the superclass of all classes representing
 * an arithmetic operation.
 */
public class SimpleAction implements Action {
    private Input input;
    private final int key;
    private final String taskName;
    Calculator calculator;

    public SimpleAction(int key, String taskName, Calculator calculator, Input input) {
        this.key = key;
        this.taskName = taskName;
        this.calculator = calculator;
        this.input = input;
    }

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String taskInfo() {
        return this.taskName;
    }

    @Override
    public Double action(double x, double y) {
        return 0.0;
    }

    /**
     * This pre action use result an input value as the operands for arithmetic operation.
     */
    @Override
    public void doAction() {
        action(calculator.getResult(), this.input.askValues("Input value:"));
    }

}
