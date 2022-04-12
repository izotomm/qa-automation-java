package com.tcs.edu.decorator;

import java.time.Instant;

import static com.tcs.edu.messageService.MessageService.messageCount;

/**
 * Message timestamp enrichment class.
 *
 * @author i.v.zotov
 * @version 2.0
 */

public class TimestampMessageDecorator {
    /**
     * Method for outputting messages + system clock to console.
     * Side effect: print message + system clock value in console.
     */

    public static String timestampDecorate(String message) {
        return String.format("%d %s %s", messageCount, Instant.now(), message);
    }
}


