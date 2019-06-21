package ru.job4j.wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private int size;
    private final Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) {
        synchronized (this.queue) {
            while (this.queue.size() == size) {
                try {
                    System.out.println("Producer is waiting");
                    this.queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.queue.offer(value);
            System.out.println("Offered");
            this.queue.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        T value;
        synchronized (this.queue) {
            while (this.queue.size() == 0) {
                System.out.println("Consumer is waiting");
                this.queue.wait();
            }
            value = this.queue.poll();
            this.queue.notifyAll();
            System.out.println("Polled");
            return value;
        }
    }

    public boolean isEmpty() {
        synchronized (this.queue) {
            return queue.isEmpty();
        }
    }

}
