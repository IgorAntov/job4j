package ru.job4j.search;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
import java.util.ArrayList;
import java.util.List;

public class PhoneDictionary {

    private List<Person> persons = new ArrayList<Person>();

    /**
     * Метод добавляет пользователя в список
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Возвращает список всех пользователей, которые содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (var person: this.persons) {
            if (person.getAddress().contains(key) || person.getName().contains(key) || person.getPhone().contains(key) || person.getSurname().contains(key)) {
                result.add(person);
            }
        }
        return result;
    }
}
