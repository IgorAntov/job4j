package ru.job4j.lsp.warehouse;

import org.junit.Test;
import ru.job4j.lsp.warehouseext.FoodExt;
import ru.job4j.lsp.warehouseext.TrashExt;
import ru.job4j.lsp.warehouseext.WarehouseExt;

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

    @Test
    public void checkAllFoodExtTest() {
        Food food1 = new FoodExt("food1", getDateSetOff(76), 100.0, false, false);
        Food food2 = new FoodExt("food2", getDateSetOff(76), 100.0, false, false);
        Food food3 = new FoodExt("food3", getDateSetOff(76), 100.0, false, false);
        Food food4 = new FoodExt("food4", getDateSetOff(76), 100.0, false, false);
        Food food5 = new FoodExt("food5", getDateSetOff(76), 100.0, false, false);
        Food food6 = new FoodExt("food6", getDateSetOff(76), 100.0, false, false);
        Food food7 = new FoodExt("food7", getDateSetOff(76), 100.0, false, true);
        Food food8 = new FoodExt("food8", getDateSetOff(76), 100.0, false, false);
        Food food9 = new FoodExt("food9", getDateSetOff(76), 100.0, false, true);
        Food food10 = new FoodExt("food10", getDateSetOff(-1), 100.0, false, true);
        Food food11 = new FoodExt("food11", getDateSetOff(-1), 100.0, true, true);
        Food food12 = new FoodExt("food12", getDateSetOff(50), 100.0, true, true);
        food1.setCreateDate(getDateSetOff(-24));
        food2.setCreateDate(getDateSetOff(-24));
        food3.setCreateDate(getDateSetOff(-24));
        food4.setCreateDate(getDateSetOff(-24));
        food5.setCreateDate(getDateSetOff(-24));
        food6.setCreateDate(getDateSetOff(-24));
        food7.setCreateDate(getDateSetOff(-24));
        food8.setCreateDate(getDateSetOff(-24));
        food9.setCreateDate(getDateSetOff(-24));
        food10.setCreateDate(getDateSetOff(-101));
        food11.setCreateDate(getDateSetOff(-101));
        food12.setCreateDate(getDateSetOff(-50));
        Shop shop = new Shop();
        WarehouseExt warehouse = new WarehouseExt(new Warehouse());
        WarehouseExt warehouse2 = new WarehouseExt(new Warehouse());
        TrashExt trash = new TrashExt(new Trash());
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.addStorage(shop);
        controlQuality.addStorage(warehouse);
        controlQuality.addStorage(warehouse2);
        controlQuality.addStorage(trash);
        controlQuality.addToStorage(food1);
        controlQuality.addToStorage(food2);
        controlQuality.addToStorage(food3);
        controlQuality.addToStorage(food4);
        controlQuality.addToStorage(food5);
        controlQuality.addToStorage(food6);
        controlQuality.addToStorage(food7);
        controlQuality.addToStorage(food8);
        controlQuality.addToStorage(food9);
        controlQuality.addToStorage(food10);
        controlQuality.addToStorage(food11);
        controlQuality.addToStorage(food12);
        assertThat(warehouse.getFoodList().size(), is(5));
        assertThat(warehouse2.getFoodList().size(), is(2));
        assertThat(warehouse2.getFoodListLowTempZone().size(), is(2));
        assertThat(trash.getFoodList().contains(food10), is(true));
        assertThat(trash.getReproductList().contains(food11), is(true));
        assertThat(shop.getFoodList().contains(food12), is(true));
    }
}