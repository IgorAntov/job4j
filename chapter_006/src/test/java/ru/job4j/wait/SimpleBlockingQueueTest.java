package ru.job4j.wait;

import org.junit.Test;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleBlockingQueueTest {

    @Test
    public void simpleBlockingQueueTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(2);
        Producer<Integer> producer1 = new Producer(simpleBlockingQueue, 1);
        Producer<Integer> producer2 = new Producer(simpleBlockingQueue, 2);
        Producer<Integer> producer3 = new Producer(simpleBlockingQueue, 3);
        Producer<Integer> producer4 = new Producer(simpleBlockingQueue, 4);
        Consumer<Integer> consumer = new Consumer(simpleBlockingQueue);
        consumer.start();
        producer1.start();
        producer2.start();
        producer3.start();
        producer4.start();
        consumer.sleep(5000);
        consumer.interrupt();
    }
}