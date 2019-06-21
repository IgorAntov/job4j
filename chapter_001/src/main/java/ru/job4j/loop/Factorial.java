package ru.job4j.loop;

/**
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class Factorial {

    /**
     * Метод вычисляет факториал числа.
     * @return факториал
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
