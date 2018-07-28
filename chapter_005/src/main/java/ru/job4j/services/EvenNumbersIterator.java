package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class EvenNumbersIterator implements Iterator {

    private final int[] values;
    private int index = 0;
    private int indexHasNext = 0;

    public EvenNumbersIterator(final int[] values) {
        this.values = values;
    }

    private boolean isNext() {
        boolean result = false;
        for (int i = indexHasNext; values.length > i; i++) {
            if ((values[i] % 2) == 0) {
                result = true;
                index = i;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return isNext();
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (isNext()) {
            indexHasNext = index + 1;
        }
        return values[index];
    }
}
