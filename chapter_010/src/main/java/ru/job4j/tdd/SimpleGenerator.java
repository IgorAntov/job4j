package ru.job4j.tdd;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class SimpleGenerator implements Generator {
    private final Pattern keys = Pattern.compile("\\$\\{.+?}");

    /**
     * Method generates text based on keys and values ​​to them.
     * @param template incoming text.
     * @param map key-value map.
     * @return
     */
    @Override
    public String generate(String template, Map<String, String> map) {
        Matcher matcher = keys.matcher(template);
        while (matcher.find()) {
            String key = matcher.group().substring(2, matcher.group().length() - 1);
            if (map.containsKey(key)) {
                template = template.replaceAll(String.format("\\$\\{%s}", key), map.get(key));
                matcher = keys.matcher(template);
                map.remove(key);
            } else {
                throw new MapIsEmptyException("Map is empty. Not enough keys in the map.");
            }
        }
        if (!map.isEmpty()) {
            throw new ExtraKeysInMapException("There are extra keys in the map.");
        }
        return template;
    }
}
