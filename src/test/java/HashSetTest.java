import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.messageService.HashMapMessageRepository;
import com.tcs.edu.messageService.OrderedDistinctedMessageService;
import com.tinkoff.edu.decorator.Severity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static com.tinkoff.edu.decorator.Doubling.DISTINCT;
import static com.tinkoff.edu.decorator.MessageOrder.DESC;

public class HashSetTest {

    private Message[] messages;
    private Message message;
    private MessageService service;
    private Collection<Message> allMessages;

    @BeforeEach
    public void setUP() {
        Severity severity = Severity.randomSeverity();
        HashMapMessageRepository repository = new HashMapMessageRepository();
        message = new Message(severity, "Hello world!");
        messages = new Message[]{new Message(severity, "Hello world!"), new Message(severity, "Hello world!1"), new Message(severity, "Hello world!1")};
        service = new OrderedDistinctedMessageService(new TimestampMessageDecorator(), repository);
        allMessages = service.findAll();
    }

    @Test
    public void shouldSaveElementWhenItDoesntExists() {
        service.log(message, messages);

        Assertions.assertTrue(allMessages.contains(message));
        Assertions.assertTrue(containsMessages());
        Assertions.assertEquals(4, allMessages.size());
    }

    @Test
    public void shouldNotSaveElementWhenItDoesntExists() {
        service.log(DESC, DISTINCT, message, messages);
        Assertions.assertEquals(2, allMessages.size());
    }

    @Test
    public void shouldGetElementByPrimaryKey() {
        final UUID key = service.log(message);
        Assertions.assertTrue((service.findByPrimaryKey(key) != null));
    }

    @Test
    public void sh1oudGetErrorWhenMessageNull() {

        Assertions.assertThrows(NullPointerException.class, () -> service.log(null));

    }


    private boolean containsMessages() {

        for (Message current : messages) {
            if (!allMessages.contains(current)) return false;
        }
        return true;
    }

}
