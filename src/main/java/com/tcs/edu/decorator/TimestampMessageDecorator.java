package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * Message enrichment class.
 *
 * @author i.v.zotov
 * @version 1.0
 */

public class TimestampMessageDecorator {
    /**
     * Method for outputting messages + system clock to console.
     * Side effect: print message + system clock value in console.
     *
     * @param message string value for outputting to console.
     */
    public static int messageCount = 1;

    public static String decorate(String message) {
        final var decoratedMessage = String.format("%d %s %s", messageCount, Instant.now(), message);
        return decoratedMessage;
    }

}

