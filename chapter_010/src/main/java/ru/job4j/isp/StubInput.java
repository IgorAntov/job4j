package ru.job4j.isp;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class StubInput implements Input {

    private String itemTitle;

    public StubInput(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    /**
     * Method simulates user input.
     * @param s invitation.
     * @return ItemTitle
     */
    @Override
    public String ask(String s) {
        return this.itemTitle;
    }
}
