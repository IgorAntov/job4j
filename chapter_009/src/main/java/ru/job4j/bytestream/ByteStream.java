package ru.job4j.bytestream;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ByteStream {

    boolean isNumber(InputStream in) {
        boolean result = false;
        int number;
        try (Scanner scanner = new Scanner(in)) {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                if (number % 2 == 0) {
                    result = true;
                }
            }
            return result;
        }
    }
}
