package ru.job4j.isp;

import java.util.*;


/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Menu implements MenuManager {

    private Input input;
    private final Item root;
    private int menuPosition = 0;

    public Menu(Input input) {
        this.root = new Item("Root");
        this.input = input;
    }

    @Override
    public boolean addItem(Item rootMenuItem) {
        this.root.getChildren().add(rootMenuItem);
        return true;
    }

    @Override
    public Item findBy(Item item) {
        Item rsl = null;
        Queue<Item> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Item el = data.poll();
            if (el.eqValue(item)) {
                rsl = el;
                break;
            }
            for (Item child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Method searches MenuItem in the MenuTree by ItemMenuName.
     * @param title ItemMenuName.
     * @return found MenuItem
     */
    private Item findByName(String title) {
        Item rsl = null;
        Queue<Item> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Item el = data.poll();
            if (el.getTitle().equals(title)) {
                rsl = el;
                break;
            }
            for (Item child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Method validates MenuItem that was inputed by user.
     * If such a MenuItem is in the MenuTree, it displays an introduction "Welcome".
     * @param s MenuItem as String.
     * @return Item.
     */
    private Item validateInput(String s) {
        Item item;
        boolean invalid = true;
        do {
            item =  findByName(this.input.ask(s));
            if (item != null) {
                invalid = false;
                System.out.println("Welcome.");
            } else {
                System.out.println("Enter a value from the menu range.");
            }
        } while (invalid);
        return item;
    }

    @Override
    public boolean addItem(Item parent, Item child) {
        boolean result;
        Item e;
        e = findBy(parent);
        if (e != null) {
            e.getChildren().add(child);
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    /**
     * Formats output menu.
     * @return offset.
     */
    private String getPosition() {
        String str = " ";
        for (int i = this.menuPosition; i > 0; i--) {
            str += "    ";
        }
        return str;
    }

    /**
     * Method outputs MenuTree.
     * @param item Root ItemMenu
     */
    private void showTreeMenu(Item item) {
        Queue<Item> data = new LinkedList<>();
        data.offer(item);
        while (!data.isEmpty()) {
            Item el = data.poll();
            if (!el.leaves().isEmpty()) {
                System.out.println(getPosition() + el.getTitle());
                for (Item child : el.leaves()) {
                    menuPosition++;
                    showTreeMenu(child);
                    menuPosition--;
                }
            } else {
                System.out.println(getPosition() + el.getTitle());
            }
        }
    }

    /**
     * Requests ItemMenu from User.
     * @param s ItemMenu as a String.
     */
    private void selectMenu(String s) {
        validateInput(s);
    }

    /**
     * Method runs User Interface.
     */
    public void showMenu() {
        showTreeMenu(root);
        selectMenu("Input MenuItem:");
    }
}
