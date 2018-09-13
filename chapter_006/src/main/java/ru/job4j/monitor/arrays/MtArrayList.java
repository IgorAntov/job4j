package ru.job4j.monitor.arrays;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.SimpleDynamicArray;

import java.util.Iterator;

@ThreadSafe
public class MtArrayList<E> {

    @GuardedBy("this")
    private final SimpleDynamicArray<E> array = new SimpleDynamicArray<>();
    volatile boolean doCopy;

    synchronized public boolean add(E value) {
        boolean result = false;
        if (this.array.add(value)) {
            result = true;
        }
        return result;
    }

    /**
     * Method gets object from array
     * @param index
     * @return
     */
    synchronized public E get(int index) {
        return this.array.get(index);
    }

    /**
     * Iterator in fail-save mode
     * @return
     */
    synchronized public Iterator<E> iterator() {
        return copy(this.array).iterator();
    }

    private SimpleDynamicArray<E> copy(SimpleDynamicArray<E> array) {
        SimpleDynamicArray<E> tempArray = new SimpleDynamicArray<>();
        synchronized (array) {
            try {
                if (doCopy) {
                    array.wait();
                }
                doCopy = true;
                Iterator<E> it = array.iterator();
                while (it.hasNext()) {
                    tempArray.add(it.next());
                }
                doCopy = false;
                array.notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return tempArray;
    }
}
