package ru.job4j.calcmemory.profiling;

import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storage.ITracker;
import ru.job4j.tracker.storage.Tracker;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Profiling {


    public static void main(String[] args) {
        ITracker tracker = new Tracker();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Adding items to List.");
        int size = 10000;
        for (int i = 0; i < size; i++) {
            Item item = new Item("test name " + i, "desc" + i);
            tracker.add(item);
        }
        tracker.findAll().clear();
        for (int i = 0; i < size; i++) {
            Item item = new Item("test name " + i, "desc" + i);
            tracker.add(item);
        }
        System.out.println("Items were added to the List.");
        try {
            Thread.currentThread().sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
