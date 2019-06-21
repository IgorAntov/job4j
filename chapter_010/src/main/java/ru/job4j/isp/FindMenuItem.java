package ru.job4j.isp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface FindMenuItem {
    /**
     * Method searches item in the MenuTree
     * @param item
     * @return
     */
    Item findBy(Item item);
}
