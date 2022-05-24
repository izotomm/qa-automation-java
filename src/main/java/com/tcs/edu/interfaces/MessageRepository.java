package com.tcs.edu.interfaces;

import com.tcs.edu.domain.Message;

import java.util.UUID;

public interface MessageRepository {

    UUID create(Message message);

}
