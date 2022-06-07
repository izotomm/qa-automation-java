import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.messageService.HashMapMessageRepository;
import com.tcs.edu.messageService.OrderedDistinctedMessageService;
import com.tinkoff.edu.decorator.Severity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.UUID;

import static com.tinkoff.edu.decorator.Doubling.DISTINCT;
import static com.tinkoff.edu.decorator.MessageOrder.DESC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class HashSetTest {

    private Message[] messages;
    private Message message;
    private MessageService service;
    private Collection<Message> allMessages;

    @BeforeEach
    public void setUP() {
        HashMapMessageRepository repository = new HashMapMessageRepository();
        message = new Message(Severity.randomSeverity(), "Hello world!");
        messages = new Message[]{new Message(Severity.randomSeverity(), "Hello world!"),
                new Message(Severity.randomSeverity(), "Hello world!1"),
                new Message(Severity.randomSeverity(), "Hello world!1")};
        service = new OrderedDistinctedMessageService(new TimestampMessageDecorator(), repository);
        allMessages = service.findAll();
    }

    @Nested
    @DisplayName("checkHashElement")
    class checkHashElement {
        @Test
        public void shouldSaveElementWhenItDoesntExists() {
            service.log(message, messages);

            assertThat(allMessages).contains(message).contains(messages);
            assertThat(allMessages.size()).isEqualTo(4).as("количеств сообщений совпадает");

        }

        @Test
        public void shouldNotSaveElementWhenItDoesntExists() {
            service.log(DESC, DISTINCT, message, messages);
            assertThat(allMessages.size()).isEqualTo(2).as("количеств сообщений совпадает");
            ;
        }

        @Test
        public void shouldGetElementByPrimaryKey() {
            final UUID key = service.log(message);
            assertThat(service.findByPrimaryKey(key)).isNotNull();
        }
    }

    @Nested
    @DisplayName("checkError")
    class checkError {
        @Test
        public void shouldGetErrorWhenMessageNull() {
            assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> {
                service.log(null);
            });
        }
    }
}
