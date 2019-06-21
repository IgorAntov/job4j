package ru.job4j.quiz;

import java.util.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CompareWords {

    public boolean eql(String wordOne, String wordTwo) {
        HashMap<Character, Integer> array = new HashMap<>();
        boolean result = false;
        if (wordOne.length() == wordTwo.length()) {
            for (int i = 0; i < wordOne.length(); i++) {
                array.put(wordOne.charAt(i), i);
            }
            for (int i = 0; i < wordOne.length(); i++) {
                array.remove(wordTwo.charAt(i));
            }
            if (array.isEmpty()) {
                result = true;
            }
        }
        return result;
    }
}
