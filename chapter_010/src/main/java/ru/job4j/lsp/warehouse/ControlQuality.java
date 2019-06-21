package ru.job4j.lsp.warehouse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ControlQuality {
    private List<Storage> storageSet = new ArrayList<>();

    /**
     * Method returns list of storage.
     * @return storageSet.
     */
    public List<Storage> getStorages() {
        return storageSet;
    }

    /**
     * Method adds Storage to Storage List.
     * @param storage Storage.
     */
    public void addStorage(Storage storage) {
        storageSet.add(storage);
    }

    /**
     * Method adds food to one of the registered storage based on specified condition.
     * @param food food.
     * @return true if food has been gone to Storage.
     */
    public boolean addToStorage(Food food) {
        boolean result = false;
        for (Storage storage: storageSet) {
            if (storage.checkFood(food)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
