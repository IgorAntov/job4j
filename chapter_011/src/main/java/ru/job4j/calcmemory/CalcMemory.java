package ru.job4j.calcmemory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class CalcMemory {
    private static Runtime runtime = Runtime.getRuntime();
    private static List objects = new ArrayList();
    private static boolean cont = true;
    private static String input;
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static long getUsedMemory() {
        return runtime.totalMemory() - runtime.freeMemory();
    }

    /**
     * Creates object User and adds to List
     * @param size in bytes.
     */
    static void createObjects(int size) {
        objects.add(new User("User", size));
    }

    /**
     * Remove object from the List
     */
    static void removeObjects() {
        int start = objects.size() - 1;
        int end = start - 2;
        for (int i = start; ((i >= 0) && (i > end)); i--) {
            objects.remove(i);
        }
    }

    /**
     * Method creates object to the List and instantly remove from the List
     */
    static void callFinalize() {
        System.out.println(" 6 objects were created.");
        objects.clear();
        for (int i = 0; i < 6; i++) {
            createObjects(500000);
            removeObjects();
        }
        info();
    }

    /**
     * VM Memory usage
     */
    public static void info() {
        int kb = 1024;
        System.out.println("   Used memory:" + getUsedMemory() + " byte (" + getUsedMemory() / kb + " kb)");
        System.out.println("   Free memory:" + runtime.freeMemory() + " byte (" + runtime.freeMemory() / kb + " kb)");
        System.out.println("   Total memory" + runtime.totalMemory() + " byte (" + runtime.totalMemory() / kb + " kb)");
        System.out.println("   Max memory:" + runtime.maxMemory() + " byte (" + runtime.maxMemory() / kb + " kb)");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Memory Tool!");
        info();
        while (cont) {
            System.out.println(
                    "\nI have " + objects.size() + " objects in use, about "
                            + (objects.size() * 500) + " kB."
                            + "\nWhat would you like me to do?\n"
                            + "1. Create one objects \n"
                            + "2. Remove one objects\n"
                            + "3. Call Finalize\n"
                            + "0. Quit");
            try {
                input = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if ((input != null) && (input.length() >= 1)) {
                if (input.startsWith("0")) {
                    cont = false;
                }
                if (input.startsWith("1")) {
                    createObjects(500000);
                }
                if (input.startsWith("2")) {
                    removeObjects();
                }
                if (input.startsWith("3")) {
                    callFinalize();
                }
                info();
            }
        }
    }
}