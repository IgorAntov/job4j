package ru.job4j.streamapi.student;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.streamapi.student.School;
import ru.job4j.streamapi.student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SchoolTest {

    @Test
    public void collect() {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(20), new Student(60), new Student(80)));
        School school = new School();
        Assert.assertThat(school.collect(students, student -> student.getScore() <= 50).get(0), is(students.get(0)));
        Assert.assertThat(school.collect(students, student -> student.getScore() > 50 && student.getScore() <= 70).get(0), is(students.get(1)));
        Assert.assertThat(school.collect(students, student -> student.getScore() > 70 && student.getScore() <= 100).get(0), is(students.get(2)));
    }
}