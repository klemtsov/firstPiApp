package app;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

@SpringBootApplication(scanBasePackages = {"controllers"})
public class Application {

    public final static GpioController GPIO_CONTROLLER = GpioFactory.getInstance();

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationListener<ContextStoppedEvent>(){

            @Override
            public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
                GPIO_CONTROLLER.shutdown();
                System.out.println(contextStoppedEvent);
            }
        });
        springApplication.run(args);
    }

}
