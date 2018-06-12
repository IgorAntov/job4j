package ru.job4j.sort;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class User implements  Comparable<User> {

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(User o) {
        return Integer.valueOf(this.age).compareTo(o.age);
    }
}
