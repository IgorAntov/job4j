package ru.job4j.tracker.action;

import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.storage.ITracker;
import ru.job4j.tracker.storage.Tracker;

public interface UserAction {

    public int key();

    public void execute(Input input, ITracker tracker);

    public String info();
}
