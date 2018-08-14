package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }
}
