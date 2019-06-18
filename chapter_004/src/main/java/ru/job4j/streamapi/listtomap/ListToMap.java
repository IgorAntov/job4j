package ru.job4j.streamapi.listtomap;

import ru.job4j.streamapi.student.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ListToMap {

    Map<String, Student> studentList(List<Student> students) {
        return students.stream().distinct().collect(Collectors.toMap(Student::getSurname, student -> student));
    }
}
