package ru.job4j.array;

import java.util.Arrays;

/**
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class ArrayDuplicate {
    /**
     * Удаление дубликатов в массиве.
     * @param array исходный массив
     * @return массив без дубликатов
     */
    public String[] remove(String[] array) {
        String temp;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length - count - 1; j++) {
                if (array[i].equals(array[j + 1])) {
                    temp = array[j + 1];
                    for (int k = j + 1; k < array.length - 1; k++) {
                        array[k] = array[k + 1];
                        j = i;
                    }
                    array[array.length - 1] = temp;
                    count++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - count);
    }
}
