package ru.job4j.tictactoe;

import ru.job4j.tictactoe.field.Field;
import ru.job4j.tictactoe.field.PlayField;
import ru.job4j.tictactoe.input.ConsolInput;
import ru.job4j.tictactoe.input.Input;
import ru.job4j.tictactoe.logic.Logic;
import ru.job4j.tictactoe.logic.LogicGame;

import static ru.job4j.tictactoe.item.TypeItem.NOUGHT;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class TicTacToeGame {
    private Input input;
    private Field field;
    private Logic logicGame;

    public TicTacToeGame(Input input, Field field) {
        this.input = input;
        this.field = field;
        init();
    }

    /**
     * Initialization of game parameters.
     */
    public void init() {
        System.out.println("TicTacToe Game:\nСomputer plays with NOUGHT.");
        field.setSize(input.ask("Input Field Size:"));
        this.logicGame = new LogicGame(field.getSize(), field.getFieldItems());
        logicGame.setScoreToWin(input.ask("Set Score To Win:"));
    }

    /**
     * Start User Interface.
     */
    public void start() {
        do {
            field.showFiled();
            while (!field.setNewItem(input.ask("input x:"), input.ask("input y:"), NOUGHT)) {
                System.out.println("Error. This cell is busy.");
            }
            logicGame.checkStatusGame();
            if (!logicGame.isWinner()) {
                logicGame.newTurn();
                field.showFiled();
                logicGame.checkStatusGame();
            }
            if (logicGame.isWinner()) {
                field.showFiled();
                System.out.println(String.format("Score: %s:%s", logicGame.getCrossWinCount(), logicGame.getNoughtWinCount()));
            }
        } while (!logicGame.isSetWiner());
        System.out.println(String.format("Winner is: %s", logicGame.getWiner()));
    }

    public static void main(String[] args) {
        Input input = new ConsolInput();
        TicTacToeGame ticTacToeGame = new TicTacToeGame(input, new PlayField());
        ticTacToeGame.start();
    }
}
