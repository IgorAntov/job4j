package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.input.ValidateInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ValidateInputTest {

    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"x", "1"})
        );
        input.ask("Enter", Arrays.asList(new Integer[] {1}));

        assertThat(
                this.mem.toString(),
                is(
                        String.format("Введите корректные данные.%n")
                )
        );
    }

    @Test
    public void whenNoFromMenuRangeInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"5", "1"})
        );
        input.ask("Enter", Arrays.asList(new Integer[] {1}));
        assertThat(
                this.mem.toString(),
                is(
                        String.format("Введите значение из диаппазона меню.%n")
                )
        );
    }
}
