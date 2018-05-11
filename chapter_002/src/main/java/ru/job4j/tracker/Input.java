package ru.job4j.tracker;

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
}
