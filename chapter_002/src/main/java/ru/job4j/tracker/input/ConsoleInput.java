package ru.job4j.tracker.input;

import ru.job4j.tracker.exception.MenuOutException;

import java.util.List;
import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);
    /**
     * Метод реализующий зпрос к пользователю
     * @param question вопрос к пользователю
     * @return результат ввода
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.next();
    }

    public int ask(String question, List<Integer> range) {

        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value: range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Выход за пределы диапазона значений.");
        }
        return key;
    }
}
