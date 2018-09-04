package ru.job4j.arrays;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MtArrayListTest {

    @Test
    public void mtArrayTestMethods() {
       MtArrayList<Integer> mtArrayList = new MtArrayList<>();
       mtArrayList.add(1);
       mtArrayList.add(2);
       mtArrayList.add(3);
       assertThat(mtArrayList.get(2), is(3));
       Iterator it = mtArrayList.iterator();
       assertThat(it.next(), is(1));
    }
}