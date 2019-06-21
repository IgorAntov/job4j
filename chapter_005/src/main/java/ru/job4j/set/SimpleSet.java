package ru.job4j.set;

import ru.job4j.generic.SimpleArray;
import java.util.Iterator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleSet<T> implements Iterable<T> {

    private final SimpleArray<T> set;

    public SimpleSet(int size) {
        set = new SimpleArray<>(size);
    }

    /**
     * Method adds new Object to List
     */
    public boolean add(T model) {
        Iterator<T> it = set.iterator();
        boolean isObj = false;
        boolean result = false;
        while (it.hasNext()) {
            T temp = it.next();
            if (model.equals(temp)) {
                isObj = true;
                break;
            }
        }
        if (!isObj) {
            set.add(model);
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
