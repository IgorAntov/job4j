package ru.job4j.interactcalc;

import ru.job4j.srp.InteractCalc;
import ru.job4j.srp.input.ConsoleInput;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class InteractCalcTest {

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
    public void whenAdd10Sub5Mul3Div3ThenResult5() {
        InputStream inputStream = new ByteArrayInputStream("0\n10\n1\n5\n2\n3\n3\n3\n4".getBytes());
        InteractCalc interactCalc = new InteractCalc();
        interactCalc.init(new ConsoleInput(inputStream));
        interactCalc.userInterface();
        assertThat(
                new String(out.toByteArray()),
                is("--------------------\n"
                        + "Result = 0,000000\n"
                        + "Actions list:\n"
                        + "0. Add\n"
                        + "1. Sub\n"
                        + "2. Mul\n"
                        + "3. Div\n"
                        + "4. Exit\n"
                        + "Input action:\n"
                        + "Input value:\n"
                        + "--------------------\n"
                        + "Result = 10,000000\n"
                        + "Actions list:\n"
                        + "0. Add\n"
                        + "1. Sub\n"
                        + "2. Mul\n"
                        + "3. Div\n"
                        + "4. Exit\n"
                        + "Input action:\n"
                        + "Input value:\n"
                        + "--------------------\n"
                        + "Result = 5,000000\n"
                        + "Actions list:\n"
                        + "0. Add\n"
                        + "1. Sub\n"
                        + "2. Mul\n"
                        + "3. Div\n"
                        + "4. Exit\n"
                        + "Input action:\n"
                        + "Input value:\n"
                        + "--------------------\n"
                        + "Result = 15,000000\n"
                        + "Actions list:\n"
                        + "0. Add\n"
                        + "1. Sub\n"
                        + "2. Mul\n"
                        + "3. Div\n"
                        + "4. Exit\n"
                        + "Input action:\n"
                        + "Input value:\n"
                        + "--------------------\n"
                        + "Result = 5,000000\n"
                        + "Actions list:\n"
                        + "0. Add\n"
                        + "1. Sub\n"
                        + "2. Mul\n"
                        + "3. Div\n"
                        + "4. Exit\n"
                        + "Input action:\n"));
    }
}