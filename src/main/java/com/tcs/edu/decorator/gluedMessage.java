package com.tcs.edu.decorator;


import com.tinkoff.edu.decorator.Severity;

import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * Message glued class.
 *
 * @author i.v.zotov
 * @version 1.0
 */

public class gluedMessage {
    public static final int PAGE_SIZE = 2;
    public static int messageCount = 0;

    /**
     * Method for glue Message.
     * Side effect: print message in console.
     */
    public static void glueMessage(String message, Severity level) {
        messageCount++;
        String messageTimestampDecorate = TimestampMessageDecorator.timestampDecorate(message);
        String severityLevel = SeverityDecoration.severityDecorate(level);
        String glued = String.format("%s %s", messageTimestampDecorate, severityLevel);
        if (messageCount % PAGE_SIZE == 0) {
            glued = glued + "\n ---";
        }
        print(glued);
    }
}