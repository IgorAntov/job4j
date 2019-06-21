package ru.job4j.chat.commands;

import org.apache.logging.log4j.Logger;
import ru.job4j.chat.Chat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Continue implements UserCommand {

    @Override
    public String keyWord() {
        return "continue";
    }

    @Override
    public void executeAction() {
        Chat.setOutStreamOn(true);
    }

    @Override
    public String logInfo(final Logger log) {
        log.info("Command: CONTINUE");
        log.info("Chat-bot OutStream is On");
        return "Chat-bot OutStream is On";
    }
}
