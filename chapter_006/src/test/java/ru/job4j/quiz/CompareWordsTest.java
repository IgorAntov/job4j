package ru.job4j.quiz;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CompareWordsTest {

    @Test
    public void testComperaWordsFunction() {
        String a = "abcdef";
        String b = "fedcba";
        CompareWords cw = new CompareWords();
        assertThat(cw.eql(a, b), is(true));
        b = "fegcba";
        assertThat(cw.eql(a, b), is(false));
    }
}
