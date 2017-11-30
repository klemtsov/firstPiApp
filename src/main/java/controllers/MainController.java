package controllers;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final GpioController controller;
    private final GpioPinDigitalOutput ledPin;

    public MainController(){
        controller = GpioFactory.getInstance();
        ledPin = controller.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.LOW);
    }

    @RequestMapping("/")
    public String index(){
        //ledPin.toggle();
        //System.out.println("включен");
        return "Hallo world!";
    }

    @RequestMapping("/ledon")
    public String ledon(){
        ledPin.high();
        System.out.println("включен");
        return "включен";
    }

    @RequestMapping("/ledoff")
    public String ledoff(){
        ledPin.low();
        System.out.println("включен");
        return "включен";
    }

}
