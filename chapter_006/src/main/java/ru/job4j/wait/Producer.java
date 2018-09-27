package ru.job4j.wait;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Producer<T> extends Thread {
    private SimpleBlockingQueue<T> simpleBlockingQueue;
    private T value;

    public Producer(SimpleBlockingQueue<T> simpleBlockingQueue, T value) {
        this.simpleBlockingQueue = simpleBlockingQueue;
        this.value = value;
    }

    @Override
    public void run() {
        this.simpleBlockingQueue.offer(this.value);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
