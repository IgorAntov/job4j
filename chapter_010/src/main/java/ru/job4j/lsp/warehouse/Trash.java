package ru.job4j.lsp.warehouse;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */

public class Trash extends SimpleStorage {

    @Override
    public boolean checkFood(Food food) {
        boolean result = false;
        if (getDataDiffFromNow(food.getExpiredDate()) <= 0) {
            super.foods.add(food);
            result = true;
        }
        return result;
    }
}
