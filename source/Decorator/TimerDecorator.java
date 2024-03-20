package Decorator;

import java.util.function.Function;

/**
 * TimerDecorator class
 * Decorator class that adds a timer to the action
 *
 * @param <I> input
 * @param <O> output
 */
public class TimerDecorator<I, O> implements Function<I, O> {

    //Duration of the action
    private long duration;
    private final Function<I, O> function;

    /**
     * Constructor for the TimerDecorator class
     *
     * @param function the function to be executed
     */
    public TimerDecorator(Function<I, O> function) {
        this.function = function;
    }


    /**
     * Applies the action and prints the time it took to execute.
     *
     * @param input the input to the action
     * @return the output of the action
     */
    public O apply(I input ) {
        long startTime = System.currentTimeMillis();

        O result = function.apply(input);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        this.duration = duration;

        System.out.println("Total execution time: " + duration + " milliseconds");
        return result;
    }

    /**
     * Returns the duration of the action
     *
     * @return duration
     */
    public long getDuration() {
        return duration;
    }
}
