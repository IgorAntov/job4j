package ru.job4j.streamapi.student;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Student {
    private int score;
    private String name;
    private String surname;

    public Student(int score, String name, String surname) {
        this.score = score;
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Student(int score) {
        this.score = score;

    }

    public int getScore() {
        return score;
    }
}
