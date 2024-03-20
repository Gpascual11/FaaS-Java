package Test;

import Components.Controller;
import Decorator.MemoizationDecorator;
import Decorator.TimerDecorator;

import java.util.function.Function;

public class TestDecoratorComplet {
    public static void main(String[] args) {

        // Create a Controller
        Controller<Integer, Integer> controller = new Controller<>(5);

        // Register a factorial function
        Function<Integer, Integer> factorialMemoization = new MemoizationDecorator<>(controller.getInvoker(0).getCache(), TestDecoratorComplet::calculateFactorial);
        Function<Integer, Integer> factorialTimer = new TimerDecorator<>(TestDecoratorComplet::calculateFactorial);
        // Register the factorial function with a specific amount of RAM
        controller.registerAction("factorialT", factorialTimer, 150);
        controller.registerAction("factorialM", factorialMemoization, 150);

        // Execute the function
        System.out.println(controller.invoke("factorialM", 5));
        System.out.println(controller.invoke("factorialM", 5));
        System.out.println(controller.invoke("factorialT", 9));

    }

    private static int calculateFactorial(int n) {
        int j = 0;
        for (int i = 0; i < 1000000000; i++) {
            j++;
            // elapse time
            j = (j * 2);
        }
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }

    }
}
