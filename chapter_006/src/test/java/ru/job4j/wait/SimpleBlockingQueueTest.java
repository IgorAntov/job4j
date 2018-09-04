package ru.job4j.wait;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void simpleBlockingQueueTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>();
        Producer<Integer> producer = new Producer(simpleBlockingQueue, 1);
        Consumer<Integer> consumer = new Consumer(simpleBlockingQueue);
        producer.start();
        producer.join();
        consumer.start();
        consumer.join();
    }
}