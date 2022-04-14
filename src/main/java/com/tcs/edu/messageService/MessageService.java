package com.tcs.edu.messageService;

import com.tcs.edu.decorator.gluedMessage;
import com.tinkoff.edu.decorator.Severity;

/**
 * API
 */
public class MessageService {

    public static void process(Severity level, String message, String... messages) {
        gluedMessage.glueMessage(message, level);
        for (String currentMessages : messages) {
            gluedMessage.glueMessage(currentMessages, level);
        }
    }
}

