package ru.job4j.jdk19new;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StudentTest {

    @Test
    public void levelOfTest() {
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("ivan", 25),
                null,
                new Student("egor", 75))
        );
        SortStudents sortStudents = new SortStudents();
        Assert.assertThat(sortStudents.levelOf(students, 50).size(), is(1));
        Assert.assertThat(sortStudents.levelOf(students, 50).get(0), is(students.get(2)));
    }
}