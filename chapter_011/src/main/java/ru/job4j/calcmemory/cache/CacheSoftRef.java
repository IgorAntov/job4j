package ru.job4j.calcmemory.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CacheSoftRef implements Cache {

    private Map<String, SoftReference<String>> cache = new HashMap<>();
    private final String path;

    CacheSoftRef() {
        this.path = "fileList";
    }

    @Override
    public String getFromCache(String key) {
        SoftReference<String> softReference;
        if (cache.containsKey(key) && cache.get(key).get() != null) {
            softReference = cache.get(key);
        } else {
            softReference = new SoftReference<>(readFile(this.path, key));
            cache.put(key, softReference);
        }
        return softReference.get();
    }

    /**
     * Check for file
     * @param path path to folder in the resource
     * @param file file name
     * @return result
     */
    private boolean isFile(String path, String file) {
        boolean result = true;
        URL u = CacheSoftRef.class.getClassLoader().getResource(path + File.separator + file);
        if (u == null) {
            result = false;
        }
        return result;
    }

    /**
     *
     * @param path path name
     * @param file file name
     * @return value aas file contents
     */
    private String readFile(String path, String file) {
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        if (isFile(path, file)) {
            Scanner scanner = new Scanner(CacheSoftRef.class.getClassLoader().getResourceAsStream(path + File.separator + file), "UTF-8");
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
                stringBuilder.append(ls);
            }
        }
        return stringBuilder.toString();
    }
}