package ru.job4j.chat.commands;

import org.apache.logging.log4j.Logger;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public interface UserCommand {

    public String keyWord();

    public void executeAction();

    public String logInfo(final Logger log);
}
