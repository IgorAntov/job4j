package ru.job4j.tracker.action;

import ru.job4j.tracker.storage.ITracker;
import ru.job4j.tracker.input.Input;

import java.util.function.Consumer;

public abstract class BaseAction implements UserAction {

    private final int key;
    private final String name;

    BaseAction(final int key, final String name) {
        this.key = key;
        this.name = name;
    }

    public int key() {
        return this.key;
    }

    public abstract void execute(Input input, ITracker tracker, Consumer<String> output);

    public String info() {
        return String.format("%s. %s\n", this.key(), this.name);
    }

}
