package ru.job4j;

/**
 * Корвертор валюты.
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class Converter {

    /**
     * Конвертируем рубли в евро.
     * @param value рубли.
     * @return Евро.
     */
    public int rubleToEuro(int value) {
        int result = value / 70;
        return result;
    }

    /**
     * Конвертируем рубли в доллары.
     * @param value рубли.
     * @return Доллоры.
     */
    public int rubleToDollar(int value) {
        int result = value / 60;
        return result;
    }

    /**
     * Конвертируем евро в рубли.
     * @param value евро.
     * @return Доллоры.
     */
    public int euroToRuble(int value) {
        int result = value * 70;
        return result;
    }

    /**
     * Конвертируем доллары в рубли.
     * @param value доллары.
     * @return Доллоры.
     */
    public int dollarToRuble(int value) {
        int result = value * 60;
        return result;
    }


}

