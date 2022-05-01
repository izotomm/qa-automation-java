import com.tcs.edu.domain.Message;
import com.tcs.edu.messageService.MessageService;
import com.tinkoff.edu.decorator.Severity;

import static com.tinkoff.edu.decorator.Doubling.DISTINCT;
import static com.tinkoff.edu.decorator.MessageOrder.DESC;

class Application {
    public static void main(String[] args) {
        Severity severity = Severity.randomSeverity();
        Message[] messages = new Message[]{
                new Message(severity, "Hello world!"),
                new Message(severity, "Hello world!1"),
                new Message(severity, "Hello world!1"),
                new Message(severity, "Hello world!2")
        };
        Message message = new Message(severity, "Hello world!");
        MessageService.log(message, messages);
        MessageService.log(DESC, message, messages);
        MessageService.log(DESC, DISTINCT, message, messages);
    }
}

