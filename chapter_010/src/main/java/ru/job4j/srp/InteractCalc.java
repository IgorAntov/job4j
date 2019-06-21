package ru.job4j.srp;

import ru.job4j.srp.actions.Action;
import ru.job4j.srp.input.ConsoleInput;
import ru.job4j.srp.input.Input;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class InteractCalc {
    protected Actions actions;

    /**
     * Method initializes the interface IO.
     * @param input InputStream
     */
    public void init(Input input) {
        this.actions = new Actions(input);
    }

    /**
     * Method outputs user interface menu to output stream.
     */
    private void showMenu() {
        System.out.println("--------------------");
        System.out.println(String.format("Result = %f", actions.getResult()));
        System.out.println("Actions list:");
        for (Action a: actions.getActions()) {
            System.out.println(String.format("%d. %s", a.key(), a.taskInfo()));
        }
    }

    /**
     *  Method executes selected action.
     */
    public void userInterface() {
        String task;
        do {
            showMenu();
            task = actions.selAction();
        } while (!task.equals("Exit"));

    }

    public static void main(String[] args) {
        InteractCalc interactCalc = new InteractCalc();
        interactCalc.init(new ConsoleInput(System.in));
        interactCalc.userInterface();
    }
}