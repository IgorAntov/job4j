package ru.job4j.monitor.arrays;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleLinkedList;

import java.util.Iterator;


@ThreadSafe
public class MtLinkedList<E> {

    @GuardedBy("this")
    private final SimpleLinkedList<E> array = new SimpleLinkedList<>();

    synchronized public boolean add(E value) {
        this.array.add(value);
        return true;
    }

    synchronized public E get(int index) {
        return this.array.get(index);
    }

    synchronized public Iterator<E> iterator() {
        return array.iterator();
    }
}
