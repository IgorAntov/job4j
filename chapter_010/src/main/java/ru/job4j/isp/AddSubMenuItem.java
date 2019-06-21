package ru.job4j.isp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface AddSubMenuItem {
    /**
     * Method adds child item menu to the MenuTree.
     * @param parent - parent item.
     * @param child - child item.
     * @return
     */
    boolean addItem(Item parent, Item child);
}
