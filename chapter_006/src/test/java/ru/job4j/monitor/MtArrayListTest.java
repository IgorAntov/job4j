package ru.job4j.monitor;

import org.junit.Test;
import ru.job4j.monitor.arrays.MtArrayList;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/** Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MtArrayListTest {

    @Test
    public void mtArrayTestMethods() {

        MtArrayList<Integer> mtArrayList = new MtArrayList<>();

        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 2000; i++) {
                    mtArrayList.add(i);
                }
                Iterator it = mtArrayList.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                for (int i = 2000; i <= 3000; i++) {
                    mtArrayList.add(i);
                }
                Iterator it = mtArrayList.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
        }.start();
    }
}