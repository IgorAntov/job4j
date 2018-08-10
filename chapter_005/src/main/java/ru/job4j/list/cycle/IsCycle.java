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
        Node pointerOwn, pointerTwo;
        pointerOwn = first;
        pointerTwo = first;

        while(true) {
            if(first == null) {
                break;
            }
            pointerOwn = pointerOwn.next;
            if(pointerTwo.next != null) {
            pointerTwo = pointerTwo.next.next;
            }
            else {
                result = false;
                break;
            }
            if(pointerOwn == null || pointerTwo == null) {
                result = false;
                break;
            }
            if(pointerOwn == pointerTwo) {
                result = true;
                break;
            }
        }
        return result;
    }

}
