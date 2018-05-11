package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.MenuTracker;
import ru.job4j.tracker.Tracker;

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
        int key;
        do {
            menu.show();
            key = Integer.valueOf(input.ask("Введите пункт меню :"));
            if (key < 6) {
                menu.select(key);
            }
        } while (!"6".equals(String.valueOf(key)));
    }

    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI(input, tracker).init();
    }
}
