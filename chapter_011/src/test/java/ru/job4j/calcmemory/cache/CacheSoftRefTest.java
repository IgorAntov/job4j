package ru.job4j.calcmemory.cache;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CacheSoftRefTest {

    @Test
    public void whenPutNamesThenGetNamesList() {
        CacheSoftRef cacheSoftRef = new CacheSoftRef();
        Assert.assertThat(cacheSoftRef.getFromCache("Names.txt"), is("igor\nivan\nanton\n"));
    }
}