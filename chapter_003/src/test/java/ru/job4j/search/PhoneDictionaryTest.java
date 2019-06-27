package ru.job4j.search;
import org.testng.annotations.Test;

/**
 * Test.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        var phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        var persons = phones.find("Petr");
        assertThat(persons.iterator().next().getSurname(), is("Arsentev"));
    }
}