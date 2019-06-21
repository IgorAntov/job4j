package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UserConvert {

    /**
     * Метод преобразует лист users в HashMap и в качесте ключа испольузет user.id
     * @param list
     * @return
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user: list) {
         result.put(user.getId(), user);
        }
        return result;
    }
}
