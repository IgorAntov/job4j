package ru.job4j.additiontasks;

import java.util.concurrent.Semaphore;

/**
 * 1) Реализуйте объект, который хранит в себе строку или ее представление. Объекту необходимо:
 * - содержать метод, который принимает на вход значение типа int, конвертирует его в строковое представление
 * (например, 4 -> "4"), а затем добавляет к концу строки.
 * - по требованию возвращать эту строку.
 * 2) Реализуйте 2 потока, которые в цикле добавляют всегда одно и то же число (1-й поток число 1, второй поток число 2)
 * в строку из пункта 1.
 * Работа потоков должна быть организована таким образом, чтобы числа добавлялись в строку в следующем порядке:
 * сначала 10 чисел из первого потока, затем 10 чисел из второго, затем снова 10 чисел из первого и так далее.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Switcher {
    private final Semaphore semaphore = new Semaphore(1);
    private String str = "";

    /**
     * Method adds value to the end of the string - str
     */
    public synchronized void add(int i) {
        this.str += String.valueOf(i);
    }

    /**
     * Method return str
     */
    public String getStr() {
        return this.str;
    }

    /**
     * Method creates two threads and concat value to string consistently
     * @throws InterruptedException
     */
    public void startTask() throws InterruptedException {
        Thread t1 = new Thread(new AddStr(semaphore, 1));
        Thread t2 = new Thread(new AddStr(semaphore, 2));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    /**
     * This class defines concatenation logic
     */
    public class AddStr implements Runnable {
        private Semaphore sem;
        private int dig;

        public AddStr(Semaphore sem, int dig) {
            this.sem = sem;
            this.dig = dig;
        }

        @Override
        public void run() {
            System.out.println(String.format("Запуск потока %s", Thread.currentThread().getName()));
            try {
                System.out.println(String.format("Поток %s ожидает разрешения", Thread.currentThread().getName()));
                sem.acquire();
                System.out.println(String.format("Поток %s получил разрешение", Thread.currentThread().getName()));
                for (int i = 1; i <= 20; i++) {
                    add(dig);
                    if ((i % 10) == 0) {
                        sem.release();
                        System.out.println(String.format("Поток %s освобождает разрешение", Thread.currentThread().getName()));
                        System.out.println(String.format("Поток %s ожидает разрешения", Thread.currentThread().getName()));
                        sem.acquire();
                        System.out.println(String.format("Поток %s получил разрешение", Thread.currentThread().getName()));
                    }
                    Thread.currentThread().sleep(100);
                    System.out.println(getStr());
                }
                System.out.println(String.format("Поток %s завершил работу", Thread.currentThread().getName()));
                sem.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}




