package ru.job4j.tracker.action;

import ru.job4j.tracker.storage.ITracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * класс редактирования заявки
 */
class EditItem extends BaseAction {

    EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        if (tracker.listNotEmpty()) {
            String id = input.ask("Введите id заявки");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            if (tracker.replace(id, new Item(name, desc))) {
                output.accept("------------ Заявка успешно отредактирована. ----------- ");
            } else {
                output.accept("------------ Заявка с таким Id не найдена. -----");
            }

        } else {
            output.accept("------------ Лист заявок пуст. ----------- ");
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
    public void execute(Input input, ITracker tracker, Consumer<String> output) {
        List<Item> result = new ArrayList<>();
        output.accept("------------ Поиск заявки по имени: --------------");
        String id = input.ask("Введите имя заявки:");
        result = tracker.findByName(id);
        output.accept("------------ Имя заявки: " + id);
        if (result != null) {
            output.accept("------------ Перечень найденных заявок:");
            for (Item item : result) {
                output.accept("Имя: " + item.getId());
                output.accept("Имя: " + item.getName());
                output.accept("Описание: " + item.getDescription());
            }
        } else {
            output.accept("Лист заявок пуст.");
        }
    }
}

public class MenuTracker {

    private Input input;
    private ITracker trecker;
    private int position = 0;
    private final Consumer<String> output;

    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.trecker = tracker;
        this.output = output;
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
        action.get(key).execute(this.input, this.trecker, this.output);
    }

    public boolean exit() {
        return exit.getExit();
    }

    public void show() {
        output.accept("Меню.");
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
        public void execute(Input input, ITracker tracker, Consumer<String> output) {
            List<Item> result = new ArrayList<>();
            output.accept("------------ Лист заявок: --------------");
            result = tracker.findAll();
            if (result != null) {
                for (Item item : result) {
                    output.accept("ID: " + item.getId());
                    output.accept("Имя: " + item.getName());
                    output.accept("Описание: " + item.getDescription());
                    output.accept("");
                }
            } else {
                output.accept("Лист заявок пуст.");
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
        public void execute(Input input, ITracker tracker, Consumer<String> output) {
            output.accept("------------ Поиск заявки по id: --------------");
            String id = input.ask("Введите id заявки:");
            Item result = (Item) tracker.findById(id);
            if (result != null) {
                output.accept("------------ Заявка с getId: " + id);
                output.accept("Имя: " + result.getName());
                output.accept("Описание: " + result.getDescription());

            } else {
                output.accept("------------ Заявка с getId не найдена: " + id);

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
        public void execute(Input input, ITracker tracker, Consumer<String> output) {
            output.accept("------------ Добавление новой языки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("------------ Новая заявка с getId : " + item.getId() + "добавлена ------------");
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
        public void execute(Input input, ITracker tracker, Consumer<String> output) {
            if (tracker.listNotEmpty()) {
                output.accept("------------ Удаление заявки --------------");
                String id = input.ask("Введите id заявки которую требуется удалить:");
                if (tracker.delete(id)) {
                    output.accept("------------ Заявка с getId удалена: " + id);
                } else {
                    output.accept("------------ Заявка с таким Id не найдена. ---- " + id);
                }
            } else {
                output.accept("------------ Лист заявок пуст. ----------- ");
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
        public void execute(Input input, ITracker tracker, Consumer<String> output) {
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
