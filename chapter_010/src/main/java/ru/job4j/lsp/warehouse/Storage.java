package ru.job4j.lsp.warehouse;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Storage {
    /**
     * Method determines the condition for entering Storage.
     * @param food food.
     * @return result.
     */
    boolean checkFood(Food food);

    /**
     * Method return Storage food list.
     * @return food list.
     */
    List<Food> getFoodList();
}
