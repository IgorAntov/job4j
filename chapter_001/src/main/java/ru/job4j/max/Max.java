package ru.job4j.max;


public class Max {

    /**
     * Отвечает на вопросы.
     * @param first- первое сравниваемое значение.
     * @param second - второе сравниваемое значение.
     * @return максимальное значение из двух переменных.
     */
    public int max(int first, int second) {
        return (first > second) ? first : second;
    }

    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), third);
    }
}
