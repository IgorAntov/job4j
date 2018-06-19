package ru.job4j.bank;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class User implements  Comparable<User> {
    private String name;
    private String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.getName().equals(((User) obj).getName()) && this.getPassport().equals(((User) obj).getPassport());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public int compareTo(User o) {
        return this.equals(o) ? 0 : 1;
    }
}
