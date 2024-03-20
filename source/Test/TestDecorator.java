package Test;

import Components.Controller;
import Decorator.TimerDecorator;

import java.util.function.Function;

public class TestDecorator {
    public static void main(String[] args) {

        Controller<Integer, String> controller = new Controller<>(3);

        // Register a sleep function
        Function<Integer, String> printAnything = print -> {
                for (int i = 0; i < print; i++) {
                    System.out.println("Hello, World!");
                    try {
                        Thread.sleep( 1000L); // Convert seconds to milliseconds
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            return print + " Done!";
        };

        Function <Integer, String> print = new TimerDecorator<>(printAnything);

        controller.registerAction("printAny", print);

        // Execute the function
        controller.invoke("printAny", 3);
        controller.invoke("printAny", 5);

    }
}
