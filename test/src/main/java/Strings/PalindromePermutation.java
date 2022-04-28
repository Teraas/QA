package Strings;
import java.util.Set;
import java.util.HashSet;

public class PalindromePermutation {


    public static boolean hasPalindromePermutation(String theString) {

        // track characters we've seen an odd number of times
        Set<Character> unpairedCharacters = new HashSet<>();

        for (int i = 0; i < theString.length(); i++) {
            char c = theString.charAt(i);
            if (unpairedCharacters.contains(c)) {
                unpairedCharacters.remove(c);
            } else {
                unpairedCharacters.add(c);
            }
        }

        // the string has a palindrome permutation if it
        // has one or zero characters without a pair
        return unpairedCharacters.size() <= 1;
    }

}
