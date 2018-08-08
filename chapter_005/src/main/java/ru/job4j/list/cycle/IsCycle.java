package ru.job4j.list.cycle;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class IsCycle {

    /**
     * Method defines cycling in Node set
     * @param first
     * @return
     */
    boolean hasCycle(Node first) {
        boolean result = false;
        Node next = first.next;
        Node current;
            while (next != first && next != null) {
                current = next;
                next = current.next;
                if (first == next) {
                    result = true;
                }
        }
        return result;
    }
}
