package ru.job4j.getfileslist;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class GetFilesListTest {

    @Test
    public void whenSerchingFilesGetFilesList() {
        GetFilesList getFilesList = new GetFilesList();
        List<String> ext = new ArrayList<>();
        ext.add("txt");
        ext.add("xml");
        for (File tf : getFilesList.files("/fileslist", ext)) {
            System.out.println(tf.getName());
        }
    }
}