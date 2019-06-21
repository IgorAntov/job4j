package ru.job4j.mtgetfileslist;

import java.io.File;
import java.util.List;
import java.util.Queue;

/**
 * This thread filters by extension
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MtCheckExte extends Thread {

    List<String> ext;
    private final Queue<File> fileList;
    private final List<File> filteredFileList;

    public MtCheckExte(List<String> ext, Queue<File> fileList, List<File> filteredFileList) {
        this.ext = ext;
        this.fileList = fileList;
        this.filteredFileList = filteredFileList;
    }

    @Override
    public void run() {
        System.out.println("start ext. checking ");
        while (!this.fileList.isEmpty() || MtFilteredFileList.isEndOfList()) {
            synchronized (this.fileList) {
                while (this.fileList.isEmpty() && MtFilteredFileList.isEndOfList()) {
                    try {
                        this.fileList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                File file = fileList.poll();
                if (file != null) {
                    String name = file.getName();
                    int i = name.lastIndexOf('.');
                    String extension = i > 0 ? name.substring(i + 1) : "";
                    for (String e : ext) {
                        if (e.equals(extension)) {
                            filteredFileList.add(file);
                        }
                    }
                }
            }
        }
    }
}