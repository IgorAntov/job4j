package ru.job4j.tdd;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleGeneratorTest {

    @Test
    public void whenTakeDuplicateKeyStringGetFormatString() {
        //assign
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String inputText = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> map = new HashMap<>();
        map.put("sos", "Aaa");
        //act
        String test = simpleGenerator.generate(inputText, map);
        //action
        Assert.assertThat(test, is("Help, Aaa, Aaa, Aaa"));
    }

    @Test
    public void whenTakeStringGetFormatString() {
        //assign
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String inputText = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Igor");
        map.put("subject", "you");
        //act
        String test = simpleGenerator.generate(inputText, map);
        //action
        Assert.assertThat(test, is("I am a Igor, Who are you?"));
    }

    @Test(expected = ExtraKeysInMapException.class)
    public void whenExtraKeysInMapGetExceptionExtraKeysInMap() {
        //assign
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String inputText = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Igor");
        map.put("subject", "you");
        map.put("title", "Notes");
        //act
        simpleGenerator.generate(inputText, map);
    }

    @Test(expected = MapIsEmptyException.class)
    public void whenNotEnoughKeysInMapGetExceptionMapIsEmpty() {
        //assign
        SimpleGenerator simpleGenerator = new SimpleGenerator();
        String inputText = "I am a ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Igor");
        //act
        simpleGenerator.generate(inputText, map);
    }
}