package ru.job4j.start;

import ru.job4j.tracker.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private Input input;
    private Tracker tracker;

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() {
        MenuTracker menu = new MenuTracker(this.input, tracker);
        menu.fillaction();
        do {
            menu.show();
                menu.select(input.ask("Введите пункт меню :", menu.getRange()));
          } while (menu.exit());
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, tracker).init();
    }
}
