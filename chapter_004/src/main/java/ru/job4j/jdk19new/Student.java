package ru.job4j.jdk19new;

import java.util.Comparator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Student implements Comparator<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Student() {
        this("", 0);
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return o2.getScore() - o1.getScore();
    }
}