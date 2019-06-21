package ru.job4j.tictactoe.input;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    /**
     * Method requests user input to choose menu item.
     * @param s invitation.
     * @return input result.
     */
    int ask(String s);
}
