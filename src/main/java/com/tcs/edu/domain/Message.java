package com.tcs.edu.domain;

import com.tinkoff.edu.decorator.Severity;

public class Message {

    private Severity severity;
    private String body;

    public Message(Severity severity, String body) {
        this.severity = severity;
        this.body = body;
    }


    public String getBody() {
        return body;
    }

    public Severity getSeverity() {
        return severity;
    }
}

