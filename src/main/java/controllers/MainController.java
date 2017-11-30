package controllers;

import com.pi4j.io.gpio.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private static final PinState[] off = {
            PinState.LOW,
            PinState.LOW,
            PinState.LOW,
            PinState.LOW,
            PinState.LOW,
            PinState.LOW,
            PinState.LOW};
    private static final PinState[] one = {
            PinState.LOW,
            PinState.LOW,
            PinState.HIGH,
            PinState.HIGH,
            PinState.LOW,
            PinState.LOW,
            PinState.LOW};

    private static final PinState[] two = {
            PinState.HIGH,
            PinState.HIGH,
            PinState.LOW,
            PinState.HIGH,
            PinState.HIGH,
            PinState.HIGH,
            PinState.LOW};

    private final GpioController controller;

    private final GpioPinDigitalOutput[] pinDigitalOutputs;


    public MainController() {
        controller = GpioFactory.getInstance();
        this.pinDigitalOutputs = new GpioPinDigitalOutput[]{
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_01, "11", PinState.LOW),
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_02, "7", PinState.LOW),
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_03, "4", PinState.LOW),
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_04, "2", PinState.LOW),
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_05, "1", PinState.LOW),
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_06, "5", PinState.LOW),
                controller.provisionDigitalOutputPin(RaspiPin.GPIO_07, "10", PinState.LOW),
        };

    }

    @RequestMapping("/")
    public String index() {
        //ledPin.toggle();
        //System.out.println("включен");
        return "Hallo world!";
    }


    private void digitsChange(final PinState[] pinStates) {
        int index = 0;
        for (GpioPinDigitalOutput pin :
                pinDigitalOutputs) {
            pin.setState(pinStates[index]);
            index++;
        }
    }

    @GetMapping("/dig/{digit}")
    public String switchDigit(@PathVariable int digit) {
        switch (digit) {
            case -1:digitsChange(off);
            case 1:digitsChange(one);
                break;
            case 2:digitsChange(two);
                break;
            default:
                break;
        }
        String mess = String.format("цифра %d", digit);
        System.out.println(mess);
        return mess;
    }

}
