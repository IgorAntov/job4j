package ru.job4j.isp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface MainItem {
    /**
     * Method adds child MenuItem to the Root of the MenuTree.
     * @param mainMenuItem root MenuTree
     * @return
     */
    boolean addItem(Item mainMenuItem);
}
