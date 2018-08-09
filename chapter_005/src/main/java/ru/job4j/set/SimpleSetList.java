package ru.job4j.set;

import ru.job4j.list.SimpleLinkedList;
import java.util.Iterator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleSetList<E> implements Iterable<E> {

    private SimpleLinkedList<E> set = new SimpleLinkedList<>();

    /**
     * Method adds new Object to List
     */
    public boolean add(E model) {

        Iterator<E> it = set.iterator();
        boolean isObj = false;
        boolean result = false;

        while (it.hasNext()) {
            E temp = it.next();
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
    public Iterator<E> iterator() {
        return set.iterator();
    }

}
