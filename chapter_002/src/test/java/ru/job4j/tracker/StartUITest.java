package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.start.StartUI;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StartUITest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }


    /**
     * Test добавление новой записи в меню пользователя
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();     // создаём Tracker
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});   //создаём StubInput с последовательностью действий
        new StartUI(input, tracker).init();     //   создаём StartUI и вызываем метод init()
        assertThat(tracker.findAll()[0].getName(), is("test name")); // проверяем, что нулевой элемент массива в трекере содержит имя, введённое при эмуляции.
    }

    /**
     * Test редактирование записи в меню пользователя
     */
    @Test
    public void whenUserEditItemThenTrackerShowEditItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Input input = new StubInput(new String[]{"2", item.getId(), "test name2", "desc2", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test name2"));
    }

    /**
     * Test удаление записи в меню пользователя
     */
    @Test
    public void whenUserDeleteItemThenTrackerDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        Item item2 = new Item("test name2", "desc2");
        tracker.add(item2);
        Input input = new StubInput(new String[]{"3", item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name2"));
    }

    /**
     * Test вывод всех заявок
     */
    @Test
    public void whenShowAllItemThenTrackerShowAllItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        item.setId("123");
        Item item2 = new Item("test name2", "desc2");
        tracker.add(item2);
        item2.setId("456");
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\n")
                                .append("0. Добавить новую заявку.\n")
                                .append("1. Показать все заявки.\n")
                                .append("2. Редактировать заявку.\n")
                                .append("3. Удалить заявку.\n")
                                .append("4. Найти заявку по ключу id.\n")
                                .append("5. Найти заявку по имени.\n")
                                .append("6. Выйти из программы.\n")
                                .append("------------ Лист заявок: --------------\n")
                                .append("ID: 123\n")
                                .append("Имя: test name\n")
                                .append("Описание: desc\n\n")
                                .append("ID: 456\n")
                                .append("Имя: test name2\n")
                                .append("Описание: desc2\n\n")
                                .append("Меню.\n")
                                .append("0. Добавить новую заявку.\n")
                                .append("1. Показать все заявки.\n")
                                .append("2. Редактировать заявку.\n")
                                .append("3. Удалить заявку.\n")
                                .append("4. Найти заявку по ключу id.\n")
                                .append("5. Найти заявку по имени.\n")
                                .append("6. Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );


    }

    /**
     * Test поиск заявки по id
     */
    @Test
    public void whenFindByIdAllItemThenTrackerShowFindItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        item.setId("123");
        Item item2 = new Item("test name2", "desc2");
        tracker.add(item2);
        item2.setId("456");
        Input input = new StubInput(new String[]{"4", "456", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\n")
                                .append("0. Добавить новую заявку.\n")
                                .append("1. Показать все заявки.\n")
                                .append("2. Редактировать заявку.\n")
                                .append("3. Удалить заявку.\n")
                                .append("4. Найти заявку по ключу id.\n")
                                .append("5. Найти заявку по имени.\n")
                                .append("6. Выйти из программы.\n")
                                .append("------------ Поиск заявки по id: --------------\n")
                                .append("------------ Заявка с getId: 456\n")
                                .append("Имя: test name2\n")
                                .append("Описание: desc2\n")
                                .append("Меню.\n")
                                .append("0. Добавить новую заявку.\n")
                                .append("1. Показать все заявки.\n")
                                .append("2. Редактировать заявку.\n")
                                .append("3. Удалить заявку.\n")
                                .append("4. Найти заявку по ключу id.\n")
                                .append("5. Найти заявку по имени.\n")
                                .append("6. Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );


    }


    /**
     * Test поиск заявки по имени
     */
    @Test
    public void whenFindByNameAllItemThenTrackerShowFindItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test name", "desc");
        tracker.add(item);
        item.setId("123");
        Item item2 = new Item("test name2", "desc2");
        tracker.add(item2);
        item2.setId("456");
        Item item3 = new Item("test name2", "desc3");
        tracker.add(item3);
        item3.setId("789");
        Input input = new StubInput(new String[]{"5", "test name2", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("Меню.\n")
                                .append("0. Добавить новую заявку.\n")
                                .append("1. Показать все заявки.\n")
                                .append("2. Редактировать заявку.\n")
                                .append("3. Удалить заявку.\n")
                                .append("4. Найти заявку по ключу id.\n")
                                .append("5. Найти заявку по имени.\n")
                                .append("6. Выйти из программы.\n")
                                .append("------------ Поиск заявки по имени: --------------\n")
                                .append("------------ Имя заявки: test name2\n")
                                .append("------------ Перечень найденных заявок:\n")
                                .append("Имя: 456\n")
                                .append("Имя: test name2\n")
                                .append("Описание: desc2\n")
                                .append("Имя: 789\n")
                                .append("Имя: test name2\n")
                                .append("Описание: desc3\n")
                                .append("Меню.\n")
                                .append("0. Добавить новую заявку.\n")
                                .append("1. Показать все заявки.\n")
                                .append("2. Редактировать заявку.\n")
                                .append("3. Удалить заявку.\n")
                                .append("4. Найти заявку по ключу id.\n")
                                .append("5. Найти заявку по имени.\n")
                                .append("6. Выйти из программы.")
                                .append(System.lineSeparator())
                                .toString()
                )
        );


    }

}
