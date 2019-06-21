package ru.job4j.chat.input;

import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput  implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask() {
        return scanner.nextLine();
    }
}