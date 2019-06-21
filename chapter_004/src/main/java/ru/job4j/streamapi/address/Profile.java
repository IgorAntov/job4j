package ru.job4j.streamapi.address;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Profile {
    private Address address;

    public Profile(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }
}
