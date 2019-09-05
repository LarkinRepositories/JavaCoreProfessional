package Lessons.Lesson_6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Lesson_6CodeSamples {
    private static final Logger LOGGER = LogManager.getLogger(Lesson_6CodeSamples.class.getName());

    public static void main(String[] args) {
        LOGGER.debug("Debug");
        LOGGER.info("Info");
        LOGGER.warn("Warn");
        LOGGER.error("Error");
        LOGGER.fatal("Fatal");
        LOGGER.info("String: {}.", "Hello World");
        //никогда не выведется, т.к в log4j2.xml установлено значение info для Root level
        LOGGER.trace(Lesson_6CodeSamples::new);
    }
}
