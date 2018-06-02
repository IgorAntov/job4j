package ru.job4j.tracker;

/**
 * класс редактирования заявки
 */
class EditItem extends BaseAction {

    EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id заявки");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        tracker.replace(id, new Item(name, desc));
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
    public void execute(Input input, Tracker tracker) {
        Item[] result;
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
    private Tracker trecker;
    private int position = 0;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.trecker = tracker;
    }

    private UserAction[] action = new UserAction[7];

    private Exit exit = this.new Exit();

    public void fillaction() {
        action[position] = this.new AddItem(position, "Добавить новую заявку.");
        position++;
        action[position] = new MenuTracker.ShowAll(position, "Показать все заявки.");
        position++;
        action[position] = new EditItem(position, "Редактировать заявку.");
        position++;
        action[position] = this.new DeleteItem(position, "Удалить заявку.");
        position++;
        action[position] = new MenuTracker.FindById(position, "Найти заявку по ключу id.");
        position++;
        action[position] = new FindByName(position, "Найти заявку по имени.");
        position++;
        action[position] = exit;
    }

    public int[] getRange() {
        int[] range = new int[action.length];
        for (int i = 0; i < range.length; i++) {
            range[i] = i;
        }
        return range;
    }

    public void select(int key) {
        this.action[key].execute(this.input, this.trecker);
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
        public void execute(Input input, Tracker tracker) {
            Item[] result;
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
        public void execute(Input input, Tracker tracker) {
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
        public void execute(Input input, Tracker tracker) {
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
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите id заявки которую требуется удалить:");
            tracker.delete(id);
            System.out.println("------------ Заявка с getId удалена: " + id);
        }
    }

    public class Exit implements UserAction {

        private boolean exit = true;

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
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
