package ru.job4j.wait;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Consumer<T> extends Thread {
    private SimpleBlockingQueue<T> simpleBlockingQueue;
    private List<T> array = new ArrayList<>();

    public Consumer(SimpleBlockingQueue<T> simpleBlockingQueue) {
        this.simpleBlockingQueue = simpleBlockingQueue;
    }

    @Override
    public void run() {
        try {
            array.add(simpleBlockingQueue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
