package ru.job4j.tictactoe.logic;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Logic {

    void checkStatusGame();
    boolean isSetWiner();
    boolean isWinner();
    void newTurn();
    void setScoreToWin(int scoreToWin);
    int getCrossWinCount();
    int getNoughtWinCount();
    String getWiner();
}
