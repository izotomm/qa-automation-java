package com.tcs.edu.messageService;

import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HashMapMessageRepository implements MessageRepository {
    private Map<UUID, Message> messages = new HashMap<>();

    @Override
    public String toString() {
        return "HashMapMessageRepository{" +
                "messages=" + messages +
                '}';
    }

    @Override
    public UUID create(Message message) {
        final UUID key = UUID.randomUUID();
        messages.put(key, message);
        return key;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }
}