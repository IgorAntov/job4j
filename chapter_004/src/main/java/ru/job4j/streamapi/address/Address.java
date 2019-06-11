package ru.job4j.streamapi.address;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Address {
    private String city;
    private String street;
    private int home;
    private int apartment;

    public Address(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}
