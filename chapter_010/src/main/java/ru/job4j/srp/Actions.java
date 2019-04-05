package ru.job4j.srp;

import ru.job4j.srp.actions.*;
import ru.job4j.srp.input.Input;
import ru.job4j.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class Actions {
    private Input input;

    /**
     * Based on this class, expand the calculator.
     */
    protected Calculator calculator = new Calculator();
    private List<Action> action = new ArrayList<>();

    public Actions(Input input) {
        this.input = input;
        fillActionList();
    }

    /**
     * Method return calculated result.
     * @return result.
     */
    public double getResult() {
        return calculator.getResult();
    }

    /**
     * Method return actions list
     * @return actions list
     */
    public List<Action> getActions() {
        return action;
    }

    /**
     * Method fills actions list with arithmetic operations.
     */
    private void fillActionList() {
        action.add(new Addition(0, "Add", calculator, input));
        action.add(new Subtraction(1, "Sub", calculator, input));
        action.add(new Multiple(2, "Mul", calculator, input));
        action.add(new Division(3, "Div", calculator, input));
        action.add(new Exit(4, "Exit", calculator, input));
    }

    /**
     *
     The method prompts the user for an arithmetic operation number.
     * @return key operation number.
     */
    String selAction() {
        int key = input.askTask("Input action:", action);
        calc(key);
        return action.get(key).taskInfo();
    }

    /**
     * Method performs arithmetic operation.
     * @param key operation number.
     */
    private void calc(int key) {
        action.get(key).doAction();
    }

    /**
     * Extended class for Exit condition.
     */
   protected class Exit extends SimpleAction {
        public Exit(int key, String taskName, Calculator calculator, Input input) {
            super(key, taskName, calculator, input);
        }

        @Override
        public void doAction() {
        }
    }
}
