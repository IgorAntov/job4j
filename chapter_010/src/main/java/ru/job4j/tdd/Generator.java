package ru.job4j.tdd;

import java.util.Map;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface Generator {
    String generate(String template, Map<String, String> map);
}
