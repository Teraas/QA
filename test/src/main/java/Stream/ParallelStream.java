package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author harish.kumar-mbp
 * createdOn 17/02/24
 */
public class ParallelStream {

    public static void main(String[] args) {
        // Create a list of numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Sequential Stream Example
        System.out.println("Sequential Stream:");
        numbers.stream()
                .forEach(num -> System.out.print(num + " "));
        System.out.println();

        // Parallel Stream Example
        System.out.println("Parallel Stream:");
        numbers.parallelStream()
                .forEach(num -> System.out.print(num + " "));
        System.out.println();

        int desiredParallelism = 4;
        // Create a ForkJoinPool with the desired number of threads
        ForkJoinPool customThreadPool = new ForkJoinPool(desiredParallelism);

        // Use the custom ForkJoinPool as the common pool for parallel streams
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", String.valueOf(desiredParallelism));
        numbers.parallelStream()
                .forEach(num -> System.out.print(num + " "));
        //Runtime.getRuntime().availableProcessors()
    }
}
