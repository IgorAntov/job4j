package ru.job4j.tracker;

import ru.job4j.start.ConsoleInput;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput extends ConsoleInput {

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;

            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректные данные");
            } catch (MenuOutException moe) {
                System.out.println("Введите значение из диаппазона меню");
            }

        } while (invalid);
        return value;
    }

}
