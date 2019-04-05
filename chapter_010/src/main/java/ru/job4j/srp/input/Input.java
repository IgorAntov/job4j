package ru.job4j.srp.input;

import ru.job4j.srp.actions.Action;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public interface Input {

    int askTask(String s, List<Action> range);

    double askValues(String s);
}
