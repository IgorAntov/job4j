package ru.job4j.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleArray<T> implements Iterable<T> {

    private T[] arraySimple;
    private int position = 0;

    public SimpleArray(int arraySize) {
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
        if (index >= getSize()) {
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
        if (index >= position) {
            throw new NoSuchElementException();
        }
        System.arraycopy(this.arraySimple, index + 1, this.arraySimple, index, arraySimple.length - index - 1);
        arraySimple[arraySimple.length - 1] = null;
        position--;
        return true;
    }

    /**
     * Method return array size
     * @return
     */
    public int getSize() {
        return position;
    }


    /**
     * Replace existing Object in Array
     * @param index
     * @param model
     * @return
     */
    public boolean set(int index, T model) {
        if (index >= position) {
            throw new NoSuchElementException();
        }
        arraySimple[index] = model;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int indexIterator = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (indexIterator < getSize()) {
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
