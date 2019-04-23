package ru.job4j.dip;

import ru.job4j.lsp.warehouse.Food;
import ru.job4j.lsp.warehouse.Shop;
import ru.job4j.lsp.warehouseext.FoodExt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ShopNew extends Shop implements FoodResort {

    @Override
    public List<FoodExt> getFoodForResort() {
        List<FoodExt> allFoods = new ArrayList<>();
        List<Food> tempFood = getFoodList();
        for (Food food: tempFood) {
            FoodExt fe = ((FoodExt) food);
            allFoods.add(fe);
        }
        tempFood.clear();
        return allFoods;
    }

    @Override
    public List<FoodExt> checkFood()  {
        List<FoodExt> tempFood = new ArrayList<>();
        List<Food> food = new ArrayList<>(foods);
        foods.clear();
        for (Food f: food) {
            if (!this.checkFood(f)) {
                FoodExt fe = ((FoodExt) f);
                tempFood.add(fe);
            }
        }
        return tempFood;
    }
}
