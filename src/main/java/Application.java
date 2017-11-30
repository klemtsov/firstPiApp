import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStoppedEvent;

@SpringBootApplication(scanBasePackages = {"controllers"})
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        context.addApplicationListener(
                new ApplicationListener<ContextStoppedEvent>() {
                    @Override
                    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
                        System.out.println(contextStoppedEvent);
                    }
                }
        );

    }

}
