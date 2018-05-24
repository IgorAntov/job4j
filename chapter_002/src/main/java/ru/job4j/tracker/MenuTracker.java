package ru.job4j.tracker;

/**
 * внешний внутренний класс редактирования заявки
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        return 2;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id заявки");
        String name = input.ask("Введите имя заявки :");
        String desc = input.ask("Введите описание заявки :");
        tracker.replace(id, new Item(name, desc));
    }

    @Override
    public String info() {
        return String.format("%s. %s\n", key(), "Редактировать заявку.");
    }
}

/**
 * внешний внутренний класс поиск заявок по имени
 */
class FindByName implements UserAction {

    @Override
    public int key() {
        return 5;
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

    @Override
    public String info() {
        return String.format("%s. %s\n", key(), "Найти заявку по имени.");
    }
}

public class MenuTracker {

    private Input input;
    private Tracker trecker;

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.trecker = tracker;
    }

    private UserAction[] action = new UserAction[7];

    private Exit exit = this.new Exit();

    public void fillaction() {
        action[0] = this.new AddItem();
        action[1] = new MenuTracker.ShowAll();
        action[2] = new EditItem();
        action[3] = this.new DeleteItem();
        action[4] = new MenuTracker.FindById();
        action[5] = new FindByName();
        action[6] = exit;
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
     * внутренний статический класс - показать все заявки
     */
    public static class ShowAll implements UserAction {

        @Override
        public int key() {
            return 1;
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

        @Override
        public String info() {
            return String.format("%s. %s\n", key(), "Показать все заявки.");
        }
    }

    /**
     * внутренний статический класс поиск заявки по id
     */
    public static class FindById implements UserAction {

        @Override
        public int key() {
            return 4;
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

        @Override
        public String info() {
            return String.format("%s. %s\n", key(), "Найти заявку по ключу id.");
        }
    }

    /**
     * внутренний класс добавления заявки
     */
    public class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s. %s\n", this.key(), "Добавить новую заявку.");
        }
    }

    /**
     * внутренний класс удаления заявки
     */
    public class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите id заявки которую требуется удалить:");
            tracker.delete(id);
            System.out.println("------------ Заявка с getId удалена: " + id);
        }

        @Override
        public String info() {
            return String.format("%s. %s\n", this.key(), "Удалить заявку.");
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
