package ru.job4j.quiz;

import java.util.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CompareWords {

    public boolean eql(ArrayList arrayList1, ArrayList arrayList2) {
        boolean result = false;
        if (arrayList1.size() == arrayList2.size()) {
            arrayList1.sort(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return (char) o1 - (char) o2;
                }
            });
            arrayList2.sort(new Comparator() {
                @Override
                public int compare(Object o1, Object o2) {
                    return (char) o1 - (char) o2;
                }
            });

            for (int i = 0; i < arrayList1.size(); i++) {
                if (arrayList1.get(i) != arrayList2.get(i)) {
                    result = false;
                    break;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

}
