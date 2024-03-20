package TestJUnit;

import Components.Controller;
import Decorator.MemoizationDecorator;
import Decorator.TimerDecorator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

class TestDecorator {

    private Controller<Integer, String> controller;

    @BeforeEach
    void setUp() {
        // Initialize the controller before each test
        controller = new Controller<>(1);
    }

    @AfterEach
    void tearDown() {
        // Shutdown the controller after each test
        controller.shutdown();
    }

    @Test
    void TestMemoizationDecorator() {
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

    @Test
    @DisplayName("Test TimerDecorator with an asynchronous function (sleep)")
    void testTimerDecorator() {
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