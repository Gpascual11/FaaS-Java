package Decorator;

import java.util.Map;
import java.util.function.Function;

/**
 * Class representing a decorator for memoization.
 *
 * @param <I> Type of input data for the function.
 * @param <O> Type of output data for the function.
 */
public class MemoizationDecorator<I, O> implements Function<I, O> {

    // Cache for memoization (Save Action result, actionId, parameters)
    private final Map<String, O> cache;

    private final Function<I, O> function;

    /**
     * Constructor for the MemoizationDecorator class.
     *
     * @param cache Cache for memoization.
     * @param function Function to be executed.
     */
    public MemoizationDecorator(Map<String, O> cache, Function<I, O> function) {
        this.cache = cache;
        this.function = function;
    }

    /**
     * Applies the memoization decorator.
     *
     * @param input Input data for the function,
     * @return Output data of the function.
     */
    public O apply(I input) {
        String actionId = generateActionId(function, input);
        O result;
        if (cache.containsKey(actionId)) {
            result = cache.get(actionId);
            System.out.println("Result from cache: " + result);
        } else {
            result = function.apply(input);

            cache.put(actionId, result);
            System.out.println("Result not in cache. Adding to cache: " + result);
        }

        return result;
    }

    /**
     * Generates a unique identifier for the action based on input parameters.
     *
     * @param function Function to be executed.
     * @param input Input data for the function.
     * @return Unique identifier for the action.
     */
    private String generateActionId(Function<I, O> function, I input) {
        return function.getClass().getSimpleName() + "_" + input.toString();
    }

}
