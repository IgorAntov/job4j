package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable {

    protected int arraySize = 0;
    private T[] arraySimple;
    private int position = 0;

    public SimpleArray(int arraySize) {
        this.arraySize = arraySize;
        this.arraySimple = (T[]) new Object[arraySize];
    }

    /**
     * Add new Object to Array
     * @param model
     * @return
     */
    public boolean add(T model) {
        arraySimple[position++] = model;
        return true;
    }

    /**
     * Get existing Object from Array
     * @param index
     * @return
     */
    public T get(int index) {
        if (index >= arraySize) {
            throw new NoSuchElementException();
        }
        return arraySimple[index];
    }

    /**
     * Delete existing Object from Array
     * @param index
     * @return
     */
    public boolean delete(int index) {
        boolean result = false;
        if (index >= arraySize) {
            throw new NoSuchElementException();
        }
        for (int i = 0; i < arraySize; i++) {
            if (i == index) {
                if (i != position - 1) {
                    arraySimple[i] = arraySimple[i + 1];
                    index++;
                } else {
                    arraySimple[i] = null;
                    position--;
                }
            }
            result = true;
        }
        return result;
    }

    /**
     * Replace existing Object in Array
     * @param index
     * @param model
     * @return
     */
    public boolean set(int index, T model) {
        if (index >= arraySize) {
            throw new NoSuchElementException();
        }
        arraySimple[index] = model;
        return true;
    }

    @Override
    public Iterator iterator() {
        return new Iterator<T>() {

            int indexIterator = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (indexIterator < arraySize) {
                    result = true;
                }
                return result;
            }

            @Override
            public T next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return arraySimple[indexIterator++];
            }
        };
    }
}
