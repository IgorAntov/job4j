package ru.job4j.comparator;

import java.util.Comparator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        int strLength = o1.length() <= o2.length() ? o1.length() : o2.length();
        for (int index = 0 ; index < strLength; index++ ) {
            if (Character.compare(o1.charAt(index), o2.charAt(index)) != 0) {
                result = Character.compare(o1.charAt(index), o2.charAt(index));
                break;
            }else if ((index == (strLength -1)) && (o1.length() < o2.length()))  {
                result = -1;

            } else if ((index == (strLength -1)) && (o1.length() > o2.length())) {
                result = 1;
            }
        }
        return result;
    }

}