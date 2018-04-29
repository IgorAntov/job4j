package ru.job4j.condition;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Point {

    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceToPoint(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

    public static void main(String[] args) {

        Point a = new Point(10, 15);
        Point b = new Point(5, 4);

        double distance = a.distanceToPoint(b);
        System.out.println("x1 = " + a.x);
        System.out.println("y1 = " + a.y);
        System.out.println("x2 = " + b.x);
        System.out.println("y2 = " + b.y);
        System.out.println("Расстояние между точками А и В : " + distance);
    }

}
