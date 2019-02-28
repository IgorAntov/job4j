package ru.job4j.chat.commands;

import org.apache.logging.log4j.Logger;
import ru.job4j.chat.Chat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Exit implements UserCommand {

    @Override
    public String keyWord() {
        return "exit";
    }

    @Override
    public void executeAction() {
        Chat.setExit(false);
        Chat.setOutStreamOn(false);
    }

    @Override
    public String logInfo(final Logger log) {
        log.info("Command: EXIT");
        log.info("Chat-bot is terminated");
        return "Chat-bot is terminated";
    }
}
