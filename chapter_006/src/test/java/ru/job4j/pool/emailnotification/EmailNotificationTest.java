package ru.job4j.pool.emailnotification;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class EmailNotificationTest {

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
    public void test() throws InterruptedException {
        EmailNotification emailNotification = new EmailNotification();
        User user1 = new User("User1", "user1@email.com");
        emailNotification.emailTo(user1);
        Thread.sleep(100);
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Notification User1 to email user1@email.com")
                                .toString()
                )
        );
        emailNotification.close();
    }
}
