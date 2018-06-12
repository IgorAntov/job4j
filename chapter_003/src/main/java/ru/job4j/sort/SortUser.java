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

    }
}
