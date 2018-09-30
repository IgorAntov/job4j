package ru.job4j.bomberman;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Player {
    private Board board;
    private Cell cell;
    private String typePlayer;

    public Player(Board board, Cell cell, String typePlayer) {
        this.board = board;
        this.cell = cell;
        this.typePlayer = typePlayer;
        System.out.println(Thread.currentThread().getName() + "(" + typePlayer + ")" + " Initial cell [" + cell.getX() + "][" + cell.getY() + "]");
    }

    /**
     * Method moves player to other cell
     */
    public void go() {
        int steps = board.getStepsForPlayers();
        for (int i = 0; i < steps; i++) {
            Cell nextCell = board.nextStep(this.cell);
            if (typePlayer.equals("MONSTER")) {
                if (board.move(this.cell, nextCell, 5000)) {
                    this.cell = nextCell;
                }
            } else {
                if (board.move(this.cell, nextCell, 500)) {
                    this.cell = nextCell;
                }
            }
        }
    }
}
