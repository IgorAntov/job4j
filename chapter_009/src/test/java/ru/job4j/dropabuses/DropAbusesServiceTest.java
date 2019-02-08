package ru.job4j.dropabuses;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DropAbusesServiceTest {
    @Test
    public void dropAbusesServiceTest() {
        String[] abuse = new String[] {"one", "three"};
        DropAbusesService dropAbusesService = new DropAbusesService();
        System.setIn(new ByteArrayInputStream("one two three".getBytes()));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        dropAbusesService.dropAbuses(System.in, byteArrayOutputStream, abuse);
        assertThat(byteArrayOutputStream.toString(), is(" two "));
    }
}