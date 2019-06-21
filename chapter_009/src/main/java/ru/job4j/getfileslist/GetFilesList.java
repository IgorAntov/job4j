package ru.job4j.getfileslist;

import java.io.File;
import java.util.*;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class GetFilesList {
    /**
     * This Method do searching files in specific directory
     * @param parent - initial directory
     * @param exts - filter on extension list
     * @return
     */
    public List<File> files(String parent, List<String> exts) {
        List<File> files = new ArrayList<>();
        Queue<File> data = new LinkedList<>();
        String path = System.getProperty("java.io.tmpdir");
        File file = new File(path + parent);
        data.offer(file);
        while (!data.isEmpty()) {
            File tempFile = data.poll();
            if (tempFile != null) {
                if (tempFile.isDirectory()) {
                    for (File dir : Objects.requireNonNull(tempFile.listFiles())) {
                        data.offer(dir);
                    }
                } else {
                    String e = getFileExtension(tempFile);
                    for (String ext : exts) {
                        if (e.equals(ext)) {
                            files.add(tempFile);
                        }
                    }
                }
            }
        }
        return files;
    }

    private String getFileExtension(File file) {
        if (file == null) {
            return "";
        }
        String name = file.getName();
        int i = name.lastIndexOf('.');
        String ext = i > 0 ? name.substring(i + 1) : "";
        return ext;
    }
}
