package ru.job4j.dip;

import ru.job4j.lsp.warehouse.Food;
import ru.job4j.lsp.warehouseext.FoodExt;
import ru.job4j.lsp.warehouseext.WarehouseExt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class WarehouseNew implements FoodResort {
    private WarehouseExt warehouseExt;

    public WarehouseNew(WarehouseExt warehouseExt) {
        this.warehouseExt = warehouseExt;
    }

    @Override
    public List<FoodExt> getFoodForResort() {
        List<FoodExt> allFoods = new ArrayList<>();
        List<Food> tempFood = getFoodList();
        for (Food food : tempFood) {
            FoodExt fe = ((FoodExt) food);
            allFoods.add(fe);
        }
        tempFood.clear();
        tempFood = warehouseExt.getFoodListLowTempZone();
        for (Food food : tempFood) {
            FoodExt fe = ((FoodExt) food);
            allFoods.add(fe);
        }
        tempFood.clear();
        return allFoods;
    }

    @Override
    public List<FoodExt>  checkFood() {
        List<FoodExt> forResortFood = new ArrayList<>();
        List<FoodExt> tempForResortFood = new ArrayList<>();
        List<Food> tempFood = new ArrayList<>(getFoodList());
        getFoodList().clear();
        for (Food f: tempFood) {
            if (!this.checkFood(f)) {
                FoodExt fe = ((FoodExt) f);
                tempForResortFood.add(fe);
            }
        }
        forResortFood.addAll(tempForResortFood);
        tempForResortFood.clear();
        tempFood = new ArrayList<>(getFoodListLowTempZone());
        getFoodListLowTempZone().clear();
        for (Food f: tempFood) {
            if (!checkFood(f)) {
                FoodExt fe = ((FoodExt) f);
                tempForResortFood.add(fe);
            }
        }
        forResortFood.addAll(tempForResortFood);
        return forResortFood;
    }

    @Override
    public boolean checkFood(Food food) {
        return this.warehouseExt.checkFood(food);
    }

    @Override
    public List<Food> getFoodList() {
        return warehouseExt.getFoodList();
    }

    public List<Food> getFoodListLowTempZone() {
        return warehouseExt.getFoodListLowTempZone();
    }
}

