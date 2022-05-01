import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.messageService.OrderedDistinctedMessageService;
import com.tcs.edu.printer.ConsolePrinter;
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
        MessageService service = new OrderedDistinctedMessageService(
                new TimestampMessageDecorator(),
                new ConsolePrinter());
        service.log(message, messages);
        service.log(DESC, message, messages);
        service.log(DESC, DISTINCT, message, messages);
    }
}
