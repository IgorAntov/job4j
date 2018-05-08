package ru.job4j.tracker;

import java.util.Arrays;
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
    private final Item[] items = new Item[100];

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
        this.items[this.position++] = item;
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
        for (int index = 0; index != position; index++) {
            if (this.items[index].getId().equals(id)) {
                item.setId(this.items[index].getId());
                this.items[index] = item;
                break;
            }
        }
    }

    /**
     * Метод реализаущий удаление заявки из хранилища
     * @param id уникальный ключ
     */
    public void delete(String id) {
        for (int index = 0; index != position; index++) {
            if (this.items[index].getId().equals(id)) {
                for (int j = index; j != position; j++) {
                    items[j] = items[j + 1];
                }
                position--;
                break;
            }
        }
    }

    /**
     * Получение списка всех заявок
     * @return лист заявок
     */
    public Item[] findAll() {
        Item[] result = new Item[position];
        for (int index = 0; index != position; index++) {
            result[index] = this.items[index];
        }
        return result;
    }

    /**
     * Метод реализаущий поиск заявок по имени
     * @param key ключ поиска (по имени)
     * @return все заявки с одинаковым именем (key)
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            if (items[index].getName().equals(key)) {
                result[count] = this.items[index];
                count++;
            }
        }
        return Arrays.copyOf(result, count);
    }


    /**
     * Метод реализаущий поиск заявки по id
     * @param id ключ поиска по id
     * @return заявка по ключу поиска id
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }

}
