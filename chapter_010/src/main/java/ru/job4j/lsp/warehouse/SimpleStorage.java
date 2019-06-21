package ru.job4j.lsp.warehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public abstract class SimpleStorage implements Storage {
    protected List<Food> foods = new ArrayList<>();

    @Override
    public abstract boolean checkFood(Food food);

    @Override
    public List<Food> getFoodList() {
        return this.foods;
    }

    /**
     * Method evaluates date difference.
     * @param date1 first date.
     * @param date2 second date.
     * @return date difference.
     */
    protected static long getDateDiff(Date date1, Date date2) {
        long diffInMilliSec = date2.getTime() - date1.getTime();
        return TimeUnit.DAYS.convert(diffInMilliSec, TimeUnit.MILLISECONDS);
    }

    /**
     * Method evaluates date difference from now.
     * @param date date.
     * @return date difference.
     */
    protected long getDataDiffFromNow(Date date) {
        return getDateDiff(new Date(), date);
    }
}
