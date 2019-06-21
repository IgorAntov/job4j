package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int indexRow = 0;
    private int indexCol = 0;

    public MatrixIterator(final int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return  values.length > indexRow;
    }

    @Override
    public Object next() {
        int value;
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        if (values[indexRow].length - 1 == indexCol) {
            int i = indexCol;
            indexCol = 0;
            value = values[indexRow++][i];
        } else {
            value = values[indexRow][indexCol++];
        }
        return value;
    }
}
