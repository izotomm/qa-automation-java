package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * message enrichment class
 *
 * @author i.v.zotov
 * @version 1.0
 */

public class TimestampMessageDecorator {
    /**
     * method for outputting messages + system clock to console
     * side effect: print message + system clock value in console
     * @param message string value for outputting to console
     */
    public static void decorate(String message) {
        System.out.println(Instant.now() + " " +  message);
    }
}
