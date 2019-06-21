package ru.job4j.ocp;

import ru.job4j.srp.InteractCalc;
import ru.job4j.srp.input.ConsoleInput;
import ru.job4j.srp.input.Input;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 *
 * Сhild class extending interactive calculator to engineering
 */
public class EngineerCalc extends InteractCalc {

    @Override
    public void init(Input input) {
        super.actions =  new ActionExt(input);
    }

    public static void main(String[] args) {
        EngineerCalc engineerCalc = new EngineerCalc();
        engineerCalc.init((new ConsoleInput(System.in)));
        engineerCalc.userInterface();
    }
}
