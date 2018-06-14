package ru.job4j.tracker.storage;

import ru.job4j.tracker.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Tracker {

    /**
     * Массив для хранение заявок.
     */
    private final List<Item>  items = new ArrayList<>();

    private static final Random RN = new Random();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Генерирует на основании времени и произвольного числа.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        //Реализовать метод генерации.
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод редактирование заявок
     * @param id уникальный ключ
     * @param item - класс заявка
     */
    public void replace(String id, Item item) {
        for (int index = 0; index < items.size(); index++) {
            if (items.get(index).getId().equals(id)) {
                item.setId(this.items.get(index).getId());
                items.set(index, item);
                break;
            }
        }
    }

    /**
     * Метод реализаущий удаление заявки из хранилища
     * @param id уникальный ключ
     */
    public void delete(String id) {
        for (Item value : items) {
            if (value.getId().equals(id)) {
                items.remove(value);
                break;
            }
        }
    }

    /**
     * Получение списка всех заявок
     * @return лист заявок
     */
    public List<Item> findAll() {

        return items;
    }

    /**
     * Метод реализаущий поиск заявок по имени
     * @param key ключ поиска (по имени)
     * @return все заявки с одинаковым именем (key)
     */
    public List<Item> findByName(String key) {
        int count = 0;
        List<Item> result = new ArrayList<>();
        for (Item value : items) {
            if (value.getName().equals(key)) {
                result.add(value);
            }
        }
        return result;
    }

    /**
     * Метод реализаущий поиск заявки по id
     * @param id ключ поиска по id
     * @return заявка по ключу поиска id
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }
}
