package ru.job4j.bomberman;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Cell {
    private int x;
    private int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
