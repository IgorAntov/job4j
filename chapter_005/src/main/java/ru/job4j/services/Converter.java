package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Converter {

    boolean isNext = false;
    Iterator tempIterator;

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        tempIterator = it.next();

        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                isNext = false;
                if (tempIterator.hasNext()) {
                    isNext = true;
                } else {
                    if (it.hasNext()) {
                        tempIterator = it.next();
                        if (tempIterator.hasNext()) {
                            isNext = true;
                        }
                    }
                }
                return isNext;
            }

            @Override
            public Integer next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException();
                }
                return (Integer) tempIterator.next();
            }
        };
    }
}
