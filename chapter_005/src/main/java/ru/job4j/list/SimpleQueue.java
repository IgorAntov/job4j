package ru.job4j.list;

import java.util.LinkedList;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleQueue<T> {

    LinkedList<T> simpleQueue = new LinkedList<>();

    /**
     * Method pulls new Object to LinkedArray
     */
    public T poll() {
        T data = simpleQueue.getLast();
        simpleQueue.removeLast();
        return data;
    }

    /**
     * Method add new Object to Linked Array
     * @param value
     */
    public void push(T value) {
        simpleQueue.push(value);
    }
}
