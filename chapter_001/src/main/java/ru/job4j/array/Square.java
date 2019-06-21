package ru.job4j.array;

/**
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class Square {

    /**
     * Метод заполняет массив числами.
     * @return заполненный массив.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];

        for (int i = 0; i < rst.length; i++) {
            rst[i] = ((int) Math.pow((i + 1), 2));
        }
        return rst;
    }
}

