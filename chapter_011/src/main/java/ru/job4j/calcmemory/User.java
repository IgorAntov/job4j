package ru.job4j.calcmemory;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class User {
    private String name;
    private byte[] size;

    User(String name, int sizeArray) {
        if (sizeArray != 0) {
            size = new byte[sizeArray];
        }
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize object.");
    }
}
