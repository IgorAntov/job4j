package ru.job4j.ocp;

import ru.job4j.ocp.engineeractions.Cos;
import ru.job4j.ocp.engineeractions.Sin;
import ru.job4j.srp.Actions;
import ru.job4j.srp.input.Input;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ActionExt extends Actions {

    protected Input input;

    ActionExt(Input input) {
        super(input);
        this.input = input;
        fillNewActions();
    }

    /**
     * Method adds new arithmetic operations to the Actions List
     */
    private void fillNewActions() {
        super.getActions().remove(4);
        super.getActions().add(new Cos(4, "Cos(x)", calculator, input));
        super.getActions().add(new Sin(5, "Sin(x)", calculator, input));
        super.getActions().add(new Exit(6, "Exit", calculator, input));

    }
}
