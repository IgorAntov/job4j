package ru.job4j.jdk19new;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SortStudents {
    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream().flatMap(Stream::ofNullable).sorted(new Student()).takeWhile(student -> student.getScore() > bound).collect(Collectors.toList());
    }
}
