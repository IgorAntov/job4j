package ru.job4j.isp;

import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConsolInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String s) {
        System.out.println(s);
        return scanner.next();
    }
}
