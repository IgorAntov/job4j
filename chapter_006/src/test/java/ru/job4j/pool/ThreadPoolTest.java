package ru.job4j.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ThreadPoolTest {
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    @Test
    public void whenRunTenTasksThenShutdownThreads() {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < 5; i++) {
            int fi = i;
            threadPool.work(new Runnable() {
                @Override
                public void run() {
                        System.out.print("TaskIsDone.");
                }
            });
        }
        threadPool.start();
        try {
            Thread.sleep(2000);
            threadPool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("TaskIsDone.TaskIsDone.TaskIsDone.TaskIsDone.TaskIsDone.")
                                .toString()
                )
        );
    }
}