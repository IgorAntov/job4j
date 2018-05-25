package ru.job4j.tracker;

import ru.job4j.start.ConsoleInput;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ValidateInput implements Input  {


    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;

            } catch (NumberFormatException nfe) {
                System.out.println("Введите корректные данные.");
            } catch (MenuOutException moe) {
                System.out.println("Введите значение из диаппазона меню.");
            }

        } while (invalid);
        return value;
    }

}
