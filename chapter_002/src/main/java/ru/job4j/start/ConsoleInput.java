package ru.job4j.start;

import ru.job4j.tracker.Input;

import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {

    Scanner scanner = new Scanner(System.in);
    /**
     * Метод реализующий зпрос к пользователю
     * @param question вопрос к пользователю
     * @return результат ввода
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.next();
    }
}
