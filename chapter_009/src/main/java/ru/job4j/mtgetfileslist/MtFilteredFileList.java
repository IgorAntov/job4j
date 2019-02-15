package ru.job4j.mtgetfileslist;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MtFilteredFileList {

    private final Queue<File> fileList = new LinkedList<>();
    private final List<File> filteredFileList = new LinkedList<>();
    List<String> ext;
    private String parent;
    private static boolean endOfList = true;

    public static void setEndOfList(boolean endOfList) {
        MtFilteredFileList.endOfList = endOfList;
    }

    public static boolean isEndOfList() {
        return endOfList;
    }

    public MtFilteredFileList(String parent, List<String> ext) {
        this.parent = parent;
        this.ext = ext;
    }

    public void outFilteredFileList() {
        for (File file: filteredFileList) {
            System.out.println(file.getName());
        }
    }

    public void searchFiles() {
        MtGetFilesList mtGetFilesList = new MtGetFilesList(fileList, parent);
        MtCheckExte mtCheckExte = new MtCheckExte(ext, fileList, filteredFileList);
        mtGetFilesList.start();
        mtCheckExte.start();
        try {
            mtGetFilesList.join();
            mtCheckExte.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}