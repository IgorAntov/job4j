package ru.job4j.srp.input;

import ru.job4j.srp.actions.Action;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 *
 * Class makes a request to input data based on InputStream.
 */
public class ConsoleInput implements Input {

    private Scanner scanner;

    public ConsoleInput(InputStream inputStream) {
        this.scanner = new Scanner(inputStream);
    }

    /**
     * The method requests user input to select a specific arithmetic operation.
     * @param s invitation string.
     * @param range actions list range.
     * @return numbere of arithmetic operation.
     */
    @Override
    public int askTask(String s, List<Action> range) {
        System.out.println(s);
        int key = -1;
        boolean invalid = true;
        do {
            try {
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        key = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Please enter a valid number.");
                        scanner.next();
                    }
                }
                invalid = !validateTask(key, range);
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
        } while (invalid);
        return key;
    }

    /**
     * The method requests user input of operands.
     * @param s invitation string.
     * @return operand
     */
    @Override
    public double askValues(String s) {
        System.out.println(s);
        double number = -1;
        while (scanner.hasNext()) {
            if (scanner.hasNextDouble()) {
                number = scanner.nextDouble();
                break;
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next();
            }
        }
        return number;
    }

    /**
     * The method validates the selected operation.
     * @param key number arithmetic operation in actions list
     * @param range action list range.
     * @return validation result.
     */
    private boolean validateTask(int key, List<Action> range) {
        boolean exist = false;
        for (Action value: range) {
            if (value.key() == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            System.out.println("Out of range of operation.");
        }
        return exist;
    }
}

