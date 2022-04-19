package com.tcs.edu.messageService;

import com.tcs.edu.decorator.GluedMessage;
import com.tinkoff.edu.decorator.MessageOrder;
import com.tinkoff.edu.decorator.Severity;

import static com.tinkoff.edu.decorator.MessageOrder.ASC;
import static com.tinkoff.edu.decorator.MessageOrder.DESC;

/**
 * API
 */
public class MessageService {


    /**
     * Вариант печати без MessageOrder и Doubling (базовый)
     */
    public static void process(Severity level, String message, String... messages) {
        GluedMessage.printGluedMessage(message, level);
        for (String currentMessages : messages) {
            GluedMessage.printGluedMessage(currentMessages, level);
        }
    }

    /**
     * Вариант печати без Doubling но с MessageOrder
     */
    public static void process(Severity level, MessageOrder order, String message, String... messages) {

        if (order == DESC) {
            //тут вывожу сообщения которые в варрарге задом наперед
            for (int counterMessages = messages.length - 1; counterMessages >= 0; counterMessages--) {
                GluedMessage.printGluedMessage(messages[counterMessages], level);
            }
            //тут вывожу сообщение которое не в варрарге
            GluedMessage.printGluedMessage(message, level);
        } else if (order == ASC) {
            //тут использую process без order чтовы вывести сообщения в заданом порядке
            MessageService.process(level, message, messages);

        }
    }


}


