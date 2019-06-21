package ru.job4j.pseudo;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Paint {

    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }

    public static void main(String[] args) {
        Paint paint = new Paint();
        paint.draw(new Triangle());
        paint.draw(new Square());
    }
}
