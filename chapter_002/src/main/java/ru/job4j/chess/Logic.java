package ru.job4j.chess;

import ru.job4j.chess.figures.Cell;
import ru.job4j.chess.figures.Figure;

import java.util.Optional;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class Logic {
    private final Figure[] figures = new Figure[32];
    private int index = 0;

    public void add(Figure figure) {
        this.figures[this.index++] = figure;
    }

    public boolean move(Cell source, Cell dest) {
        boolean rst = false;
        try {

            int index = this.findBy(source);
            if (index != -1) {
                Cell[] steps = this.figures[index].way(source, dest);
                if (steps.length > 0 && steps[steps.length - 1].equals(dest)) {
                    boolean cellsFree = true;
                    for (int i = 0; i < steps.length; i++) {
                        if (findBy(steps[i]) != -1) {
                            cellsFree = false;
                            break;
                        }
                    }
                    if (!cellsFree) {
                        throw new OccupiedWayException();
                    }
                    rst = true;
                    this.figures[index] = this.figures[index].copy(dest);

                }
            }
        } catch (OccupiedWayException owe) {
            System.out.println("Путь занят.");
        } catch (ImpossibleMoveException ime) {
            System.out.println("Так ходить нельзя.");
        }
        return rst;
    }

    public void clean() {
        for (int position = 0; position != this.figures.length; position++) {
            this.figures[position] = null;
        }
        this.index = 0;
    }

    private int findBy(Cell cell) {
        int rst = -1;
        for (int index = 0; index != this.figures.length; index++) {
            if (this.figures[index] != null && this.figures[index].position().equals(cell)) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}