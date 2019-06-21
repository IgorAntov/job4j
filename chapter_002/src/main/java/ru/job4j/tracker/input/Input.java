package ru.job4j.tracker.input;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Input {
    /**
     * Метод реализующий зпрос к пользователю.
     */
    String ask(String question);

    int ask(String question, List<Integer> range);
}
