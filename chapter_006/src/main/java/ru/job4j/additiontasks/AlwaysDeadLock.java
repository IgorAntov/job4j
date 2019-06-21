package ru.job4j.additiontasks;

import java.util.concurrent.CountDownLatch;

/**
 *Нужно написать программу, которая всегда падает в дедлок.
 * почитай. что такое CountDownLatch
 * в программе нельзя использовать метод sleep.
 *
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class AlwaysDeadLock {
    CountDownLatch countDownLatch = new CountDownLatch(2);
    Object item1 = new Object();
    Object item2 = new Object();

    public void startTask() throws InterruptedException {
        Thread t1 = new Thread(new CdL(countDownLatch, item1, item2));
        Thread t2 = new Thread(new CdL(countDownLatch, item2, item1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    public class CdL implements Runnable {
        private CountDownLatch cdl;
        private Object o1, o2;

        public CdL(CountDownLatch cdl, Object o1, Object o2) {
            this.cdl = cdl;
            this.o1 = o1;
            this.o2 = o2;
        }

        @Override
        public void run() {
            System.out.println(String.format("Запуск потока %s", Thread.currentThread().getName()));
            synchronized (o1) {
                System.out.println(String.format("Блокируем первый объект в потоке %s %s", Thread.currentThread().getName(), o1.getClass().getName()));
                cdl.countDown();
                try {
                    cdl.await();
                    System.out.println(String.format("Начинаем блокировать второй объект в потоке %s", Thread.currentThread().getName()));
                    synchronized (o2) {
                        System.out.println(String.format("Блокируем второй объект в потоке %s", Thread.currentThread().getName()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("Поток %s завершил работу", Thread.currentThread().getName()));
        }
    }
}

