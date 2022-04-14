package com.tcs.edu.decorator;

import java.time.Instant;

import static com.tcs.edu.decorator.gluedMessage.messageCount;

/**
 * Message timestamp enrichment class.
 *
 * @author i.v.zotov
 * @version 2.0
 */

public class TimestampMessageDecorator {
    /**
     * Method for outputting messages + system clock to console.
     */

    public static String timestampDecorate(String message) {
        return String.format("%d %s %s", messageCount, Instant.now(), message);
    }
}


