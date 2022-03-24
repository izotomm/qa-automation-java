package com.tcs.edu.printer;

/**
 * Message handling class.
 *
 * @author i.v.zotov
 * @version 1.0
 */

public class ConsolePrinter {
    /**
     * Method for outputting messages to console.
     * Side effect: print message value in console
     *
     * @param message string value for outputting to console.
     */
    public static void print(String message) {
        System.out.println(message);
    }
}
