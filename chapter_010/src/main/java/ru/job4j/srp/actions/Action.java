package ru.job4j.srp.actions;

/**
 * @author Igor Antropov.
 * @version $Id$.
 * @since 0.1.
 *
 * This interface describes methods for arithmetic operations.
 */
public interface Action {
    /**
     *  This method return specific unique number of arithmetic operation.
     *  That is set in class constructor.
     * @return  arithmetic operation number (key).
     */
    int key();

    /**
     * Method returns a brief description of the arithmetic operation.
     * @return task information.
     */
    String taskInfo();

    /**
     * Method performs one specific arithmetic operation.
     * @param x first operand.
     * @param y second operand.
     * @return operation result.
     */
    Double action(double x, double y);

    /**
     * methods to perform an initial data preparation.
     */
    void doAction();
}
