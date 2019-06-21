package ru.job4j.isp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MenuTest {

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }


    @Test
    public void whenSelectMenuItemOutIsWelcome() {
        Menu menu = new Menu(new StubInput("SubMenu1Item111"));
        Item rootMenu1 = new Item("RootMenu1");
        Item rootMenu2 = new Item("RootMenu2");
        menu.addItem(rootMenu1);
        menu.addItem(rootMenu2);
        Item subMenu1Item11 = new Item("SubMenu1Item11");
        Item subMenu1Item12 = new Item("SubMenu1Item12");
        menu.addItem(rootMenu1, subMenu1Item11);
        menu.addItem(rootMenu1, subMenu1Item12);
        Item subMenu1Item21 = new Item("SubMenu1Item21");
        Item subMenu1Item22 = new Item("SubMenu1Item22");
        menu.addItem(rootMenu2, subMenu1Item21);
        menu.addItem(rootMenu2, subMenu1Item22);
        Item subMenu1Item111 = new Item("SubMenu1Item111");
        Item subMenu1Item112 = new Item("SubMenu1Item112");
        menu.addItem(subMenu1Item11, subMenu1Item111);
        menu.addItem(subMenu1Item11, subMenu1Item112);
        menu.showMenu();
        assertThat(
                new String(out.toByteArray()), is(" Root\n"
                        + "     RootMenu1\n"
                        + "         SubMenu1Item11\n"
                        + "             SubMenu1Item111\n"
                        + "             SubMenu1Item112\n"
                        + "         SubMenu1Item12\n"
                        + "     RootMenu2\n"
                        + "         SubMenu1Item21\n"
                        + "         SubMenu1Item22\n"
                        + "Welcome.\n"));
    }
}