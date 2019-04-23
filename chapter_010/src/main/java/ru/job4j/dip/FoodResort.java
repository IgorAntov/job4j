package ru.job4j.dip;

import ru.job4j.lsp.warehouse.Food;
import ru.job4j.lsp.warehouse.Storage;
import ru.job4j.lsp.warehouseext.FoodExt;

import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface FoodResort extends Storage {

    List<FoodExt> getFoodForResort();

    List<FoodExt> checkFood();
}
