package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.storage.Tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class TrackerTest {

    /**
     * Test добавление новой записи (заявки)
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));

    }

    /**
     * Test поиск по id
     */
    @Test
    public void whenFindByIdItemThenTrackerCouldFinedById() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2");
        tracker.add(item1);
        Item item2 = new Item("test1", "testDescription");
        assertThat(tracker.findById(item.getId()), is(item));
    }

    /**
     * Test поиск по Имени
     */
    @Test
    public void whenFindByNameItemThenTrackerCouldFinedByNameItems() {
        Tracker tracker = new Tracker();
        Item[] expect = new Item[2];
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2");
        expect[0] = item1;
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription3");
        tracker.add(item2);
        expect[1] = item2;
        assertThat(tracker.findByName("test2").get(0).getName(), is(expect[0].getName()));
        assertThat(tracker.findByName("test2").get(1).getName(), is(expect[1].getName()));
    }

    /**
     * Test удаление записи (заявки)
     */
    @Test
    public void whenDeleteItemThenTrackerHasTwoItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2");
        tracker.add(item1);
        Item item2 = new Item("test2", "testDescription3");
        tracker.add(item2);
        tracker.delete(item1.getId());
        assertThat(tracker.findById(item.getId()), is(item));
        assertThat(tracker.findById(item2.getId()), is(item2));
        Item expect = null;
        assertThat(tracker.findById(item1.getId()), is(expect));
    }

    @Test
    public void whenDelete2ItemThenTrackerHasTwoItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2");
        tracker.add(item1);
        Item item2 = new Item("test3", "testDescription3");
        tracker.add(item2);
        tracker.delete(item.getId());
        assertThat(tracker.findAll().get(0).getName(), is("test2"));
    }


    /**
     * Test редактирование заявки
     */
    @Test
    public void whenReplaceItemThenEditedItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        Item item1 = new Item("test2", "testDescription2");
        tracker.replace(item.getId(), item1);
        Item result = tracker.findById(item1.getId());
        assertThat(result.getName(), is(item1.getName()));
        assertThat(result.getDescription(), is(item1.getDescription()));
    }

    /**
     * Test найти все заявки
     */
    @Test
    public void whenfindItemsThenAllItems() {
        Tracker tracker = new Tracker();
        Item[] expect = new Item[3];
        Item item = new Item("test1", "testDescription");
        tracker.add(item);
        expect[0] = item;
        Item item1 = new Item("test2", "testDescription2");
        tracker.add(item1);
        expect[1] = item1;
        Item item2 = new Item("test3", "testDescription3");
        tracker.add(item2);
        expect[2] = item2;
        Item result = tracker.findById(item.getId());
        assertThat(result, is(expect[0]));
        result = tracker.findById(item1.getId());
        assertThat(result, is(expect[1]));
        result = tracker.findById(item2.getId());
        assertThat(result, is(expect[2]));
    }

}
