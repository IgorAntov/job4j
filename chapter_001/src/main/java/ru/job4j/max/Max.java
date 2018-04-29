package ru.job4j.max;

/**
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class Max {

    /**
     * Отвечает на вопросы.
     * @param first- первое сравниваемое значение.
     * @param second - второе сравниваемое значение.
     * @return максимальное значение из двух переменных.
     */
    public int max(int first, int second){
        return (first > second) ? first: second;
    }
}
