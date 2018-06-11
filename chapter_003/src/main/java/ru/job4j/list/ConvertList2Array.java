package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConvertList2Array {

    /**
     * метод равномерно разбивает лист на количество строк двумерного массива
     * @param list - лист со значениями.
     * @param rows - количесвто строк
     * @return - двумерный массив значний
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = list.size() % rows != 0 ? list.size() / rows + 1 : list.size() / rows;
        int[][] array = new int[rows][cells];
        int row = 0, cell = 0;

        for (Integer value : list) {
            if (cell == cells - 1) {
                array[row][cell] = value;
                cell = 0;
                row++;
            } else {
                array[row][cell] = value;
                cell++;
            }
        }
        if (list.size() / rows != cells) {
            for (int i = list.size(); i < cells; i++) {
                array [row][i] = 0;
            }
        }
        return array;
    }

    /**
     * метод равномерно разбивает лист на количество строк двумерного массива
     * @param - лист массивов со значениями.
     * @return - лист значенинй
     * */
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] arr : list) {
            for (int value : arr) {
                result.add(value);
            }
        }
        return result;
    }
}