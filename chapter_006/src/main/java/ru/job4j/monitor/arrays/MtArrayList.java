package ru.job4j.arrays;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleDynamicArray;

import java.util.Iterator;

@ThreadSafe
public class MtArrayList<E> {

    @GuardedBy("this")
    private final SimpleDynamicArray<E> array = new SimpleDynamicArray<>();

    synchronized public boolean add( E value) {
        boolean result = false;
        if (this.array.add(value)) {
            result = true;
        }
        return result;
    }

    synchronized public E get(int index) {
        return this.array.get(index);
    }

    synchronized public Iterator <E> iterator() {
        return array.iterator();
    }
}
