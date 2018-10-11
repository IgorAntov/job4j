package ru.job4j.pool.emailnotification;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public void emailTo(User user) {
        pool.submit(new Runnable() {
            @Override
            public void run() {
                String subject = String.format("Notification %s to email %s", user.getUsername(), user.getEmail());
                String body = String.format("Add a new event to %s", user.getUsername());
                send(subject, body, user.getEmail());
            }
        });
    }

    public void close() {
        pool.shutdown();
        if (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
        System.out.print(subject);
    }
}
