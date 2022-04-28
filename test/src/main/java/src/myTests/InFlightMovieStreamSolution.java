package src.myTests;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.*;

public class InFlightMovieStreamSolution {

    public static boolean canTwoMoviesFillFlight(int[] movieLengths, int flightLength) {

        // determine if two movies add up to the flight length
        /** 
         * Create a HashMap and store flightlength -value
         * Check pair for next nums if they match with index in hashMap
        */
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int movie : movieLengths) {
            if ( map.containsKey( flightLength - movie) ) {
                return true;
            }
            else {
                map.put( flightLength - movie, movie);
            }
            
        }

        return false;
    }


















    // tests

    @Test
    public void shortFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 1);
        assertFalse(result);
    }

    @Test
    public void longFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {2, 4}, 6);
        System.out.println("current test longFlightTest result    " + result);
        assertTrue(result);
    }

    @Test
    public void onlyOneMovieHalfFlightLenghtTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8}, 6);
        assertFalse(result);
    }

    @Test
    public void twoMoviesHalfFlightLengthTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {3, 8, 3}, 6);
        assertTrue(result);
    }

    @Test
    public void lotsOfPossiblePairsTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {1, 2, 3, 4, 5, 6}, 7);
        assertTrue(result);
    }

    @Test
    public void notUsingFirstMovieTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {4, 3, 2}, 5);
        assertTrue(result);
    }

    @Test
    public void multipleMoviesShorterThanFlightTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {5, 6, 7, 8}, 9);
        assertFalse(result);
    }

    @Test
    public void oneMovieTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {6}, 6);
        assertFalse(result);
    }

    @Test
    public void noMoviesTest() {
        final boolean result = canTwoMoviesFillFlight(new int[] {}, 6);
        assertFalse(result);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(InFlightMovieStreamSolution.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
