package ru.job4j.wait;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Producer<T> extends Thread {
    private SimpleBlockingQueue<T> simpleBlockingQueue;
    private T prod;

    public Producer(SimpleBlockingQueue<T> simpleBlockingQueue, T prod) {
        this.simpleBlockingQueue = simpleBlockingQueue;
        this.prod = prod;
    }

    public void add(T value) {
        simpleBlockingQueue.offer(value);
    }

    @Override
    public void run() {
        this.add(this.prod);
    }
}
