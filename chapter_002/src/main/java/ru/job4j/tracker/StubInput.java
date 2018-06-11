package ru.job4j.tracker;

import java.util.List;

public class StubInput implements Input {

    private final String[] value;
    private int position;

    StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value: range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Выход за пределы диапазона значений.");
        }
        return key;
    }

}
