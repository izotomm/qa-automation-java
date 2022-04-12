package com.tcs.edu.messageService;

import com.tcs.edu.decorator.SeverityDecoration;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tinkoff.edu.decorator.Severity;

import static com.tcs.edu.printer.ConsolePrinter.print;

/**
 * API for glued message,severity,timestamp
 */
public class MessageService {

    public static int messageCount = 0;
    public static final int PAGE_SIZE = 2;

    public static void process(Severity level, String message) {
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

