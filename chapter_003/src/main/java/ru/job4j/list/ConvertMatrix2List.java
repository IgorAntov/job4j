package ru.job4j.list;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConvertMatrix2List {

    /**
     * Метод преобразует двухмерный массив в List
     * @param - исходный array.
     * @return - List элементов двухмерного массива
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arr: array) {
            for (int value: arr) {
                list.add(value);
            }
        }
        return list;
    }
}