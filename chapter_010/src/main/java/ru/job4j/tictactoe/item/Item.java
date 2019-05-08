package ru.job4j.tictactoe.item;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Item {
    private TypeItem typeItem;
    private String sign;
    private int rate = 0;

    public Item(TypeItem typeItem) {
        setTypeItem(typeItem);
    }

    public void setTypeItem(TypeItem typeItem) {
        this.typeItem = typeItem;
        switch (typeItem) {
            case CROSS: sign = "X"; break;
            case NOUGHT: sign = "O"; break;
            case EMPTY: sign = ""; break;
            default: sign = "";
        }
    }

    public TypeItem getTypeItem() {
        return typeItem;
    }

    public String getSign() {
        return sign;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int cena) {
        this.rate = cena;
    }
}
