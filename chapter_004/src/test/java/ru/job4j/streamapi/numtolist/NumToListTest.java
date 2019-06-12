package ru.job4j.streamapi.numtolist;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class NumToListTest {

    @Test
    public void matrixToList() {
        Integer[][] matrix = {{1, 2}, {3, 4}};
        List<Integer> numList = new ArrayList<>(Arrays.asList(matrix[0]));
        numList.addAll(Arrays.asList(matrix[1]));
        NumToList numToList = new NumToList();
        Assert.assertThat(numToList.matrixToList(matrix), is(numList));
    }
}