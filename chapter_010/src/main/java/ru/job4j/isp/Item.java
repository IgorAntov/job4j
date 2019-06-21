package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Item {

    private String title;
    private List<Item> children = new ArrayList<>();

    public Item(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }

    public List<Item> getChildren() {
        return children;
    }

    public boolean eqValue(Item that) {
        return this.title.compareTo(that.getTitle()) == 0;
    }

    public List<Item> leaves() {
        return this.children;
    }

    @Override
    public String toString() {
        return "Item{"
                + "title='" + title + '\'';
    }
}
