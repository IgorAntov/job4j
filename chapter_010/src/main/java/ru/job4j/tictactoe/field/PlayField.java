package ru.job4j.tictactoe.field;

import ru.job4j.tictactoe.field.Field;
import ru.job4j.tictactoe.item.Item;
import ru.job4j.tictactoe.item.TypeItem;

import static ru.job4j.tictactoe.item.TypeItem.CROSS;
import static ru.job4j.tictactoe.item.TypeItem.EMPTY;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class PlayField implements Field {
    private int size;
    private Item[][] fieldItems;

    public PlayField() {
        this.size = 3;
        this.fieldItems = new Item[size][size];
        init();
    }

    /**
     * Field Items status initialization.
     */
    private void init() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.fieldItems[i][j] = new Item(EMPTY);
            }
        }
    }

    /**
     * Setting field size.
     * @param size field size.
     */
    public void setSize(int size) {
        this.size = size;
        this.fieldItems = new Item[size][size];
        init();
    }

    /**
     * Field Output.
     */
    @Override
    public void showFiled() {
        for (int j = 0; j < this.size; j++) {
            System.out.print(String.format("    %s", j));
        }
        System.out.print("\n  ");
        for (int j = 0; j < this.size; j++) {
            System.out.print("-----");
        }
        for (int i = 0; i < this.size; i++) {
            System.out.print(String.format("\n%s|", i));
            for (int j = 0; j < this.size; j++) {
                if (fieldItems[i][j].getTypeItem() != EMPTY) {
                    System.out.print(String.format(" %s  ", fieldItems[i][j].getSign()));
                } else {
                    System.out.print("    ");
                }
                System.out.print("|");
            }
            System.out.print("\n  ");
            for (int j = 0; j < this.size; j++) {
                System.out.print("-----");
            }
        }
        System.out.println("\n");
    }

    /**
     * Setting new Item into the FieldPlay.
     * @param x coordinate
     * @param y coordinate.
     * @param typeitem type of item (CROSS or NOUGHT)
     * @return
     */
    @Override
    public boolean setNewItem(int x, int y, TypeItem typeitem) {
        boolean result = false;
        if (fieldItems[x][y].getTypeItem() == EMPTY) {
            fieldItems[x][y] = new Item(CROSS);
            result = true;
        }
        return result;
    }

    /**
     * Field size getting.
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * Field Items Array getting.
     * @return
     */
    public Item[][] getFieldItems() {
        return fieldItems;
    }
}