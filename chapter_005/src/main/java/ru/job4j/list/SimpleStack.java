package ru.job4j.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleStack<T> {

    SimpleArrayList<T> simpleStack = new SimpleArrayList<>();

    /**
     * Method pulls new Object to LinkedArray
     */
    public T poll() {
        T data = simpleStack.get(0);
        simpleStack.delete();
        return data;
    }

    /**
     * Method add new Object to Linked Array
     * @param value
     */
    public void push(T value) {
        simpleStack.add(value);

    }

    public T get(int index) {
        return simpleStack.get(index);
    }
}
