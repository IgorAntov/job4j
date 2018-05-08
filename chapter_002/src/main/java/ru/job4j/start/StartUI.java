package ru.job4j.start;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех заявок.
     */
    private static final String SHOW_ALL = "1";
    /**
     * Константа меню для редактирования заявки
     */
    private static final String EDIT = "2";
    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа меню для поиска по id.
     */
    private static final String FIND_BY_ID = "4";
    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;
    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAll();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findByIdItem();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findByNameItem();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }
    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой языки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "добавлена ------------");
    }

    /**
     * Метод реализует вывод всех заявок.
     */
    private void showAll() {
        Item[] result = null;
        System.out.println("------------ Лист заявок: --------------");
        result =  this.tracker.findAll();
        if (result != null) {
            for (Item item : result) {
                System.out.println("ID: " + item.getId());
                System.out.println("Имя: " + item.getName());
                System.out.println("Описание: " + item.getDescription());
                System.out.println("");
            }
        } else {
            System.out.println("Лист заявок пуст.");
        }
    }

    /**
     * Метод реализует редактированаие заявки.
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки --------------");
        String id = this.input.ask("Введите id заявки :");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.replace(id, item);
        System.out.println("------------ Заявка с getId отредактирована: " + item.getId() + "-----------");
        System.out.println("Новое имя: " + item.getName());
        System.out.println("Новое описание: " + item.getDescription());
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите id заявки которую требуется удалить:");
        this.tracker.delete(id);
        System.out.println("------------ Заявка с getId удалена: " + id);
    }

    /**
     * Метод реализует поиск заявки по id.
     */
    private void findByIdItem() {
        System.out.println("------------ Поиск заявки по id: --------------");
        String id = this.input.ask("Введите id заявки:");
        Item result = this.tracker.findById(id);
        if (result != null) {
            System.out.println("------------ Заявка с getId: " + id);
            System.out.println("Имя: " + result.getName());
            System.out.println("Описание: " + result.getDescription());

        } else {
            System.out.println("------------ Заявка с getId не найдена: " + id);

        }
    }

    /**
     * Метод реализует поиск заявки по имени.
     */
    private void findByNameItem() {
        Item[] result = null;
        System.out.println("------------ Поиск заявки по имени: --------------");
        String id = this.input.ask("Введите имя заявки:");
        result = this.tracker.findByName(id);
        System.out.println("------------ Заявка с getId: " + id);
        if (result != null) {
            System.out.println("------------ Перечень найденных заявок:");
            for (Item item : result) {
                System.out.println("Имя: " + item.getId());
                System.out.println("Имя: " + item.getName());
                System.out.println("Описание: " + item.getDescription());
            }
        } else {
            System.out.println("Лист заявок пуст.");
        }

    }

    /**
     * Метод вывода меню в консоль.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку.");
        System.out.println("1. Показать все заявки.");
        System.out.println("2. Редактировать заявку.");
        System.out.println("3. Удалить заявку.");
        System.out.println("4. Найти заявку по ключу id.");
        System.out.println("5. Найти заявку по имени.");
        System.out.println("6. Выйти из программы.");
    }
    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
