package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleDynamicArray<E> implements Iterable<E>   {


    private int capacity = 5;
    private int index;
    private int modCount;
    private E[] arraySimple = (E[]) new Object[capacity];

    /**
     * Method adds new Object to SimpleArray.
     * When SimpleArray is filled then SimpleArray capacity increases by five.
     * @param value
     * @return
     */
    public boolean add(E value) {
        if (this.index >= capacity) {
            resize();
        }
        arraySimple[this.index++] = value;
        modCount++;
        return true;
    }

    public void resize() {
        E[] tempArray;
        tempArray = arraySimple;
        capacity += 5;
        arraySimple =  (E[]) new Object[capacity];
        System.arraycopy(tempArray, 0, this.arraySimple, 0, index);
    }

/**
 * Method gets Object from SimpleArray
 */
    public E get(int index) {
        return arraySimple[index];
    }

    /**
     * Method create iterator.
     * Iterator tracks simpleArray modification and throw exception if array is changed
     * @return
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int indexIterator = 0;


            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                if (indexIterator < capacity) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return arraySimple[indexIterator++];
            }
        };
    }
}