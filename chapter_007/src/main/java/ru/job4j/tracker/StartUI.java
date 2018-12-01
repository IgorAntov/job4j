package ru.job4j.tracker;

import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.input.ValidateInput;
import ru.job4j.tracker.storage.ITracker;

public class StartUI {

    public static void main(String[] args) {
        ITracker tracker = new TrackerSQL();
        Input input = new ValidateInput(new ConsoleInput());
        new ru.job4j.tracker.start.StartUI(input, tracker).init();
    }
}
