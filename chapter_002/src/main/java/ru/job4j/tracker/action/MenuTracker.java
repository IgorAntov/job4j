package ru.job4j.tracker.action;

import ru.job4j.tracker.storage.ITracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * класс редактирования заявки
 */
class EditItem extends BaseAction {

    EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        if (tracker.listNotEmpty()) {
            String id = input.ask("Введите id заявки");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            if (tracker.replace(id, new Item(name, desc))) {
                System.out.println("------------ Заявка успешно отредактирована. ----------- ");
            } else {
                System.out.println("------------ Заявка с таким Id не найдена. -----");
            }

        } else {
            System.out.println("------------ Лист заявок пуст. ----------- ");
        }
    }
}

/**
 * класс поиск заявок по имени
 */
class FindByName extends BaseAction {

    FindByName(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker) {
        List<Item> result = new ArrayList<>();
        System.out.println("------------ Поиск заявки по имени: --------------");
        String id = input.ask("Введите имя заявки:");
        result = tracker.findByName(id);
        System.out.println("------------ Имя заявки: " + id);
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
}

public class MenuTracker {

    private Input input;
    private ITracker trecker;
    private int position = 0;

    public MenuTracker(Input input, ITracker tracker) {
        this.input = input;
        this.trecker = tracker;
    }

    private List<UserAction> action = new ArrayList<>();

    private Exit exit = this.new Exit();

    public void fillaction() {
        action.add(this.new AddItem(action.size(), "Добавить новую заявку."));
        action.add(new MenuTracker.ShowAll(action.size(), "Показать все заявки."));
        action.add(new EditItem(action.size(), "Редактировать заявку."));
        action.add(this.new DeleteItem(action.size(), "Удалить заявку."));
        action.add(new MenuTracker.FindById(action.size(), "Найти заявку по ключу id."));
        action.add(new FindByName(action.size(), "Найти заявку по имени."));
        action.add(exit);
    }

    public ArrayList<Integer> getRange() {
        ArrayList<Integer> range = new ArrayList<>();
        for (int i = 0; i < action.size(); i++) {
            range.add(i);
        }
        return range;
    }


    public void select(int key) {
        action.get(key).execute(this.input, this.trecker);
    }

    public boolean exit() {
        return exit.getExit();
    }

    public void show() {
        System.out.println("Меню.");
        for (UserAction action : this.action) {
            if (action != null) {
                System.out.print(action.info());
            }
        }
    }

    /**
     * класс - показать все заявки
     */
    public class ShowAll extends BaseAction {

        ShowAll(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            List<Item> result = new ArrayList<>();
            System.out.println("------------ Лист заявок: --------------");
            result = tracker.findAll();
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
    }

    /**
     * класс поиск заявки по id
     */
    public class FindById extends BaseAction {

        FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Поиск заявки по id: --------------");
            String id = input.ask("Введите id заявки:");
            Item result = tracker.findById(id);
            if (result != null) {
                System.out.println("------------ Заявка с getId: " + id);
                System.out.println("Имя: " + result.getName());
                System.out.println("Описание: " + result.getDescription());

            } else {
                System.out.println("------------ Заявка с getId не найдена: " + id);

            }
        }

    }

    /**
     * класс добавления заявки
     */
    public class AddItem extends BaseAction {

        AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            System.out.println("------------ Добавление новой языки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с getId : " + item.getId() + "добавлена ------------");
        }
    }


    /**
     * класс удаления заявки
     */
    public class DeleteItem extends BaseAction {

        DeleteItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            if (tracker.listNotEmpty()) {
                System.out.println("------------ Удаление заявки --------------");
                String id = input.ask("Введите id заявки которую требуется удалить:");
                if (tracker.delete(id)) {
                    System.out.println("------------ Заявка с getId удалена: " + id);
                } else {
                    System.out.println("------------ Заявка с таким Id не найдена. ---- " + id);
                }
            } else {
                System.out.println("------------ Лист заявок пуст. ----------- ");
            }
        }
    }

    public class Exit implements UserAction {

        private boolean exit = true;

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            exit = false;
        }

        boolean getExit() {
            return this.exit;
        }

        @Override
        public String info() {
            return String.format("%s. %s\n", this.key(), "Выйти из программы.");
        }
    }

}
