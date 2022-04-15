package com.tcs.edu.messageService;

import com.tcs.edu.decorator.GluedMessage;
import com.tinkoff.edu.decorator.Severity;

/**
 * API
 */
public class MessageService {

    public static void process(Severity level, String message, String... messages) {
        GluedMessage.printGluedMessage(message, level);
        for (String currentMessages : messages) {
            GluedMessage.printGluedMessage(currentMessages, level);
        }
    }
}

