package ru.job4j.lsp.warehouse;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Shop extends SimpleStorage {

    @Override
    public boolean checkFood(Food food) {
        boolean result = false;
        if (getDataDiffFromNow(food.getExpiredDate()) < (0.75 * getDateDiff(food.getCreateDate(), food.getExpiredDate()))
                && getDataDiffFromNow(food.getExpiredDate()) > (0.25 * getDateDiff(food.getCreateDate(), food.getExpiredDate()))) {
            super.foods.add(food);
            result = true;
        }
        if (getDataDiffFromNow(food.getExpiredDate()) <= 0.25 * getDateDiff(food.getCreateDate(), food.getExpiredDate())
                && getDataDiffFromNow(food.getExpiredDate()) > 0) {
            food.setDisscount(15);
            super.foods.add(food);
            result = true;
        }
        return result;
    }
}
