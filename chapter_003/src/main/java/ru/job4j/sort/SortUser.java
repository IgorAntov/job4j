package ru.job4j.sort;

import java.util.*;

public class SortUser {

    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания
     */
    public Set<User> sort(List<User> users) {
        //      Set<User> result = new TreeSet<User>(users);
        return new TreeSet<User>(users);
    }

    /**
     *Метод определяет Comparator для метода Collections.sort и сортирует List<User> по длине имени.
     */
    public List<User> sortNameLength(List<User> users) {
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.valueOf(o1.getName().length()).compareTo(o2.getName().length());
            }
        });
        return users;
    }

    /**
     *  метод определяет Comparator для метода Collections.sort и отсортировывет List<User> по обоим полям,
     *  сначала сортировка по имени в лексикографическом порядке, потом по возрасту.
     * @return
     */
    public List<User> sortByAllFields(List<User> users) {
        List<User> result = new ArrayList(users);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName()) == 0 ? Integer.valueOf(o1.getAge()).compareTo(o2.getAge()) : o1.getName().compareTo(o2.getName());
            }
        });
        return result;
    }
}
