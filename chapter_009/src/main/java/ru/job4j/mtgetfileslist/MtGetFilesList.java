package ru.job4j.mtgetfileslist;

import java.io.File;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * This thread does files list
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class MtGetFilesList  extends Thread {
    private Queue<File> data;
    private Queue<File> queueFiles = new LinkedList<>();
    private String parent;

    public MtGetFilesList(Queue<File> fileList, String parent) {
        this.data = fileList;
        this.parent = parent;
    }

    @Override
    public void run() {
        System.out.println("start searching");
        String path = System.getProperty("java.io.tmpdir");
        File file = new File(path + parent);
        queueFiles.offer(file);
        while (!currentThread().isInterrupted()) {
            File tempFile = queueFiles.poll();
            if (tempFile != null) {
                if (tempFile.isDirectory()) {
                    for (File dir : Objects.requireNonNull(tempFile.listFiles())) {
                        queueFiles.offer(dir);
                    }
                } else {
                    synchronized (this.data) {
                        data.offer(tempFile);
                        this.data.notifyAll();
                    }
                }
            } else {
                MtFilteredFileList.setEndOfList(false);
                currentThread().interrupt();
                synchronized (this.data) {
                    this.data.notifyAll();
                }
            }
        }
    }
}