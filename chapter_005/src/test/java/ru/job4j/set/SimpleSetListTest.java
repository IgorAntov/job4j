package ru.job4j.set;

import org.junit.Test;
import java.util.Iterator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetListTest {

    public SimpleSetList<String> simpleSetList = new SimpleSetList<>();
    public Iterator it;

    @Test
    public void addObjectsStringInSetLink() {
        simpleSetList.add("test1");
        simpleSetList.add("test2");
        simpleSetList.add("test3");
        simpleSetList.add("test4");
        simpleSetList.add("test3");

        it = simpleSetList.iterator();
        assertThat(it.next(), is("test4"));
        assertThat(it.next(), is("test3"));
        assertThat(it.next(), is("test2"));
        assertThat(it.next(), is("test1"));
        assertThat(it.hasNext(), is(false));
    }
}
