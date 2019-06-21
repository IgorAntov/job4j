package ru.job4j.lsp.warehouseext;

import ru.job4j.lsp.warehouse.Food;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class FoodExt extends Food {

    private boolean canReproduct;
    private boolean vegetable;

    public FoodExt(String name, String expiredDate, Double price, boolean canReproduct, boolean vegetables) {
        super(name, expiredDate, price);
        this.canReproduct = canReproduct;
        this.vegetable = vegetables;
    }

    /**
     * Returns flag - Can Reproduct option.
     * @return
     */
    public boolean isCanReproduct() {
        return canReproduct;
    }

    /**
     * Returns flag that food is vegetable.
     * @return
     */
    public boolean isVegetable() {
        return vegetable;
    }

}
