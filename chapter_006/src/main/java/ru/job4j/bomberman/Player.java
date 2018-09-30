package ru.job4j.bomberman;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Player {
    private Board board;
    private Cell cell;

    public Player(Board board, Cell cell) {
        this.board = board;
        this.cell = cell;
        System.out.println(Thread.currentThread().getName() + " Initial cell ["+ cell.getX() +"]["+ cell.getY() +"]");
    }

    /**
     * Method moves player to other cell
     */
    public void go() {
        int steps = board.getStepsForPlayers();
        for (int i = 0; i < steps; i++) {
            Cell nextCell = board.nextStep(this.cell);
            if (board.move(this.cell, nextCell)) {
                this.cell = nextCell;
            }
        }
    }
}
