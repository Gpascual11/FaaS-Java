package Test;

import Components.Controller;
import Decorator.MemoizationDecorator;

import java.util.function.Function;

public class TestMemoizationDecorator {
    public static void main(String[] args) {
        // Create a Controller with total memory
        Controller<Integer, String> controller = new Controller<>(1);

        // Register a sleep function
        Function<Integer, String> printAnything = print -> {
            for (int i = 0; i < print; i++) {
                System.out.println("Hello, World!");
                try {
                    Thread.sleep(1000L); // Convert seconds to milliseconds
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return print + " Done!";
        };

        // Register the sleep function with memoization and a specific amount of RAM
        Function <Integer, String> print = new MemoizationDecorator<>(controller.getInvoker(0).getCache(), printAnything);
        controller.registerAction("printAny", print, 150);

        // Execute the function
        controller.invoke("printAny", 3);
        controller.invoke("printAny", 5);
        controller.invoke("printAny", 5);
        controller.invoke("printAny", 3);
    }
}
