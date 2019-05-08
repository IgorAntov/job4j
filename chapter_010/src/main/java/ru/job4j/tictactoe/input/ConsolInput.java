package ru.job4j.tictactoe.input;

import ru.job4j.tictactoe.input.Input;

import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConsolInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public int ask(String s) {
        System.out.println(s);
        return scanner.nextInt();
    }
}
