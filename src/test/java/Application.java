import com.tcs.edu.messageService.MessageService;
import com.tinkoff.edu.decorator.Severity;

import static com.tinkoff.edu.decorator.Doubling.DISTINCT;
import static com.tinkoff.edu.decorator.MessageOrder.ASC;

class Application {
    public static void main(String[] args) {
        // for (int i = 1; i <= 5; i++) {
        MessageService.process(
                Severity.randomSeverity(), ASC, DISTINCT,
                "Hello world!",
                "Hello world 2!", null, "Hello world 3!", "Hello world 3!", "Hello world 5!");
        // }
    }
}

