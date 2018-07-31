package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable {

    public int arraySize = 0;
    public T[] arraySimple;
    int index = 0;

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
        arraySimple[index++] = model;
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
                for (int j = i + 1; j < arraySize; j++) {
                    arraySimple[j - 1] = arraySimple[j];
                }
                arraySimple[arraySize - 1] = null;
                result = true;
                break;
            }
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
