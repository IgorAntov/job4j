package ru.job4j.chat.input;

import java.util.Scanner;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class ConsoleInput  implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask() {
        return scanner.nextLine();
    }

  /*

  public String ask(String question) {
      System.out.print(question);
      return scanner.next();
  }

    public int ask(String question, List<Integer> range) {

        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value: range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (!exist) {
            throw new MenuOutException("Выход за пределы диапазона значений.");
        }
        return key;
    }

   */

}
