package ru.job4j.lsp.warehouseext;

import ru.job4j.lsp.warehouse.Food;
import ru.job4j.lsp.warehouse.Storage;
import ru.job4j.lsp.warehouse.Warehouse;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class WarehouseExt implements Storage {
    private Warehouse warehouse;
    private Warehouse warehouseLowTempZone;
    private final int maxSize;

    public WarehouseExt(Warehouse warehouse, int maxSize) {
        this.maxSize = maxSize;
        this.warehouse = warehouse;
        this.warehouseLowTempZone = new Warehouse();
    }

    public WarehouseExt(Warehouse warehouse) {
        this(warehouse, 5);
    }

    public boolean isFull() {
        return (getFoodList().size() + getFoodListLowTempZone().size()) >= maxSize;
    }

    public boolean checkFood(FoodExt food) {
        boolean result = false;
        if (!isFull()) {
            if (!food.isVegetable()) {
                result = warehouse.checkFood(food);
            } else {
                result = warehouseLowTempZone.checkFood(food);
            }
        }
        return result;
    }

    @Override
    public boolean checkFood(Food food) {
        return checkFood((FoodExt) food);
    }

    @Override
    public List<Food> getFoodList() {
        return this.warehouse.getFoodList();
    }

    /**
     * Returns List of Foods that are vegetables and must be kept in Low temp zone.
     * @return
     */
    public List<Food> getFoodListLowTempZone() {
        return this.warehouseLowTempZone.getFoodList();
    }
}
