package com.tcs.edu.interfaces;

import com.tcs.edu.domain.Message;
import com.tinkoff.edu.decorator.Doubling;
import com.tinkoff.edu.decorator.MessageOrder;

public interface MessageService {
    void log(Message message, Message... messages);

    void log(MessageOrder order, Message message, Message... messages);

    void log(MessageOrder order, Doubling doubling, Message message, Message... messages);
}
