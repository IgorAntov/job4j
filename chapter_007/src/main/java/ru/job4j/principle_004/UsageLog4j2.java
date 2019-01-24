package ru.job4j.principle_004;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class UsageLog4j2 {

    private static final Logger LOG = LogManager.getLogger(UsageLog4j2.class.getName());

    public static void main(String[] args) {
        DOMConfigurator.configure("chapter_007/src/main/resources/log4j2.xml");
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
