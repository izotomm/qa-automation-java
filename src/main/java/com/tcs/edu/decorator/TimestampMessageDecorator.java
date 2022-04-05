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
    public static final int PAGE_SIZE = 2;


    public static String decorate(String message) {
        if (messageCount % PAGE_SIZE == 0) {
            return String.format("%d %s %s \n ---", messageCount, Instant.now(), message);
        } else {
            return String.format("%d %s %s", messageCount, Instant.now(), message);
        }
    }
}


