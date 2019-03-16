package ru.job4j.oracl;

import com.google.common.base.Joiner;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ServerTest {

    private static final String LN = System.getProperty("line.separator");

    private void testServer(String input, String excepted) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.init();
        assertThat(out.toString(), is(excepted));
    }

    @Test
    public void whenAskExitThenExitFromProgramme() throws IOException {
        this.testServer("exit", "");
    }

    @Test
    public void whenAskUnsupportedThenGotReplayIDoNotUnderstand() throws IOException {
        this.testServer(
                Joiner.on(LN).join("Unsupported ask", "exit"), String.format("I don't understand%s", LN)
        );    }

    @Test
    public void whenAskHelloThenGotReplayHiBuddy() throws IOException {
        this.testServer(
                Joiner.on(LN).join("Hello Oracle", "exit"), String.format("Hi buddy%s%s", LN, LN)
        );
    }
}