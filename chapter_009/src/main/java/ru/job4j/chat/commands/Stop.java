package ru.job4j.chat.commands;

import org.apache.logging.log4j.Logger;
import ru.job4j.chat.Chat;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Stop implements UserCommand {

    @Override
    public String keyWord() {
        return "stop";
    }

    @Override
    public void executeAction() {
        Chat.setOutStreamOn(false);
    }

    @Override
    public String logInfo(final Logger log) {
        log.info("Command: STOP");
        log.info("Chat-bot outStream is Off");
        return "Chat-bot outStream is Off";
    }
}
