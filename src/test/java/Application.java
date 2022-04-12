import com.tcs.edu.messageService.MessageService;
import com.tinkoff.edu.decorator.Severity;

class Application {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
        MessageService.process(Severity.randomSeverity(),"Hello world!");
      }
    }
}

