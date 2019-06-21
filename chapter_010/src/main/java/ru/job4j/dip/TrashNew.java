package ru.job4j.dip;

import ru.job4j.lsp.warehouse.Food;
import ru.job4j.lsp.warehouseext.FoodExt;
import ru.job4j.lsp.warehouseext.TrashExt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class TrashNew  implements FoodResort {
    private TrashExt trashExt;

    public TrashNew(TrashExt trashExt) {
        this.trashExt = trashExt;
    }

    @Override
    public List<FoodExt> getFoodForResort() {
        List<FoodExt> allFoods = new ArrayList<>();
        List<Food> tempFood = getFoodList();
        for (Food food: tempFood) {
            FoodExt fe = ((FoodExt) food);
            allFoods.add(fe);
        }
        tempFood.clear();
        tempFood = trashExt.getReproductList();
        for (Food food : tempFood) {
            allFoods.add((FoodExt) food);
        }
        tempFood.clear();
        return allFoods;
    }

    @Override
    public List<FoodExt> checkFood() {
        List<FoodExt> forResortFood = new ArrayList<>();
        List<Food> tempFood = new ArrayList<>(getFoodList());
        getFoodList().clear();
        for (Food f: tempFood) {
            if (!checkFood(f)) {
                FoodExt fe = ((FoodExt) f);
                forResortFood.add(fe);
            }
        }
        return forResortFood;
    }

    @Override
    public boolean checkFood(Food food) {
        return trashExt.checkFood(food);
    }

    @Override
    public List<Food> getFoodList() {
        return trashExt.getFoodList();
    }

    public List<Food> getReproductList() {
        return trashExt.getReproductList();
    }
}
