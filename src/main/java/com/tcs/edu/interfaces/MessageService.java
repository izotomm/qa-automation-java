package com.tcs.edu.interfaces;

import com.tcs.edu.domain.Message;
import com.tinkoff.edu.decorator.Doubling;
import com.tinkoff.edu.decorator.MessageOrder;

import java.util.UUID;

public interface MessageService {

    UUID log(Message message);

    void log(Message message, Message... messages);

    void log(MessageOrder order, Message message, Message... messages);

    void log(MessageOrder order, Doubling doubling, Message message, Message... messages);

    Message findByPrimaryKey(UUID key);
}
