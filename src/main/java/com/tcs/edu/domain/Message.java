package com.tcs.edu.domain;

import com.tinkoff.edu.decorator.Severity;

import java.util.Objects;
import java.util.UUID;

public class Message {

    private Severity severity;
    private String body;
    private UUID id;

    public Message(Severity severity, String body) {
        this.severity = severity;
        this.body = body;
    }

    public Message() {
        body = "";

    }


    public String getBody() {
        return body;
    }

    public Severity getSeverity() {
        return severity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "severity=" + severity +
                ", body='" + body + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return severity == message.severity && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severity, body);
    }
}

