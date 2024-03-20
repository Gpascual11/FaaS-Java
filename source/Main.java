import Components.Controller;

import java.util.function.Function;
import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static <ResultFuture> void main(String[] args) throws ExecutionException, InterruptedException {

        Controller<Map<String, Integer>, Integer> controller = new Controller<Map<String, Integer>, Integer>();

        Function<Map<String, Integer>, Integer> f = x -> x.get("x") - x.get("y");//adeu
        controller.registerAction("addAction", f);
        int res = (int) controller.invoke("addAction", Map.of("x", 10, "y", 5));
        // System.out.println(res);

        List<Map<String, Integer>> input = Arrays.asList(new Map[]{
                Map.of("x", 2, "y", 3),
                Map.of("x", 9, "y", 1),
                Map.of("x", 8, "y", 8),
        });

        for (Map<String, Integer> stringIntegerMap : input) {
            System.out.println("Resultat: " + controller.invoke("addAction", stringIntegerMap));
        }
    }
}