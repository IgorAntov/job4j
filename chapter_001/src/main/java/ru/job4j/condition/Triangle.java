package ru.job4j.condition;

/**
 *  * @author Igor Antropov.
 *  * @version $Id$.
 *  * @since 0.1.
 */
public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Метод вычисления полупериметра по длинам сторон.
     *
     * Формула.
     *
     * (ab + ac + bc) / 2
     *
     * @param ab расстояние между точками a b.
     * @param ac расстояние между точками a c.
     * @param bc расстояние между точками b c.
     * @return Перимент.
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Метод вычисляет площадь треугольника.
     * @return Вернуть прощадь, если треугольник существует или -1, если треугольника нет.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceToPoint(this.b);
        double ac = this.a.distanceToPoint(this.c);
        double bc = this.b.distanceToPoint(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Метод проверяет можно ли построить треугольник с такими длинами сторон.
     *
     * Подумайте какое надо написать условие, чтобы определить можно ли построить треугольник.
     *
     * @param ab Длина от точки a b.
     * @param ac Длина от точки a c.
     * @param bc Длина от точки b c.
     * @return существование треугольника
     */
    private boolean exist(double ab, double ac, double bc) {
        return ((ab > 0) && (ac > 0) && (bc > 0)) ? true : false;
    }
}

