package ru.job4j.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.chat.commands.Continue;
import ru.job4j.chat.commands.Exit;
import ru.job4j.chat.commands.Stop;
import ru.job4j.chat.commands.UserCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Chat {

    private static final Logger LOG = LogManager.getLogger(Chat.class.getName());
    private List<UserCommand> action = new ArrayList<>();
    private List<String> lineSet = new ArrayList<>();
    private static boolean outStreamOn = true;
    private static boolean exit = true;
    Random random = new Random();

    public static void setOutStreamOn(boolean outStreamPermition) {
        Chat.outStreamOn = outStreamPermition;
    }

    public static boolean isExit() {
        return exit;
    }

    public static void setExit(boolean exit) {
        Chat.exit = exit;
    }

    /**
     * Метод считывает строки из текстового файла строки и заносит в массив строк,
     * создает массив команд
     */
    public void init() {
        System.out.println("ЧАТ-бот.");
        System.out.println("Команды: stop - остановить вывод сообщений; continue - продолжить вывод сообщений; exit - выйти из программы.");
        System.out.println("Введите фразу или команду:");
        Scanner scanner = new Scanner(Chat.class.getClassLoader().getResourceAsStream("chatdb.txt"), "UTF-8");
        while (scanner.hasNext()) {
            lineSet.add(scanner.nextLine());
        }
        action.add(new Stop());
        action.add(new Continue());
        action.add(new Exit());
    }

    /**
     * Метод читает случайную строку из массива строк
     * @return
     */
    private String getLine() {
        return lineSet.get(random.nextInt(lineSet.size()));
    }

    /**
     * Метод проверяет на ключевые слова-команды и выполняет соответствующие действия
     * @param line
     */
    public void readLine(String line) {
        LOG.info(String.format("Input Line: %s", line));
        for (UserCommand uc: action) {
            if (uc.keyWord().equals(line.toLowerCase())) {
                uc.executeAction();
                uc.logInfo(LOG);
            }
        }
        if (outStreamOn) {
            String newLine = getLine();
            LOG.info(String.format("Output Line: %s", newLine));
            System.out.println(String.format("Ответ: %s", newLine));
            System.out.println("Введите фразу или команду:");

        }
    }
}