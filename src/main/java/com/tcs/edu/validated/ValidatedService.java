package com.tcs.edu.validated;

import com.tcs.edu.domain.Message;

public abstract class ValidatedService {

    public boolean isArgsValid(Message message) {
        if (message.getBody() != null && message.getSeverity() != null) return true;
        return false;
    }
}
