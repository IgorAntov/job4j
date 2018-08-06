package ru.job4j.generic;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArrayTest {

    private Iterator<String> it;

    public SimpleArray<String> testStringArray = new SimpleArray(3);
    public SimpleArray<Integer> testIntegerArray = new SimpleArray(3);

    @Before
    public void setUp() {
        it = testStringArray.iterator();
    }

    @Test
    public void addGetObjectsStringInFromArray() {
        testStringArray.add("test1");
        testStringArray.add("test2");
        testStringArray.add("test3");
        assertThat(testStringArray.get(0), is("test1"));
        assertThat(testStringArray.get(1), is("test2"));
        assertThat(testStringArray.get(2), is("test3"));
    }

    @Test
    public void addNewObjectIntegerInArray() {
        testIntegerArray.add(1);
        testIntegerArray.add(2);
        testIntegerArray.add(3);
        assertThat(testIntegerArray.get(0), is(1));
        assertThat(testIntegerArray.get(1), is(2));
        assertThat(testIntegerArray.get(2), is(3));
    }


    @Test
    public void deleteObjectFromArray() {
        testStringArray.add("test1");
        testStringArray.add("test2");
        testStringArray.add("test3");
        testStringArray.delete(0);
        assertThat(testStringArray.get(0), is("test2"));
        assertThat(testStringArray.get(1), is("test3"));
   //     assertThat(testStringArray.get(2), is((String) null));
    }

     @Test
    public void setReplaceObjectInArray() {
         testStringArray.add("test1");
         testStringArray.add("test2");
         testStringArray.add("test3");
         testStringArray.set(2, "test4");
         assertThat(testStringArray.get(0), is("test1"));
         assertThat(testStringArray.get(1), is("test2"));
         assertThat(testStringArray.get(2), is("test4"));
    }


    @Test(expected = NoSuchElementException.class)
    public void iteratorSimplyArray() {
        testStringArray.add("test1");
        testStringArray.add("test2");
        testStringArray.add("test3");
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test1"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test2"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("test3"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void notObjectInArray() {
        testIntegerArray.get(3);
    }
}
