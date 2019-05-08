package ru.job4j.tictactoe.field;

import ru.job4j.tictactoe.item.Item;
import ru.job4j.tictactoe.item.TypeItem;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Field {
    void showFiled();
    boolean setNewItem(int x, int y, TypeItem typeitem);
    void setSize(int size);
    int getSize();
    Item[][] getFieldItems();
}
