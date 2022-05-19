package com.tcs.edu.validated;

import com.tcs.edu.domain.Message;

public abstract class ValidatedService {

    public boolean isArgsValid(Message message) {
        if (message.getBody() != null && message.getSeverity() != null) return true;
        if (message.getSeverity() == null)
            throw new IllegalArgumentException("severity is null");
        if (message.getBody() == null || message.getBody().isEmpty())
            throw new IllegalArgumentException("message is empty");
        return false;
    }
}



