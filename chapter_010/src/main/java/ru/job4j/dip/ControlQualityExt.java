package ru.job4j.dip;

import ru.job4j.lsp.warehouse.*;
import ru.job4j.lsp.warehouseext.FoodExt;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ControlQualityExt extends ControlQuality {

    public boolean resort() {
        boolean result = true;
        List<Storage> storageSet = getStorages();
        for (Storage storage : storageSet) {
            List<FoodExt> tempList = ((FoodResort) storage).getFoodForResort();
            for (FoodExt foodExt: tempList) {
                addToStorage(foodExt);
            }
            if (!tempList.isEmpty()) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Method provides dynamic redistribution of products.
     * @return true is all foods were resorted.
     */
    public boolean checkAllFood() {
        boolean result = true;
        List<Storage> storageSet = getStorages();
        for (Storage storage: storageSet) {
            List<FoodExt> tempList = ((FoodResort) storage).checkFood();
            if (!addToStorageFromList(tempList)) {
                result = false;
            }
        }
        return result;
    }

    /**
     * Method sorts products from the FoodList.
     * @param foods list of foods.
     * @return true is that all foods sorted successfully.
     */
    private boolean addToStorageFromList(List<FoodExt> foods) {
        boolean result = true;
        if (!foods.isEmpty()) {
            for (Food food: foods) {
                addToStorage(food);
            }
        }
        if (!foods.isEmpty()) {
            result = false;
        }
        return result;
    }
}
