package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageDecorator;

import java.time.Instant;

/**
 * Message timestamp enrichment class.
 *
 * @author i.v.zotov
 * @version 2.0
 */

public class TimestampMessageDecorator implements MessageDecorator {

    public static final int PAGE_SIZE = 2;
    public static int messageCount = 0;
    SeverityDecoration decoration = new SeverityDecoration();

    /**
     * Method for outputting messages + system clock.
     */
    public String timestampDecorate(Message message) {
        if (message.getBody() != null && message.getSeverity() != null) {
            messageCount++;
            String messageTimestampDecorate = String.format("%d %s %s", messageCount, Instant.now(), message.getBody());
            String severityLevel = decoration.severityDecorate(message.getSeverity());
            String glued = String.format("%s %s", messageTimestampDecorate, severityLevel);
            if (messageCount % PAGE_SIZE == 0) {
                glued = glued + "\n ---";
            }
            return glued;
        }

        return null;
    }

}

