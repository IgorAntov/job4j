package ru.job4j.list.cycle;

import ru.job4j.list.SimpleDynamicArray;

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

        SimpleDynamicArray<Node> nodeArray = new SimpleDynamicArray<>();

        boolean result = false;
        Node current = first;

        while (current.next != null && !result) {
            if (isNode(current.next, nodeArray)) {
                result = true;
                break;
            } else {
                nodeArray.add(current);
                current = current.next;
            }
        }
        return result;
    }

    public boolean isNode(Node node, SimpleDynamicArray<Node> nodeArray) {
        boolean result = false;
        for (Node value: nodeArray) {
            if (value != null && value.equals(node)) {
                result = true;
            }
        }
        return result;
    }
}
