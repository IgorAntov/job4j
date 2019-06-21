package ru.job4j.dropabuses;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class DropAbusesService {

    void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        String stringLine;
        try (Scanner scanner = new Scanner(in)) {
            do  {
                stringLine = scanner.nextLine();
                for (String keyword : abuse) {
                    if (stringLine.contains(keyword)) {
                        stringLine = stringLine.replaceAll(keyword,  "");
                    }
                } try {
                    out.write(stringLine.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (scanner.hasNextLine());
        }
    }
}
