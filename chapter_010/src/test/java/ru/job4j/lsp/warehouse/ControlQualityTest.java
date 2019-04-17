package ru.job4j.lsp.warehouse;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ControlQualityTest {

    private String getDateSetOff(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00";
    }

    @Test
    public void checkAllFoodTest() {
        Food food1 = new Food("food1", getDateSetOff(76), 100.0);
        Food food2 = new Food("food2", getDateSetOff(50), 200.0);
        Food food3 = new Food("food3", getDateSetOff(24), 300.0);
        Food food4 = new Food("food4", getDateSetOff(-1), 400.0);
        food1.setCreateDate(getDateSetOff(-24));
        food2.setCreateDate(getDateSetOff(-50));
        food3.setCreateDate(getDateSetOff(-76));
        food4.setCreateDate(getDateSetOff(-101));
        Shop shop = new Shop();
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStorage(shop);
        controlQuality.addStorage(warehouse);
        controlQuality.addStorage(trash);
        controlQuality.addToStorage(food1);
        controlQuality.addToStorage(food2);
        controlQuality.addToStorage(food3);
        controlQuality.addToStorage(food4);
        assertThat(warehouse.getFoodList().contains(food1), is(true));
        assertThat(shop.getFoodList().contains(food2), is(true));
        assertThat(shop.getFoodList().contains(food3), is(true));
        assertThat(shop.getFoodList().get(1).getDisscount(), is(15));
        assertThat(trash.getFoodList().contains(food4), is(true));
    }
}