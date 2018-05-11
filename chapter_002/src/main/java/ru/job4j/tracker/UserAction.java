package ru.job4j.tracker;

public interface UserAction {

    public int key();

    public void execute(Input input, Tracker tracker);

    public String info();
}
