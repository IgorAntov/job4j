package ru.job4j.nba;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class OptimisticException extends RuntimeException {

    public OptimisticException(String msg) {
        super(msg);
    }
}
