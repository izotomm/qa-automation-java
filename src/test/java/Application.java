import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.messageService.HashMapMessageRepository;
import com.tcs.edu.messageService.OrderedDistinctedMessageService;
import com.tinkoff.edu.decorator.Severity;

import java.util.Collection;

import static com.tinkoff.edu.decorator.Doubling.DISTINCT;
import static com.tinkoff.edu.decorator.MessageOrder.DESC;
import static com.tinkoff.edu.decorator.Severity.MAJOR;

class Application {
    public static void main(String[] args) {
        Severity severity = Severity.randomSeverity();
        Severity severity1 = Severity.randomSeverity();
        Message[] messages = new Message[]{new Message(severity, "Hello world!"), new Message(severity, "Hello world!1"), new Message(severity, "Hello world!1"), new Message(severity, "Hello world!2")};
        Message message = new Message(severity, "Hello world!");
        Message message1 = new Message(severity1, "Hello world!");
        Message message2 = new Message();
        HashMapMessageRepository repository = new HashMapMessageRepository();
        MessageService service = new OrderedDistinctedMessageService(new TimestampMessageDecorator(), repository);
        // new ConsolePrinter());
//        try {
//            service.log(message1);
//        } catch (LogException test) {
//            test.printStackTrace();
//        }
//        try {
//            service.log(message2);
//        } catch (LogException test) {
//            test.printStackTrace();
//        }

        service.log(message, messages);
        service.log(DESC, message1, messages);
        service.log(DESC, DISTINCT, message, messages);
//      System.out.println(repository.toString());
//      final UUID key = service.log(message);
//      System.out.println(service.findByPrimaryKey(key));

        final Collection<Message> allMessages = service.findAll();
        for (Message current : allMessages) {
            System.out.println(current);
        }

        System.out.println("______");
        final Collection<Message> filterMessages = service.findBySeverity(MAJOR);
        for (Message current : filterMessages) {
            System.out.println(current);
        }


    }

    //System.out.println(message.toString());
//        final Message message1 = new Message(severity, "Hello world!");
//        final Message message2 = new Message(severity, "Hello world!");
//        System.out.println(message1.equals(message2));
//        System.out.println(message1.hashCode() + " " + message2.hashCode());
//
//        final Message message3 = new Message(severity, "Hello world!");
//        final Message message4 = new Message(severity, "Hello world!123");
//        System.out.println(message3.equals(message4));
//        System.out.println(message3.hashCode() + " " + message4.hashCode());

}

