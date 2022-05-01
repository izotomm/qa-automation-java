package com.tcs.edu.decorator;


import com.tcs.edu.domain.Message;

import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * Message glued class.
 *
 * @author i.v.zotov
 * @version 1.0
 */

public class GluedMessage {
    public static final int PAGE_SIZE = 2;
    public static int messageCount = 0;

    /**
     * Method for glue Message.
     * Side effect: print message in console.
     */
    public static void printGluedMessage(Message message) {
        if (message.getBody() != null && message.getSeverity() != null) {
            messageCount++;
            String messageTimestampDecorate = TimestampMessageDecorator.timestampDecorate(message.getBody());
            String severityLevel = SeverityDecoration.severityDecorate(message.getSeverity());
            String glued = String.format("%s %s", messageTimestampDecorate, severityLevel);
            if (messageCount % PAGE_SIZE == 0) {
                glued = glued + "\n ---";
            }
            print(glued);
        }
    }
}