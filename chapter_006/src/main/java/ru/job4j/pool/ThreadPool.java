package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ThreadPool {

    public final List<Thread> thread = new LinkedList<>();
    public final Queue<Runnable> tasks = new LinkedBlockingQueue<>();

    public ThreadPool() {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < cores; i++) {
            thread.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (!Thread.currentThread().isInterrupted()) {
                            poll().run();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }));
        }
    }

    /**
     * Method runs all initialised threads
     */
    public void start() {
        int cores = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < cores; i++) {
            thread.get(i).start();
        }

    }

    /**
     * Method pulls task from task queue
     * @return
     * @throws InterruptedException
     */
    public Runnable poll() throws InterruptedException {
        synchronized (tasks) {
            while (tasks.isEmpty()) {
                tasks.wait();
            }
            return tasks.poll();
        }
    }

    /**
     * Method adds task to task queue
     * @param job
     */
    public void work(Runnable job) {
        synchronized (tasks) {
            tasks.add(job);
            tasks.notifyAll();
        }
    }

    /**
     * Method stops task running
     */
    public void shutdown() {
        for (Thread t: thread) {
            t.interrupt();
        }
    }
}
