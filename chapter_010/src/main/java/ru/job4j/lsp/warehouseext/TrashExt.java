package ru.job4j.lsp.warehouseext;

import ru.job4j.lsp.warehouse.Food;
import ru.job4j.lsp.warehouse.Storage;
import ru.job4j.lsp.warehouse.Trash;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class TrashExt implements Storage {

    private Trash trash;
    protected List<Food> reproduct = new ArrayList<>();

    public TrashExt(Trash trash) {
        this.trash = trash;
    }

    @Override
    public boolean checkFood(Food food) {
        FoodExt foodExt = (FoodExt) food;
        boolean result = trash.checkFood(foodExt);
        if (foodExt.isCanReproduct() && result) {
            trash.getFoodList().remove(foodExt);
            reproduct.add(foodExt);
        }
        return result;
    }

    @Override
    public List<Food> getFoodList() {
        return trash.getFoodList();
    }

    /**
     * Returns List of Food that can be reproducted
     * @return
     */
    public List<Food> getReproductList() {
        return reproduct;
    }
}
