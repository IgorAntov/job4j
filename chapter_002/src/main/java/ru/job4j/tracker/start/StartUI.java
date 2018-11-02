package ru.job4j.tracker.start;

import ru.job4j.tracker.storage.ITracker;
import ru.job4j.tracker.action.MenuTracker;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.storage.Tracker;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    private Input input;
    private ITracker tracker;

    public StartUI(Input input, ITracker tracker) {
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
        ITracker tracker = new Tracker();
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(input, tracker).init();
    }
}
