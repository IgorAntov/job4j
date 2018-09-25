package ru.job4j.nba;

import java.util.Objects;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Base {
    private int version;
    private String name;

    public Base(String name) {
        this.version = 0;
        this.name = name;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.version++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Base base = (Base) o;
        return Objects.equals(name, base.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
