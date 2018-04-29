package ru.job4j;
/**
 * Calculate.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */


public class Calculator {
    private double result;

    /**
     * Method add.
     * method performs addition operation on variables first and second
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method subtract.
     * method performs subtracting operation on variables first.
     * and second.
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }
    /**
     * Method div.
     * method performs dividing operation on variables first and second
     */
    public void div(double first, double second) {
        this.result = first / second;
    }
    /**
     * Method multiple.
     * method performs multiplies operation on variables first and second
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
    /**
     * method returns the result of arithmetic operation
     */

    public double getResult() {
        return this.result;
    }
}

