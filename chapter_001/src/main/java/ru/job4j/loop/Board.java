package ru.job4j.loop;

/**
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class Board {

    /**
     * Метод вычисляет факториал числа.
     * @return факториал
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i % 2 == 0) == (j % 2 == 0) || (i % 2 != 0) == (j % 2 != 0)) {
                    screen.append("X");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(ln);
        }
        return screen.toString();
    }
}
