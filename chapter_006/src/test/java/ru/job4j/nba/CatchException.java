package ru.job4j.nba;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;
import static org.hamcrest.Matchers.is;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CatchException {
    @Test
    public void whenThrowOptimisticException() throws InterruptedException {
        NbaHashMap nbaHashMap = new NbaHashMap();
        nbaHashMap.add(0, new Base("task1"));
        nbaHashMap.add(1, new Base("task2"));
        nbaHashMap.add(2, new Base("task3"));
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        Base task = nbaHashMap.get(1);
                        Thread.sleep(200);
                        task.setName("newTask");
                        nbaHashMap.update(1, task);

                    } catch (OptimisticException  | InterruptedException e) {
                        System.out.println("e");
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        Base task = new Base("newTask-2");
                        Base base = nbaHashMap.get(1);
                        task.setVersion(base.getVersion());
                        Thread.sleep(500);
                        nbaHashMap.update(1, task);
                    } catch (OptimisticException | InterruptedException e) {
                        ex.set(e);
                    }
                }
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("OptimisticException"));
    }
}
