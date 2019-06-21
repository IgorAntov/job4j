package ru.job4j.bytestream;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ByteStreamTest {

    @Test
    public void byteStreamTest() {
        ByteStream byteStream = new ByteStream();
        System.setIn(new ByteArrayInputStream("10".getBytes()));
        boolean isnumber  = byteStream.isNumber(System.in);
        assertThat(isnumber, is(true));
        System.setIn(new ByteArrayInputStream("11".getBytes()));
        isnumber  = byteStream.isNumber(System.in);
        assertThat(isnumber, is(false));
    }
}