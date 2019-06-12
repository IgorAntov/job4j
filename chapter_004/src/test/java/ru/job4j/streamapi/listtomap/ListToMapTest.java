package ru.job4j.streamapi.listtomap;

import org.junit.Assert;
import org.junit.Test;
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
public class ListToMapTest {

    @Test
    public void listToMapTest() {
        List<Student> students = new ArrayList<>(Arrays.asList(new Student(20, "Ivan", "Ivanov")));
        Assert.assertThat(new ListToMap().studentList(students).get("Ivanov").getSurname(), is("Ivanov"));
    }
}