package ru.job4j.mtgetfileslist;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;


/**
 * Test
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MtFilteredFileListTest {

    @Test
    public void whenMtSerchingFilesGetFilesList() {
        List<String> ext = new ArrayList<>();
        ext.add("txt");
        ext.add("xml");
        MtFilteredFileList mtFilteredFileList = new MtFilteredFileList("/fileslist", ext);
        mtFilteredFileList.searchFiles();
        mtFilteredFileList.outFilteredFileList();
     }

}