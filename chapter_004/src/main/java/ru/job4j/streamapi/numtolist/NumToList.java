package ru.job4j.streamapi.numtolist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class NumToList {
    //Method one
    List<Integer> matrixToList(Integer[][] value) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < value[0].length; i++) {
            list.add(Arrays.asList(value[i]));
        }
        return list.stream().flatMap(List::stream).collect(Collectors.toList());
     //Method two
     //return Arrays.stream(value).flatMap(Arrays::stream).collect(Collectors.toList());
    }
}
