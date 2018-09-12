package ru.job4j.quiz;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CompareWordsTest {

    @Test
    public void compareWordTest() {
        CompareWords cw = new CompareWords();
        String a = "abcdef";
        String b = "fedcba";
        ArrayList<Character> str1 = new ArrayList();
        ArrayList<Character> str2 = new ArrayList();
        for(int i = 0; i < a.length(); i++) {
            str1.add(a.charAt(i));
        }
        for(int i = 0; i < b.length(); i++) {
            str2.add(b.charAt(i));
        }
        assertThat(cw.eql(str1, str2), is(true));
    }
}
