package com.tcs.edu.messageService;

import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageDecorator;
import com.tcs.edu.interfaces.MessageRepository;
import com.tcs.edu.interfaces.MessageService;
import com.tinkoff.edu.decorator.Doubling;
import com.tinkoff.edu.decorator.MessageOrder;
import com.tinkoff.edu.decorator.Severity;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static com.tinkoff.edu.decorator.Doubling.DISTINCT;
import static com.tinkoff.edu.decorator.Doubling.DOUBLES;
import static com.tinkoff.edu.decorator.MessageOrder.ASC;
import static com.tinkoff.edu.decorator.MessageOrder.DESC;

/**
 * API
 */
public class OrderedDistinctedMessageService implements MessageService {
    MessageDecorator decorator;
    MessageRepository hashRepo;

    public OrderedDistinctedMessageService(MessageDecorator messageDecorator,
                                           MessageRepository haspRepo) {
        this.hashRepo = haspRepo;
        this.decorator = messageDecorator;
    }

    public Message findByPrimaryKey(UUID key) {
        return this.hashRepo.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> findAll() {
        return hashRepo.findAll();
    }

    @Override
    public Collection<Message> findBySeverity(Severity by) {
        return hashRepo.findBySeverity(by);
    }


    /**
     * Вариант печати без MessageOrder и Doubling (базовый)
     *
     * @return
     */

    public UUID log(Message message) {
        UUID key = hashRepo.create(message);
        return key;
    }

    public void log(Message message, Message... messages) {
        // printer.print(decorator.timestampDecorate(message));
        hashRepo.create(message);
        for (Message currentMessage : messages) {
            // printer.print(decorator.timestampDecorate(currentMessage));
            hashRepo.create(currentMessage);
        }
    }


    /**
     * Вариант печати без Doubling но с MessageOrder
     */
    public void log(MessageOrder order, Message message, Message... messages) {

        if (order == DESC) {
            //тут вывожу сообщения которые в варрарге задом наперед
            for (int counterMessages = messages.length - 1; counterMessages >= 0; counterMessages--) {
                //printer.print(decorator.timestampDecorate(messages[counterMessages]));
                hashRepo.create(messages[counterMessages]);
                // messages[counterMessages].setId(haspRepo.create(messages[counterMessages]));
            }
            //тут вывожу сообщение которое не в варрарге
            // printer.print(decorator.timestampDecorate(message));
            hashRepo.create(message);
        } else if (order == ASC) {
            //тут использую process без order чтовы вывести сообщения в заданом порядке
            log(message, messages);

        }
    }

    /**
     * Вариант печати с Doubling и MessageOrder
     */
    public void log(MessageOrder order, Doubling doubling, Message message, Message... messages) {

        //массив данных для сообщений
        String[] printMessages = new String[messages.length + 1];

        if (doubling == DISTINCT) {

            if (order == DESC) {
                //вывод сообщений в обратной последовательности + убрать повторы
                for (int counterMessages = messages.length - 1; counterMessages >= 0; counterMessages--) {
                    //проверка если ли в массиве уже сообщение
                    if (!OrderedDistinctedMessageService.isMessagePrinted(messages[counterMessages], printMessages)) {
                        //printer.print(decorator.timestampDecorate(messages[counterMessages]));
                        hashRepo.create(messages[counterMessages]);
                        //пихаю сообщение в массив
                        printMessages[counterMessages] = messages[counterMessages].getBody();
                    }

                }
                //тут вывожу сообщение которое не в варрарге, сначала проверка на дубликат, потом печать
                if (!OrderedDistinctedMessageService.isMessagePrinted(message, printMessages)) {
                    //printer.print(decorator.timestampDecorate(message));
                    hashRepo.create(message);
                }
            }
            if (order == ASC) {
                //вывод сообщения не в варарге + засовываю это сообещение в массив
                //printer.print(decorator.timestampDecorate(message));
                hashRepo.create(message);
                printMessages[messages.length] = message.getBody();

                //тут перебираю сообщения в варрге, печатаю и засовываю их в массив
                for (int counterMessages = 0; counterMessages <= messages.length - 1; counterMessages++) {
                    if (!OrderedDistinctedMessageService.isMessagePrinted(messages[counterMessages], printMessages)) {
                        //printer.print(decorator.timestampDecorate(messages[counterMessages]));
                        hashRepo.create(messages[counterMessages]);
                        printMessages[counterMessages] = messages[counterMessages].getBody();
                    }
                }
            }
            //вариант когда дубликаты сообщений уберить не нужно но был передан параметр doubling
        } else if (doubling == DOUBLES) {
            log(order, message, messages);
        }
    }

    /**
     * метод проверки сообщения в массиве
     */
    private static boolean isMessagePrinted(Message message, String... printed) {
        for (String printedMessage : printed)
            if (Objects.equals(message.getBody(), printedMessage)) {
                return true;
            }
        return false;

    }

}


