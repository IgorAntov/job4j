package ru.job4j.chat.start;

import ru.job4j.chat.Chat;
import ru.job4j.chat.input.ConsoleInput;
import ru.job4j.chat.input.Input;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StartChat {
    private Input input;

    public StartChat(Input input) {
        this.input = input;
    }

    public void init() {
        Chat chat = new Chat();
        chat.init();
        do {
            chat.readLine(input.ask());
        } while (Chat.isExit());
    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        new StartChat(input).init();
    }
}
