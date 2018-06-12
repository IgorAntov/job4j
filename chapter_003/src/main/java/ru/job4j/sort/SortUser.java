package ru.job4j.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    /**
     * Метод возвращает TreeSet пользователей, отсортированных по возрасту в порядке возрастания
     */
    public Set<User> sort(List<User> users) {
        Set<User> result = new TreeSet<User>();
        return result;
    }

    public static void main(String[] args) {

        SortUser sortUser = new SortUser();
        Set<User> users = new TreeSet<>();
                users.addAll(Arrays.asList(
                        new User("igor", 25),
                        new User("ivan", 30),
                        new User("vasiliy", 5),
                        new User("andrey", 10)));

       for (User user : users) {
           System.out.println(user.getName() + " " + user.getAge());
       }
    }

}
