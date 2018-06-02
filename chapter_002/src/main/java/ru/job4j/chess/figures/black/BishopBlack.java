package ru.job4j.chess.figures.black;

import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        int step = 0;
        int sign1 = 1, sign2 = -1;

        if (abs(dest.x - source.x) != abs(dest.y - source.y)) {
            throw new ImpossibleMoveException();
        }

        steps = new Cell[abs(dest.x - source.x)];

        if (dest.x > source.x && dest.y < source.y) {
            getStep(source, dest, steps, step, sign1, sign2);
        }
        if (dest.x < source.x && dest.y < source.y) {
            getStep(source, dest, steps, step, sign2, sign2);
        }
        if (dest.x > source.x && dest.y > source.y) {
            getStep(source, dest, steps, step, sign1, sign1);
        }
        if (dest.x < source.x && dest.y > source.y) {
            getStep(source, dest, steps, step, sign2, sign1);
        }
        return steps;
    }

    private void getStep(Cell source, Cell dest, Cell[] steps, int step, int dirX, int dirY) {
        for (int i = 1; i <= abs(dest.x - source.x); i++) {
            for (Cell cell : Cell.values()) {
                if (cell.x == (source.x + i * dirX) && cell.y == (source.y + i * dirY)) {
                    steps[step++] = cell;
                }
            }
        }
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
