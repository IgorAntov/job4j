package ru.job4j.tictactoe;

public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        String str  = "";
        int tmp = 0;
        boolean result;
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].hasMarkX()) {
                    str = str + "1";
                } else {
                    str = str + "0";
                }
                count++;
            }
        }
        tmp = Integer.parseInt(str, 2);
        if (((tmp & 448) == 448) || ((tmp & 56) == 56) || ((tmp & 7) == 7)
                || ((tmp & 292) == 292) || ((tmp & 146) == 146) || ((tmp & 73) == 73)
                || ((tmp & 273) == 273) || ((tmp & 84) == 84)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean isWinnerO() {
        String str = "";
        int tmp = 0;
        boolean result;
        int count = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if (table[i][j].hasMarkO()) {
                    str = str + "1";
                } else {
                    str = str + "0";
                }
                count++;
            }
        }
        tmp = Integer.parseInt(str, 2);
        if (((tmp & 448) == 448) || ((tmp & 56) == 56) || ((tmp & 7) == 7)
                || ((tmp & 292) == 292) || ((tmp & 146) == 146) || ((tmp & 73) == 73)
                || ((tmp & 273) == 273) || ((tmp & 84) == 84)) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public boolean hasGap() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                if ((!table[i][j].hasMarkO()) & (!table[i][j].hasMarkX())) {
                    result = true; break;
                }
            }
        }
        return result;
    }
}