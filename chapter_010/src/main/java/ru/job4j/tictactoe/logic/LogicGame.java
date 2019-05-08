package ru.job4j.tictactoe.logic;

import ru.job4j.tictactoe.item.Item;

import static ru.job4j.tictactoe.item.TypeItem.CROSS;
import static ru.job4j.tictactoe.item.TypeItem.EMPTY;
import static ru.job4j.tictactoe.item.TypeItem.NOUGHT;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class LogicGame implements Logic {
    private int size;
    private Item[][] fieldItems;
    private int[][] xLine;
    private int[][] yLine;
    private int[][] upLine;
    private int[][] downLine;
    private int scoreToWin = 1;
    private int crossWinCount;
    private int noughtWinCount;
    private String winner;
    boolean isWinner = false;

    public LogicGame(int size, Item[][] fieldItems) {
        this.size = size;
        this.fieldItems = fieldItems;
        xLine = new int[2][size];
        yLine = new int[2][size];
        upLine = new int[2][1];
        downLine = new int[2][1];
    }

    /**
     * Сhecks if there is a winner in the game.
     * @return
     */
    public boolean isSetWiner() {
        boolean result = false;
        if (isWinner) {
            if (crossWinCount >= scoreToWin) {
                result = true;
                winner = "CROSS";
            }
            if (noughtWinCount >= scoreToWin) {
                result = true;
                winner = "NOUGHT";
            }
            resetField();
            isWinner = false;
        }
        return result;
    }

    /**
     * Fills the field with items with an empty status.
     */
    private void resetField() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.fieldItems[i][j] = new Item(EMPTY);
            }
        }
    }

    @Override
    public String getWiner() {
        return winner;
    }

    @Override
    public int getCrossWinCount() {
        return crossWinCount;
    }

    @Override
    public int getNoughtWinCount() {
        return noughtWinCount;
    }

    @Override
    public boolean isWinner() {
        return this.isWinner;
    }

    public void setScoreToWin(int scoreToWin) {
        this.scoreToWin = scoreToWin;
    }

    @Override
    public void newTurn() {
        if (!isWinner) {
            calcRate();
            maxRate(fieldItems).setTypeItem(NOUGHT);
            resetRate();
        }
    }

    /**
     * Resets Item Rate for new turn.
     */
    private void resetRate() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < this.size; j++) {
                fieldItems[i][j].setRate(0);
            }
        }
    }

    /**
     * Searches max rate of Items
     * @param fieldItems
     * @return
     */
    private Item maxRate(Item[][] fieldItems) {
        int maxRate = 0;
        Item itemMaxRate = null;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (maxRate <= fieldItems[i][j].getRate() && fieldItems[i][j].getTypeItem() == EMPTY) {
                    maxRate = fieldItems[i][j].getRate();
                    itemMaxRate = fieldItems[i][j];
                }
            }
        }
        return itemMaxRate;
    }

    /**
     * Checks status game if there is the winner.
     */
    @Override
    public void checkStatusGame() {
        xLine = new int[2][size];
        yLine = new int[2][size];
        upLine = new int[2][1];
        downLine = new int[2][1];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (fieldItems[i][j].getTypeItem() != EMPTY) {
                    if (fieldItems[i][j].getTypeItem() == CROSS) {
                        xLine[0][i]++;
                    } else {
                        xLine[1][i]++;
                    }
                }
                if (fieldItems[j][i].getTypeItem() != EMPTY) {
                    if (fieldItems[j][i].getTypeItem() == CROSS) {
                        yLine[0][i]++;
                    } else {
                        yLine[1][i]++;
                    }
                }
            }
            if (checkCrossIsWinner(xLine[0][i], yLine[0][i])) {
                break;
            }
            if (checkNoughtIsWinner(xLine[1][i], yLine[1][i])) {
                break;
            }
            if (fieldItems[i][i].getTypeItem() != EMPTY) {
                if (fieldItems[i][i].getTypeItem() == CROSS) {
                    downLine[0][0]++;
                } else {
                    downLine[1][0]++;
                }
            }
            if (fieldItems[size - 1 - i][i].getTypeItem() != EMPTY) {
                if (fieldItems[size - 1 - i][i].getTypeItem() == CROSS) {
                    upLine[0][0]++;
                } else {
                    upLine[1][0]++;
                }
            }
        }
        checkCrossIsWinner(downLine[0][0], upLine[0][0]);
        checkNoughtIsWinner(downLine[1][0], upLine[1][0]);
    }

    /**
     * Calculates position item rate.
     */
    private void calcRate() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (xLine[0][i] == 0 && xLine[1][i] != 0) {
                    setRate(fieldItems[i][j], xLine[1][i]);
                } else {
                    setRateZero(fieldItems[i][j], xLine[1][i]);
                }
                if (yLine[0][i] == 0 && yLine[1][i] != 0) {
                    setRate(fieldItems[j][i], yLine[1][i]);
                } else {
                    setRateZero(fieldItems[j][i], yLine[1][i]);
                }
                if (downLine[0][0] == 0 && downLine[1][0] != 0) {
                    setRate(fieldItems[j][j], downLine[1][0]);
                } else {
                    setRateZero(fieldItems[j][j], downLine[1][0]);
                }
                if (upLine[0][0] == 0 && upLine[1][0] != 0) {
                    setRate(fieldItems[size - 1 - j][j], upLine[1][0]);
                } else {
                    setRateZero(fieldItems[size - 1 - j][j], upLine[1][0]);
                }
            }
        }
    }

    /**
     * Set rate into Item RateField
     * @param item
     * @param xNLine
     */
    private void setRate(Item item, int xNLine) {
        if (item.getTypeItem() == EMPTY && item.getRate() < xNLine) {
            item.setRate(xNLine);
        }
    }

    /**
     * Set rate to zero.
     * @param item
     * @param xNLine
     */
    private void setRateZero(Item item, int xNLine) {
        if (item.getTypeItem() == EMPTY && item.getRate() < xNLine) {
            item.setRate(0);
        }
    }

    /**
     * Checks If Nought player is winner.
     */
    private boolean checkNoughtIsWinner(int xNLine, int yNLine) {
        if (xNLine == size || yNLine == size) {
            this.noughtWinCount++;
            isWinner = true;
            return true;
        }
        return false;
    }

    /**
     * Checks If Nought player is winner.
     */
    private boolean checkCrossIsWinner(int xCLine, int yCLine) {
        if (xCLine == size || yCLine == size) {
            this.crossWinCount++;
            isWinner = true;
            return true;
        }
        return false;
    }
}