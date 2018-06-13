package ru.job4j.sort;

import java.util.*;

public class SortUser {

    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания
     */
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<User>(users);
        return result;
    }

    /**
     *Метод определяет Comparator для метода Collections.sort и сортирует List<User> по длине имени.
     */
    public List<User> sortNameLength(List<User> users) {
        List<User> result = new ArrayList(users);
        result.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return Integer.valueOf(o1.getName().length()).compareTo(o2.getName().length());
            }
        });
        return result;
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

    public static void main(String[] args) {

        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(
                new User("vadim", 25),
                new User("igor", 30),
                new User("vasiliy", 5),
                new User("andrey", 10)));

        Set<User> result = sortUser.sort(users);

        for (User user : result) {
            System.out.println(user.getName() + " " + user.getAge());
        }

        List<User> users2 = new ArrayList<>();
        users2.addAll(Arrays.asList(
                new User("Сергей", 25),
                new User("Иван", 30),
                new User("Сергей", 20),
                new User("Иван", 25)));
        List<User> users3 = sortUser.sortNameLength(users2);
        for (User user : users3) {
            System.out.println(user.getName() + " " + user.getAge());
        }

        users3 = sortUser.sortByAllFields(users2);
        for (User user : users3) {
            System.out.println(user.getName() + " " + user.getAge());
        }

    }
}
